# JANSEVA Android Demo

A beginner-friendly offline Android prototype for a college demonstration.

## Included screens
- Home dashboard
- Submit a civic complaint (department, location, description)
- View complaints submitted during the current app session
- About screen

## Important limitations
This is a demo only. It does not connect to a backend, government department, GPS, login, AI, photo upload, or push notifications. Complaint data is kept in memory and is lost when the app process closes. Do not enter real personal or sensitive information.

## Build APK online with GitHub Actions
1. Create a new GitHub repository.
2. Extract this ZIP on your computer.
3. Upload the *contents* of the extracted folder to the repository root. Keep the `.github` folder (it may be hidden on Windows).
4. Commit the upload to the `main` branch.
5. Open the repository's **Actions** tab and select **Build JANSEVA APK**.
6. If the workflow has not started automatically, choose **Run workflow**.
7. Open the successful run and download the `JANSEVA-APK` artifact. Extract the downloaded artifact ZIP to get `app-debug.apk`.

The workflow uses GitHub-hosted Ubuntu runners, Java 17, Android SDK 35, and Gradle 8.9. No Android Studio is required on your laptop.
