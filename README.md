# NewsApp

An Android News Application built using modern Android development tools and frameworks. This app showcases the latest headlines, breaking news, and in-depth articles from multiple news sources. It is developed with Jetpack Compose for a fully declarative UI, utilizing the best practices for clean architecture and MVVM design pattern.

## Features

- Breaking News Feed: Real-time updates from various news sources.
- News Categories: Browse by categories such as Technology, Sports, Business, etc.
- Bookmarking: Save articles to read later.
- Dark Mode Support: Material 3 dark mode theme support.
- Paging: Load news feeds in a paginated manner.
- Search Functionality: Find news articles by keyword.

## Tech Stack

UI :
- Jetpack Compose: For building the UI declaratively.
- Material 3: For adhering to modern Material Design guidelines.
- Navigation Component: For seamless navigation between screens.
- Pager: For swiping through different sections and content easily.
- Coil: To load and display images efficiently.

Architecture :
- Clean Architecture: Separation of concerns through domain, data, and presentation layers.
- MVVM (Model-View-ViewModel): For structuring the app and managing UI-related data.
- ViewModel: For managing UI-related data lifecycle-conscious.

Data & Backend : 
- Room Database: For local data persistence.
- Retrofit: For making network requests to the news API.
- Coroutines & Flow: For managing asynchronous tasks and data streams in a clean, concise way.

Dependency Injection
- Hilt: For managing dependency injection efficiently across the app.

## Screenshots

<img src="https://github.com/med25ch/MyNewsApp/blob/main/screenshots/Discover.png" width="30%"></img> <img src="https://github.com/med25ch/MyNewsApp/blob/main/screenshots/MostRead.png" width="30%"></img> <img src="https://github.com/med25ch/MyNewsApp/blob/main/screenshots/Favorites.png" width="30%"></img> <img src="https://github.com/med25ch/MyNewsApp/blob/main/screenshots/Details.png" width="30%"></img> <img src="https://github.com/med25ch/MyNewsApp/blob/main/screenshots/Discorver_Dark.png" width="30%"></img> <img src="https://github.com/med25ch/MyNewsApp/blob/main/screenshots/Details_Dark.png" width="30%"></img> 
