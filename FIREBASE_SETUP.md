# Firebase setup (outside-Android prerequisites)

Manual steps to complete in the Firebase / Google Cloud consoles before the app can build & run.
The Android code (Gradle deps, `Application`, ViewModel, auth, UI states) is implemented separately in the repo.

## Key values for this project

- **Android package name:** `com.example.recept`
- **Debug SHA-1:** `A8:DD:D9:C2:3E:C7:06:B0:9A:1E:7D:9F:FD:84:83:4B:05:FA:53:98`
  - Regenerate anytime with: `./gradlew signingReport` (look at the `debug` variant).

Do the console setup **A, B, D, E** first, then **C** (download `google-services.json` last, so it includes the web client from E). **F–G** happen *after* the app's first sign-in (your UID doesn't exist until then). **H** anytime.

## A. Create the Firebase project
1. Go to <https://console.firebase.google.com> → **Add project**.
2. Name it e.g. `Recept`. Disable Google Analytics (not needed).

## B. Register the Android app
3. Project overview → **Android** icon ("Add app").
4. **Android package name:** `com.example.recept` (must match exactly).
5. **Debug signing certificate SHA-1:** paste the SHA-1 above. *(Optional in the UI but REQUIRED for Google Sign-In — don't skip.)*
6. **Register app**.

## C. Add `google-services.json`
> ⚠️ **Order matters:** download `google-services.json` **only after you finish step E** (enabling Google sign-in). The file must include the auto-created OAuth **web client**, which doesn't exist until E is done. If you download it now, come back and **re-download** after E — otherwise `R.string.default_web_client_id` will be missing and sign-in fails.

7. (After step E) Download `google-services.json` and place it at: `app/google-services.json` (the `app/` module folder, NOT the repo root).
8. Optional: add `app/google-services.json` to `.gitignore` if you don't want to commit project config.
9. Skip the rest of Firebase's "add SDK" wizard — the Gradle/code parts are handled in the repo.

## D. Enable Firestore
10. Left nav → **Firestore Database** → **Create database**.
11. **Location:** pick one near you (e.g. `eur3` / `europe-west`). ⚠️ Permanent, can't change later.
12. Start in **production mode** (locked). Real rules are set in step G.

## E. Enable Google authentication
13. Left nav → **Authentication** → **Get started**.
14. **Sign-in method** tab → **Google** → **Enable** → set a support email → **Save**.
15. This auto-creates the OAuth **Web client**. No need to copy the ID by hand — the `google-services` Gradle plugin generates it into `R.string.default_web_client_id` from `google-services.json`, and the sign-in code reads it from there. (Just make sure you download `google-services.json` *after* enabling Google sign-in so the web client is included.)

## F. Get your UID *(after first sign-in)*

> **Concept:** authentication and Firestore rules are independent. Signing in with Google creates your Auth user + UID *regardless of the rules* — the rules only control whether the app can then read recipes. So the temporary open rule below isn't what creates your UID; it just lets the app load recipes so you can confirm the whole flow end-to-end.

Your UID doesn't exist until you sign in once, and to sign in you must run the app — so:

16. **Set a temporary open rule** so reading works once signed in. Firestore Database → Rules → Publish:
    ```
    rules_version = '2';
    service cloud.firestore {
      match /databases/{database}/documents {
        match /recipes/{id} {
          allow read, write: if request.auth != null;
        }
      }
    }
    ```
17. **Run the app** (`app/build/outputs/apk/debug/app-debug.apk`, or Android Studio ▶ Run).
    - ⚠️ The device/emulator **must have Google Play services** — use a **"Google Play"/"Google APIs"** emulator image (not plain AOSP), signed into a Google account.
    - On launch the app auto-fires the Google account picker; choose your account. Landing on the recipe list or the "Inga recept än" empty state means sign-in succeeded.
18. **Copy your UID:** Firebase console → **Authentication → Users** tab → your account row → **User UID** column.

## G. Lock the rules to your UID
19. **Firestore Database → Rules**, replace with (paste your UID):
    ```
    rules_version = '2';
    service cloud.firestore {
      match /databases/{database}/documents {
        match /recipes/{id} {
          allow read, write: if request.auth != null
                             && request.auth.uid == "YOUR_UID_HERE";
        }
      }
    }
    ```
    → **Publish**.

## H. Seed recipes
**All** recipes live in a **single document** `recipes/all`, in one string field named **`json`** that holds a **JSON array** of recipe objects. Editing workflow: open `recipes/all`, copy the `json` field into a code editor, edit, paste the whole thing back.

20. **Firestore Database → Data** → **Start collection** → collection ID **`recipes`** → add a document with **Document ID** typed as **`all`** (NOT Auto-ID) → add one field:
    - **Field name:** `json`
    - **Type:** string
    - **Value:** a JSON **array** of recipes, e.g.:
      ```json
      [
        {"name":"Pannkakor","portions":4,"isVegetarian":true,"timeMinutes":30,"ingredients":[{"name":"vetemjöl","quantity":2.5,"unit":"dl"},{"name":"mjölk","quantity":6.0,"unit":"dl"}],"steps":["Vispa ihop smeten","Stek tunna pannkakor i smör"]},
        {"name":"Tomatpasta","portions":4,"isVegetarian":true,"timeMinutes":30,"ingredients":[{"name":"pasta","quantity":400.0,"unit":"g"}],"steps":["Koka pastan","Rör ihop"]}
      ]
      ```

Each recipe object's shape (no `id` key — recipes are identified by position in the array for now):

| Key            | Type              |
|----------------|-------------------|
| `name`         | string            |
| `portions`     | number            |
| `isVegetarian` | boolean           |
| `timeMinutes`  | number            |
| `steps`        | array of strings  |
| `ingredients`  | array of `{ name, quantity, unit }` objects |

The app reads only `recipes/all`; any other documents in the collection are ignored. Recipes are parsed element-by-element, so one malformed object is skipped rather than breaking the whole list.

---

## What the code needs from you
- `app/google-services.json` present (step C) — the build fails without it.
- Google sign-in provider enabled (step E) so `R.string.default_web_client_id` is generated.
- Your UID only matters for tightening the rules (step G); the code doesn't reference it.
