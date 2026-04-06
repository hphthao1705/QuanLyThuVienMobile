# QuanLyThuVienMobile

A mobile library management app for Android, built as a coursework project for **Advanced Software Engineering**. Only registered students of the school can log in to browse, borrow, and manage library documents.

## Features

- **Authentication** — Student-only registration and login
- **Home** — Image slideshow banner, top-borrowed books, and book categories
- **Search** — Search documents by title or keyword
- **Book Detail** — View full document info including publisher, category, and borrow status
- **Borrow / Return** — Create borrow slips and track borrow history
- **Favourites** — Add or remove documents from a personal favourites list
- **Cart** — Add documents to a local cart before submitting a borrow request
- **History** — View past borrow and return records

## Architecture

MVVM (Model–View–ViewModel) with Repository pattern.

```
UI (Activities / Fragments)
    └── ViewModel
        └── Repository
            ├── Remote: Retrofit2 REST API
            └── Local:  Room database
```

## Tech Stack

| Category | Library |
|---|---|
| Networking | Retrofit2, Gson, Moshi |
| Reactive | RxJava2, RxAndroid |
| Persistence | Room, SharedPreferences |
| Image loading | Glide |
| Dependency injection | Dagger2, Hilt |
| UI | Data Binding, ViewPager, RecyclerView, Material Design |
| Testing | JUnit4, Espresso, Mockito, MockK, Robolectric |

## Requirements

- Android Studio Hedgehog or later
- Android SDK 34 (compile), min SDK 24 (Android 7.0)
- Java 17 / Kotlin

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/QuanLyThuVienMobile.git
   ```
2. Open the project in Android Studio.
3. Set the base API URL in `app/src/main/java/com/example/qlthuvien/data/remote/Common.java`.
4. Build and run on an emulator or physical device (API 24+).

## Project Structure

```
app/src/main/java/com/example/qlthuvien/
├── data/
│   ├── local/          # Room database, DAOs, entities
│   ├── model/          # Domain models (TaiLieu, MuonTra, DocGia, ...)
│   └── remote/         # Retrofit API service and client
├── di/                 # Dagger component definitions
├── dto/                # Data transfer objects
├── respository/        # Repository classes
├── utils/              # Constants, SharedPrefs helper
├── view/
│   ├── activities/     # LoginActivity, MainActivity, RegisterActivity, DetailsBookActivity
│   ├── adapter/        # RecyclerView adapters
│   └── fragments/      # Home, Cart, Favourites, History, Search, Category, ...
└── viewmodels/         # ViewModels for each feature
```
