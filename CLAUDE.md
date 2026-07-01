# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project overview

Recept ("recipe" in Swedish) is a native Android app built with Kotlin and Jetpack Compose. It's an app for storing recipes containing ingredients and instructions. The app is in development so a lot of features are still missing.

## Commands

Run all commands from the repository root using the Gradle wrapper (`gradlew.bat` on Windows, `./gradlew` on Unix shells).

- Build debug APK: `gradlew.bat assembleDebug`
- Run unit tests (JVM, `app/src/test`): `gradlew.bat testDebugUnitTest`
- Run a single unit test class: `gradlew.bat testDebugUnitTest --tests "com.example.recept.ExampleUnitTest"`
- Run instrumented tests (`app/src/androidTest`, requires an emulator/device): `gradlew.bat connectedDebugAndroidTest`
- Lint: `gradlew.bat lint`
- Full check (lint + tests): `gradlew.bat check`

## Architecture

The app should follow a clean organized architecture that allows it to keep growing. KISS (Keep it simple stupid) and YAGNI (you ain't gonna need it) should be followed when adding new functionality since things can always be extended at a later point when needed.

Each screen file includes `@Preview` composables at the bottom (e.g. `RecipeListScreenPreview`, `EmptyRecipeListScreenPreview`, `CookingScreenPreview`) using `sampleRecipes` — use these via Android Studio's preview pane when iterating on UI.

## Notes

- `minSdk` and `targetSdk` are both 36 (`app/build.gradle.kts`); the app targets current Android only, no backward-compatibility shims are needed.
- There is no dependency injection framework, repository layer, or persistence (Room/DataStore) yet — introducing real data loading will require adding one.
- The design of the app should be simple with colors that are nice to look at and it should be easy to find the information being looked for. Nothing fancy, but also not dull.
