# SimpleSample - Android Boilerplate Repository

<p align="center">
<img alt="Android Boilerplate Banner" src="https://raw.githubusercontent.com/RiteshF7/simpleSample/refs/heads/main/androidboilerplatebanner.png"> 
</p>

## Overview

SimpleSample is a comprehensive Android boilerplate repository designed to eliminate the repetitive setup process when starting new Android projects. Instead of writing the same boilerplate code repeatedly, developers can simply merge feature branches to quickly add commonly used functionalities like dependency injection, networking, database integration, and more.

## Problem & Solution

**Problem:** Starting a new Android project requires writing extensive boilerplate code for features like Hilt, Retrofit, Room Database, Navigation, etc., which is time-consuming and repetitive.

**Solution:** SimpleSample provides a modular branch-based approach where each feature is maintained in separate branches. Developers can start with a clean base and merge only the features they need, creating a customized project setup in minutes.

## Available Feature Branches

<p align="center">
<img alt="Android Boilerplate Banner" src="https://raw.githubusercontent.com/RiteshF7/simpleSample/refs/heads/main/simple_roadmap.png"> 
</p>


### Core Setup
- **`initial_clean_setup`** - Clean Android project with basic folder structure
- **`HILT`** - Dagger Hilt dependency injection setup
- **`HILT-NAV`** - Navigation component with Hilt integration

### Networking & Database
- **`HILT-RETROFIT`** - Retrofit networking with Hilt
- **`HILT-ROOM-DB`** - Room database with Hilt integration
- **`HILT-RETROFIT-ROOM`** - Combined Retrofit and Room setup

### Feature Implementations
- **`HOME-TOP-HEADLINES`** - Home screen with top headlines
- **`TOP-HEADLINES-PAGGING`** - Pagination implementation for news
- **`TOP-HEADLINES-SEARCH`** - Search functionality with Flow operators
- **`TOP-HEADLINES-OFFLINE`** - Offline caching with single source of truth
- **`TOP-HEADLINES-REMAINING`** - Additional news features (sources, countries, languages)
- **`NAV-HOME`** - Navigation setup with home screen

### Complete Implementation
- **`HILT_NEWS_APP`** - Complete news app with all features merged
- **`master`** - Stable release with all branches merged

## Sample Implementation: News App

The repository includes a complete News App implementation showcasing all features:

<p align="center">
<img alt="Android Boilerplate Banner" src="https://raw.githubusercontent.com/RiteshF7/simpleSample/refs/heads/main/roadmap_news_app.png"> 
</p>



### Major Highlights

- **Jetpack Compose** for modern UI development
- **MVVM Architecture** with Clean Architecture principles
- **Offline caching** with single source of truth
- **Dagger Hilt** for efficient dependency injection
- **Retrofit** for seamless API integration
- **Room Database** for local data storage
- **Coroutines** and **Flow** for asynchronous programming
- **StateFlow** for reactive state management
- **Paging 3** for efficient data loading
- **Navigation Component** for smooth screen transitions
- **WorkManager** for background tasks
- **Coil** for optimized image loading
- **Unit Tests** and **UI Tests** for robust code coverage

### Features Implemented

- **News Headlines** - Display latest news from various sources
- **Instant Search** - Real-time search with Flow operators:
  - Debounce for optimized API calls
  - Filter for relevant results
  - DistinctUntilChanged for duplicate prevention
  - FlatMapLatest for latest results
- **Offline Support** - Read news without internet connection
- **Pagination** - Efficient loading of large news datasets
- **Country-wise News** - Filter news by different countries
- **Language Selection** - Multi-language news support
- **Source Filtering** - News from specific sources
- **Background Sync** - Periodic news updates using WorkManager

## 🛠️ Tech Stack

### UI & Architecture
```kotlin
// Jetpack Compose
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.ui:ui-graphics")
implementation("androidx.compose.ui:ui-tooling-preview")
implementation("androidx.compose.foundation:foundation")
implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
implementation("androidx.activity:activity-compose:1.8.2")
implementation(platform("androidx.compose:compose-bom:2023.08.00"))

// Material 3
implementation("androidx.compose.material3:material3:1.1.2")
```

### Navigation
```kotlin
implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
implementation("androidx.navigation:navigation-compose:2.7.6")
```

### Networking
```kotlin
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")
implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
```

### Database
```kotlin
implementation("androidx.room:room-runtime:2.5.0")
kapt("androidx.room:room-compiler:2.6.1")
implementation("androidx.room:room-ktx:2.5.0")
```

### Dependency Injection
```kotlin
implementation("com.google.dagger:hilt-android:2.44")
kapt("com.google.dagger:hilt-android-compiler:2.44")
```

### Pagination
```kotlin
implementation("androidx.paging:paging-runtime-ktx:3.2.1")
implementation("androidx.paging:paging-compose:3.2.1")
```

### Image Loading
```kotlin
implementation("io.coil-kt:coil-compose:2.4.0")
```

### Background Processing
```kotlin
implementation("androidx.work:work-runtime-ktx:2.9.0")
implementation("androidx.hilt:hilt-work:1.1.0")
kapt("androidx.hilt:hilt-compiler:1.1.0")
```


## Project Structure

```
com.trex.simplesample/
├── data/
│   ├── local/                    # Room database implementation
│   │   ├── dao/                  # Database access objects
│   │   └── entity/               # Database entities
│   └── remote/                   # Network implementation
│       ├── models/               # API response models
│       └── repositories/         # Repository implementations
├── di/                           # Dependency injection modules
│   └── modules/                  # Hilt modules
├── domain/                       # Business logic layer
│   ├── models/                   # Domain models
│   ├── repositories/             # Repository interfaces
│   └── usecase/                  # Business use cases
├── ui/                           # Presentation layer
│   ├── base/                     # Base UI components
│   ├── theme/                    # App theming
│   └── [feature]/                # Feature-specific screens
└── utils/                        # Utility classes
```

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/SimpleSample.git
cd SimpleSample
```

### 2. Start with Clean Setup
```bash
git checkout initial_clean_setup
```

### 3. Merge Required Features
```bash
# For basic Hilt setup
git merge HILT

# For networking
git merge HILT-RETROFIT

# For database
git merge HILT-ROOM-DB

# For navigation
git merge HILT-NAV

# For complete news app
git merge HILT_NEWS_APP
```

### 4. Configure API
1. Get your API key from [NewsAPI](https://newsapi.org/)
2. Add to your `/utils/AppConstants`:
```properties
API_KEY="your_api_key_here"
```

### 5. Build and Run
```bash
./gradlew build
```

## 🔧 Usage Guide

### Starting a New Project

1. **Fork/Clone** this repository
2. **Checkout** the `initial_clean_setup` branch
3. **Create** your feature branch: `git checkout -b my-new-feature`
4. **Merge** required feature branches based on your needs
5. **Customize** the merged code for your specific requirements

### Available Merge Combinations

#### Basic Setup (Minimal)
```bash
git merge HILT
git merge HILT-NAV
```

#### API Integration
```bash
git merge HILT
git merge HILT-RETROFIT
```

#### Local Database
```bash
git merge HILT
git merge HILT-ROOM-DB
```

#### Complete Setup
```bash
git merge HILT_NEWS_APP  # All features included
```

## Architecture

The project follows **Clean Architecture** principles with **MVVM** pattern:

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Presentation  │    │     Domain      │    │      Data       │
│                 │    │                 │    │                 │
│   • UI (Compose)│◄──►│   • Use Cases   │◄──►│   • Repository  │
│   • ViewModel   │    │   • Models      │    │   • API Service │
│   • State       │    │   • Repository  │    │   • Database    │
│                 │    │     Interfaces  │    │                 │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## Contributing

We welcome contributions! Here's how you can help:

### Adding New Features

1. **Fork** the repository
2. **Create** a feature branch: `git checkout -b feature/your-feature-name`
3. **Implement** your feature following the existing patterns
4. **Update documentation** if needed
5. **Submit** a pull request


### Guidelines

- Follow existing code style and architecture patterns
- Update README for new feature branches
- Use meaningful commit messages

### Feature Branch Naming Convention

- `FEATURE-NAME` - For new features
- `FEATURE-NAME-IMPROVEMENT` - For enhancements
- `BUGFIX-DESCRIPTION` - For bug fixes

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- [Android Developers](https://developer.android.com/) for excellent documentation
- [NewsAPI](https://newsapi.org/) for providing news data
- Open source community for inspiration and contributions

## About Me

Hi there! My name is **Ritesh Singh**, I work as a Senior Android Developer and like to build things to make life a little bit less difficult for everyone.

If you have any questions or want to connect, feel free to reach out to me on:

- ## Connect with Me

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Profile-blue?logo=linkedin&style=for-the-badge)](https://www.linkedin.com/in/riteshf1/)
## Connect with Me

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Profile-blue?logo=linkedin&style=for-the-badge)](https://www.linkedin.com/in/riteshf7/)
- [GitHub](https://github.com/riteshf7)

---

<p align="center">
Made with ❤️ by <a href="https://github.com/riteshf7">Ritesh Singh</a>
</p>

<p align="center">
If you found this project helpful, please consider giving it a ⭐️
</p>