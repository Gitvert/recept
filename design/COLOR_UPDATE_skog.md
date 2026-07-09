# Color Update: Grädde → Skog (green-forward)

The **Skog** palette keeps every layout, size, radius, and shadow exactly the same as the
original design — **only colors change**. To migrate an existing implementation of the original
(cream/"Grädde") palette to Skog, do a project-wide find/replace of the hex values below.

## Direct hex remap (old → new)

| # | Role | Original (Grädde) | Skog (new) |
|---|---|---|---|
| 1 | Presentation backdrop (behind device frame — usually not in-app) | `#e7e1d1` | `#d9dec9` |
| 2 | **App / screen background** | `#f5efe3` | `#e9eede` |
| 3 | Device bezel (mockup only) | `#20301d` | `#1b2917` |
| 4 | Deep green — status text, dark CTAs | `#33452f` | `#24361f` |
| 5 | Hairline divider (header borders, pill borders) | `#e4dcc9` | `#d3dcc2` |
| 6 | Green accent — header/chip icons, links | `#4a6047` | `#3c6b3e` |
| 7 | **Surface / card background** | `#fffdf7` | `#fbfdf4` |
| 8 | Card / row border | `#efe8d7` | `#dde5cd` |
| 9 | Meta-chip strip background | `#eaeede` | `#dbe6cd` |
| 10 | **Primary green** — buttons, FAB, checked checkbox fill, chip text | `#3f5540` | `#2f5030` |
| 11 | "Vegetariskt" tag background | `#e7ecdf` | `#d6e3c6` |
| 12 | "Vegetariskt" tag text/icon | `#5f7a4a` | `#4c6b38` |
| 13 | Info panel background ("Se ingredienser…") | `#eef1e7` | `#dfe8d1` |
| 14 | Cooking-mode ingredients pane background | `#efe9dc` | `#e2ead6` |
| 15 | Step-number accent (bold number before each step) | `#8fa06a` | `#6f8a45` |
| 16 | Checkbox unchecked border | `#c6bfa9` | `#b7c1a4` |
| 17 | Italic eyebrow (durations, "Under 45 minuter") | `#8a7a4a` | `#6f7a45` |

**Unchanged** (kept from the original): text colors `#2b2a22` / `#5e5c4c` / `#7c7a68` /
`#8a8873` / `#a8a48f`, checked-item dim `#b0ab98`, food-placeholder stripes `#e9e2cf` /
`#e2dac4` / `#9a9377`, offer badge `#c07a52`, and the tweakable warm accent `#c8a24a`.

## Notes for the implementer
- These are the only changes — do **not** touch spacing, typography, radii, or shadows.
- If your codebase uses semantic color tokens (recommended), update the token values rather
  than raw hex: `background`→`#e9eede`, `surface`→`#fbfdf4`, `primary`→`#2f5030`,
  `accent`→`#3c6b3e`, `divider`→`#d3dcc2`, `chipBg`→`#dbe6cd`. The "Role" column above maps
  each hex to its semantic purpose.
- The net effect: the whole surface shifts from warm cream to soft sage, cards become a
  green-tinted off-white, and the primary/accents become a noticeably richer, more present
  green. Contrast ratios remain equivalent to the original.
- One shadow uses a literal cream RGBA (the FAB focus ring `rgba(245,239,227,0.85)`) — optional
  to nudge toward `rgba(233,238,222,0.85)` to match the new background; visually negligible.

## Preferred palette
**Skog is the chosen palette going forward** — implement new work against these values, not the
original Grädde set.
