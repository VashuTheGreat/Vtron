# Vtron3 - Simple URL Saver Android App

## Overview
Vtron3 is a lightweight Android application that allows users to save URLs with associated names locally on the device. Saved entries persist across app restarts using SharedPreferences. Each saved entry can be deleted individually with a dedicated delete button.

The app demonstrates dynamic UI updates in Android with user input, persistent local storage, and basic data management.

---

## Features

- Enter a URL and a name for the app/site.
- Save multiple entries locally on the device.
- Display all saved entries dynamically.
- Delete any saved entry individually.
- Data persists even after closing and reopening the app.
- Simple, clean user interface with dark background.

---

## Screenshots

*(Add your screenshots here if available)*

---

## Getting Started

### Prerequisites
- Android Studio (recommended latest version)
- Android SDK (API Level 21+ recommended)

### How to Run
1. Clone or download this repository.
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.
4. Enter a URL and a name, then tap **Save App**.
5. Saved entries will appear below with a **Delete** button to remove them.

---

## Code Highlights

- **Persistent Storage:** Uses `SharedPreferences` with Gson to save/load lists of URLs and names.
- **Dynamic UI:** Dynamically adds TextViews and Delete buttons inside a vertical LinearLayout.
- **User Input:** Validates non-empty URL and name before saving.
- **Delete Functionality:** Remove specific saved entries with immediate UI and storage update.

---

## Project Structure


---

## Future Improvements

- Add input validation for proper URL format.
- Implement URL preview or favicon fetching.
- Support editing saved entries.
- Export/import saved URLs.
- Use a local database (Room) for better scalability.

---

## License

This project is open source and available under the MIT License.

---

## Author

VashuTheGreat

---

If you have any questions or suggestions, feel free to reach out!



MIT License

Copyright (c) [YEAR] [YOUR NAME OR ORGANIZATION]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
