# Handoff: Nordkök — Recipe App

## Overview
Nordkök is a personal cooking-recipe mobile app inspired by the ICA Recept app, redesigned
in a warm Nordic palette (deep forest green + cream/oat). It is a **single-user** app (no
social features), covering three screens:

1. **Receptlista** (recipe list / collection)
2. **Recept** (recipe detail — ingredients + method)
3. **Matlagningsläge** (cooking mode — ingredients & steps side-by-side, landscape)

All content is in **Swedish**.

## About the Design Files
The file in this bundle (`Nordkök.dc.html`) is a **design reference created in HTML** — a
prototype showing the intended look, layout, and behavior. It is **not production code to copy
directly**. It is built as a "Design Component" (a small streaming-HTML format) whose exact
runtime is irrelevant to implementation.

The task is to **recreate these designs in the target codebase's environment** (React Native,
Flutter, SwiftUI, native Android, etc.) using its established patterns, navigation, and
component libraries. If no codebase exists yet, choose the most appropriate mobile framework
and implement there. Treat the HTML as the visual + behavioral spec, not as source.

## Fidelity
**High-fidelity.** Colors, typography, spacing, radii, and interactions are final and should
be recreated faithfully. The only placeholders are the **food images** (rendered as diagonally
striped boxes with a monospace label naming the intended photo) — swap these for real image
components.

## Design Tokens

### Colors
| Token | Hex | Usage |
|---|---|---|
| Backdrop (canvas behind phones) | `#e7e1d1` | Presentation only — not part of the app UI |
| App background | `#f5efe3` | Screen background (warm oat) |
| Surface / card | `#fffdf7` | Recipe cards, ingredient/step rows, pills |
| Surface alt (cooking right pane) | `#efe9dc` | Ingredients pane in cooking mode |
| Info panel bg | `#eef1e7` / `#eaeede` | "Se ingredienser…" panel, meta-chip strip |
| Primary green | `#3f5540` | Primary buttons, FAB, checked checkbox fill |
| Deep green | `#33452f` | Header title accents, "Lägg till"-style CTAs, status text |
| Green accent (icons/links) | `#4a6047` | Header icons, chip icons, links |
| Green tag text/icon | `#5f7a4a` | "Vegetariskt" tag |
| Green tag bg | `#e7ecdf` | "Vegetariskt" tag background |
| Step-number green | `#8fa06a` | The bold step number before each method step |
| Bezel (device frame) | `#20301d` | Phone frame — presentation only |
| Text primary | `#2b2a22` | Titles, ingredient/step text |
| Text secondary | `#5e5c4c` | Ingredient names, body |
| Text muted | `#7c7a68` / `#8a8873` | Captions, sublabels |
| Text faint | `#a8a48f` | Placeholder-ish detail |
| Border hairline | `#e4dcc9` | Header dividers, pill borders |
| Card border | `#efe8d7` | Card / row borders |
| Checkbox unchecked border | `#c6bfa9` | Empty checkbox |
| Italic accent (durations) | `#8a7a4a` | "Under 45 minuter" italic label |
| Warm accent (tweakable) | `#c8a24a` (alt `#c07a52`, `#a98a3f`) | Reserved accent (offer badge / ratings if reintroduced) |
| Offer badge bg | `#c07a52` | "ERBJUDANDE" ribbon (currently unused/hidden) |

### Typography
- **Font family:** `Hanken Grotesk` (Google Fonts), weights 400/500/600/700/800.
- **Screen/section titles:** 24px / weight 800 / letter-spacing -0.01em (e.g. "Ingredienser",
  "Gör så här").
- **Recipe card title:** 20px / 800 / -0.01em / line-height 1.15.
- **App header title:** 20–24px / 800.
- **Ingredient row:** 16–17px; quantity+unit weight 800, name weight 400 color `#5e5c4c`,
  name offset `margin-left: 9px`.
- **Step row:** 16px / line-height 1.4–1.45; leading step number weight 800 color `#8fa06a`.
- **Meta chips:** 13px / 600.
- **Section eyebrow (durations):** 15px / 600 / **italic** / color `#8a7a4a`.
- **Small captions/labels:** 12–14px / 600–700, uppercase eyebrow uses letter-spacing 0.16–0.26em.

### Spacing / Radius / Shadow
- Screen padding: 16–20px horizontal; card internal padding 15–18px.
- Gaps: card list gap 14px; row list gap 10px.
- Radii: cards 22px; rows 15–16px; info panels 14–18px; pills/FAB `999px`/circle;
  checkboxes 6–8px; images 18px.
- Card shadow: `0 10px 24px -18px rgba(40,50,30,0.4)`.
- Row shadow: `0 6px 16px -16px rgba(0,0,0,0.4)`.
- FAB shadow: `0 14px 28px -8px rgba(51,69,47,0.7)` + `0 0 0 5px rgba(245,239,227,0.85)` ring.

## Screens / Views

### 1. Receptlista (Recipe List)
- **Purpose:** Browse the user's saved recipe collection.
- **Layout (portrait, ~372×806 content):** vertical stack — status bar, header row, meta row
  (with bottom hairline), then a scrolling list of recipe cards.
- **Header row:** back chevron (`#4a6047`) + title "Mat" (24/800). (A trailing ⋯ menu existed
  in the source reference but was removed per the single-user requirement.)
- **Meta row:** left — "29 recept" (weight 600 `#5e5c4c`) over "Skapad av Jesper";
  right — a sort control: list icon + "Senast tillagd" in `#4a6047` / 700 / 15px.
- **Recipe card** (bg `#fffdf7`, radius 22, border `#efe8d7`):
  - Left column: title (20/800), optional **"Vegetariskt"** pill (bg `#e7ecdf`, text/icon
    `#5f7a4a`, 12/700, radius 999, leaf icon).
  - Right: 112×112 image (rounded 18) — placeholder striped box labelled with the dish.
  - Bottom **meta-chip strip** (bg `#eaeede`, radius 14): clock + time ("Under 45 min"),
    bottle icon + "{n} ingredienser", bars icon + difficulty ("Enkel"/"Medel"). All chips
    13/600 `#3f5540`. Difficulty chip is toggleable (see State/tweaks).
  - **Removed for single-user use:** star rating, review count, comment count, favorite heart,
    ⋯ overflow. Do **not** re-add these.
- **Sample data:** 5 recipes — Våfflor grundrecept (veg, 6 ing, Enkel), Marry me chicken
  (18 ing, Medel), Krämig svamppasta (veg, 9, Enkel), Ugnsbakad lax med dill (7, Enkel),
  Rotfruktssoppa (veg, 11, Medel).

### 2. Recept (Recipe Detail)
- **Purpose:** View a single recipe; scale portions; read ingredients; step through method;
  jump into cooking mode.
- **Layout (portrait):** status bar → header (back + title + [right-side actions removed]) →
  scrollable body → **floating action button** pinned bottom-right of the frame.
- **Header:** back chevron → `Receptlista`; title "Våfflor grundrecept" (20/800).
- **Body (scrolls):**
  - **Hero image** 210px tall — placeholder ("våffelstapel · foto").
  - **Ingredients section:** title "Ingredienser" (24/800) on the left; **portion stepper** on
    the right — a pill (`#fffdf7`, border `#e4dcc9`, radius 999) with a `−` circle, "{n}
    portioner" (14/700), and a `+` circle (circles are `#eaeede`, stroke `#3f5540`).
  - **Ingredient rows** (read-only, no checkboxes here): each row bg `#fffdf7`, radius 16, shows
    `**{qty} {unit}**  {name}`.
  - **Method section:** italic eyebrow "Under 45 minuter" (`#8a7a4a`) + title "Gör så här".
    - An **info panel** (bg `#eef1e7`): copy "Se ingredienser och tillagningssteg bredvid
      varandra." + a full-width primary button **"Starta matlagningsläge"** (bg `#3f5540`, text
      `#f5efe3`, pot icon) → opens cooking mode.
    - **Step rows:** each is a **checkable** card — 24px checkbox (radius 8) + step number (bold
      green) + step text. Checking fills the box `#3f5540` with a white check, dims text to
      `#b0ab98`, and strikes it through.
  - (An allergen/"FÖR ALLA" block existed in the source but is currently empty/removed.)
- **Floating Action Button (FAB):** 60×60 circle, bg `#3f5540`, pot icon, pinned
  `position:absolute; right:18px; bottom:22px` **relative to the phone frame** so it stays
  fixed while the body scrolls. Tapping it opens cooking mode. (This is functionally the same
  action as the in-panel "Starta matlagningsläge" button.)

### 3. Matlagningsläge (Cooking Mode)
- **Purpose:** Hands-on cooking view — method and ingredients visible together.
- **Orientation:** **Landscape** (content ~812×392). Two equal columns.
- **Left column (steps):** title "Gör så här"; the same **checkable step rows** as detail (state
  shared with detail — checking a step in one place reflects in the other).
- **Right column (ingredients):** bg `#efe9dc`; title "Ingredienser" + a circular **close (✕)**
  button (top-right) → returns to the recipe detail. Rows are **checkable** here (24px→20px
  checkbox), same fill/check behavior as steps.
- **No portion stepper here** — cooking mode inherits whatever serving count was set on the
  detail screen (intentionally not editable mid-cook).

## Interactions & Behavior
- **Navigation (from the prototype's anchor links):**
  - Detail back chevron → Recipe list.
  - "Starta matlagningsläge" button **and** FAB → Cooking mode.
  - Cooking-mode ✕ → Recipe detail.
  - (List → detail tap target: wire card tap to open the recipe.)
- **Portion scaling:** `−`/`+` adjust servings (clamp **1–20**, base recipe = 4 portions).
  All ingredient quantities scale by `servings / 4` and re-render live. Cooking mode reflects
  the same count but cannot change it.
- **Quantity formatting:** scaled amounts round to fractions where clean — `.25→¼ .5→½ .75→¾`,
  otherwise 1 decimal with a **comma** decimal separator (Swedish, e.g. `2,3`). Whole numbers
  show plain. See `fmt()` in the source for exact logic.
- **Check-off:** step rows (detail + cooking) and ingredient rows (cooking only) toggle a
  checked state — fill `#3f5540`, white check icon, text dims to `#b0ab98` + strike-through
  (steps). Ingredient rows on the **detail** screen are read-only.
- **No animations** beyond default state changes; keep transitions subtle if added.

## State Management
- `servings` (int, default 4, clamp 1–20) — drives ingredient scaling; **shared** across detail
  & cooking.
- `stepChecked` (map index→bool) — **shared** between detail and cooking method lists.
- `ingChecked` (map index→bool) — used by cooking-mode ingredient list.
- `showDifficulty` (bool, default true) — toggles the difficulty meta chip on list cards.
- `warmAccent` (color) — reserved accent token (`#c8a24a` / `#c07a52` / `#a98a3f`).
- Recipe/step/ingredient content is static seed data (see `renderVals()` in the source).
  A real implementation would load this from a recipe store/DB. Yield label ≈ `round(6 × scale)`.

## Assets
- **Font:** Hanken Grotesk (Google Fonts). Use the platform equivalent or bundle the font.
- **Icons:** all icons are inline SVG (stroke-based, ~2px) — chevron, list/sort, clock, bottle
  (ingredients), bar chart (difficulty), leaf (vegetarian), pot with lid (cooking mode),
  check, plus, minus, close. Recreate with the codebase's icon set at matching weights.
- **Food images:** none provided — placeholders only. Replace with real photos; intended
  subjects are named in each placeholder (e.g. "våffelstapel", "kyckling i gräddsås").

## Files
- `Nordkök.dc.html` — the full design reference (all three screens + interaction logic).
  The markup is inline-styled HTML; the `<script>` block at the bottom holds the data model,
  `fmt()` quantity formatter, and state/handlers (`renderVals`).
