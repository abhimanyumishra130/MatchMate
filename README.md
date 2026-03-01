# 📱 MatchMate – Matrimonial Match Listing App

MatchMate is an Android application that simulates a matrimonial matchmaking platform.  
The app fetches user profiles from a remote API, computes compatibility scores locally, and allows users to Accept or Decline profiles while ensuring smooth functionality even in offline or unstable network conditions.

This project demonstrates clean architecture principles, offline-first data handling, and scalable Android development practices.

---

## 🚀 Project Setup Instructions

### Prerequisites
- Android Studio (Latest Stable Version)
- JetBrains Runtime 21 (JBR 21)
- Minimum SDK 24+
- Kotlin Enabled

### Setup Steps

1. Clone repository

```
git clone https://github.com/abhimanyumishra130/MatchMate.git
```

2. Open project in Android Studio.

3. Add Base URL inside **local.properties**

```
BASE_URL=https://randomuser.me/
```

> Base URL is stored in local.properties to avoid exposing configuration values in version control.

4. Sync Gradle and Run the project.

---

## 🧰 Libraries Used & Justifications

### Core Android Libraries
- androidx.core.ktx → Kotlin extensions for Android APIs
- androidx.lifecycle.runtime.ktx → Lifecycle aware components
- androidx.appcompat → Backward compatibility support
- material & material3 → Modern Material UI components
- constraintlayout → Flat and performant UI hierarchy

---

### UI
- Jetpack Compose → Declarative UI development
- Navigation Component → Fragment navigation handling
- ViewBinding → Type-safe view access and null safety

---

### Dependency Injection
- Hilt

**Justification**
- Reduces boilerplate dependency management
- Lifecycle aware injection
- Improves scalability and testability

---

### Networking
- Retrofit → REST API communication
- Gson Converter → JSON parsing
- Scalars Converter → Primitive response handling
- OkHttp Logging Interceptor → Network debugging

**Justification**
Retrofit provides type-safe API calls with coroutine support and is an industry-standard networking solution.

---

### Local Database
- Room Database

**Justification**
- Structured SQLite abstraction
- Compile-time query validation
- Enables offline-first architecture

---

### Image Loading
- Glide

**Justification**
Efficient image loading with caching and memory optimization.

---

## 🏗 Architecture

The application follows **MVVM with Clean Architecture**.

```
UI Layer (Activity / Fragment)
        ↓
ViewModel
        ↓
Domain Layer
        ↓
Repository (Single Source of Truth)
        ↓
-----------------------------------
Remote API      |     Local Database
-----------------------------------
```

### Architecture Benefits
- Separation of concerns
- Lifecycle awareness
- Testable components
- Scalable structure
- Offline data support

Repository decides whether data should come from API or local database.

---

## 🧩 Added Matrimonial Fields (Justification)

RandomUser API does not provide essential matrimonial attributes.  
Therefore, additional fields were introduced:

- Religion → Important compatibility factor in matrimonial systems
- Education → Helps evaluate professional and lifestyle compatibility

These additions make matchmaking logic more realistic.

---

## ❤️ Match Score Algorithm

A compatibility score between **0–100** is calculated locally.

### Factors Considered
- Age proximity
- Religion match
- Education match
- Location compatibility

### Algorithm

```kotlin
fun calculateMatchScore(person1: Person, person2: Person): Int {
    var score = 0

    val ageDifference = kotlin.math.abs(person1.age - person2.age)
    score += when {
        ageDifference <= 5 -> 30
        ageDifference <= 10 -> 20
        else -> 10
    }

    if (person1.religion == person2.religion) {
        score += 30
    }

    if (person1.education == person2.education) {
        score += 20
    }

    if (person1.city == person2.city &&
        person1.country == person2.country
    ) {
        score += 20
    }

    return score.coerceAtMost(100)
}
```

Match score is computed locally to ensure fast performance and offline availability.

---

## 📡 Offline Mode & Error Handling Strategy

The application follows an **Offline-First Approach**.

### Offline Handling
- User profiles cached using Room Database
- Previously fetched data shown when device is offline
- Accept/Decline actions persisted locally

### Error Handling
Handled scenarios include:
- API failures
- Network loss
- Flaky network conditions
- Database exceptions
- Partial data loading

### Strategy
1. Load cached data first
2. Attempt API refresh
3. Retry failed requests
4. Gracefully fallback to local database

This ensures uninterrupted user experience.

---

## ⚖️ Hypothetical Design Constraint Handling

### Scenario
Profile images cannot be displayed due to legal/privacy restrictions.

### Solution
UI can be modified to display:
- User initials avatar
- Gender placeholder icon
- Default illustration

This maintains usability while complying with privacy requirements.

---

## 🔍 Reflection

If given more time, the following improvements would be implemented:

- Swipe-based matchmaking interaction
- Background synchronization using WorkManager
- Server sync for Accept/Decline actions
- Advanced compatibility scoring
- Pagination optimization

Additionally, ViewBinding was integrated to improve UI safety and prevent runtime crashes caused by invalid view references.

---

## ✅ Key Highlights

- MVVM + Clean Architecture
- Offline-first data handling
- Local compatibility scoring
- Robust error handling
- Scalable repository pattern

---

## 👨‍💻 Author

Abhimanyu  
Android Developer
