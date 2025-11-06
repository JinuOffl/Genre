# Genre – Android Movie Streaming App

**Genre** is an Android-based OTT movie streaming application that allows users to browse and watch movies categorized by **title, genre, or cast**. Built using **Java**, **XML**, and **Firebase Realtime Database**, it delivers a fast, responsive, and immersive entertainment experience.  

Designed in **Android Studio**, with UI components built in **XML** and visual assets designed in **Figma**, Genre provides a modern interface with real-time data updates and seamless video playback powered by **ExoPlayer**.

---

## 🚀 Key Features

- 🎥 **Stream Movies Instantly:** Watch HD movies directly within the app using ExoPlayer.
- 📱 **Dynamic Firebase Content:** Fetch movies, details, and cast info in real-time via Firebase Realtime Database.
- 🔍 **Smart Search:** Search by title, cast, or genre with instant results.
- 🧠 **Categorized Browsing:** Movies displayed by genre such as Action, Horror, Romance, Comedy, Sci-Fi, etc.
- 🎭 **Detailed Info:** View title, description, cast, thumbnail, and genre-specific data.
- 🎨 **Modern UI:** A sleek, responsive interface designed with XML and Figma.
- ☁️ **Cloud Integration:** Firebase Authentication and Database ensure secure, scalable cloud storage.
- ⚙️ **Admin Controls:** Manage content dynamically from Firebase’s web dashboard.
- 🔔 **Notifications & Updates:** Easily integrate push notifications and updates for new releases.

---

## 🏗️ Tech Stack

| Category | Technology |
|-----------|-------------|
| **Language** | Java |
| **UI Design** | XML, Material Components |
| **Backend / Cloud** | Firebase Realtime Database, Firebase Authentication |
| **Video Player** | ExoPlayer |
| **Image Loading** | Glide |
| **IDE** | Android Studio |
| **Design Tools** | Figma |
| **Build System** | Gradle |

---

## 🧩 Modules Overview

### 👑 Admin Modules
- Admin Login Page (Firebase Authentication)
- Content Management (Add/Delete Movies)
- User Management Dashboard
- Analytics & Reporting via Firebase Console

### 👤 User Modules
- Firebase User Authentication
- Browse Movies by Genre
- Search by Title or Cast
- View Detailed Descriptions
- Watch Movies Instantly (ExoPlayer)
- Manage Preferences and Settings

---

## ⚙️ System Requirements

### Software
- **Android Studio (Latest Version)**
- **Firebase Realtime Database**
- **APIs:** ExoPlayer, Glide, Material Components
- **Languages:** Java, XML
- **Operating System:** Windows / macOS / Linux

### Hardware
- **RAM:** Minimum 4GB  
- **Storage:** 500MB (for development environment)
- **Internet Connection:** Required for streaming and Firebase sync
- **Device:** Android Smartphone or Emulator

---

## 🔧 Installation & Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/Genre.git
   cd Genre
   ```

2. **Open in Android Studio**
   - Launch Android Studio → *Open Project* → Select the cloned folder.

3. **Connect Firebase**
   - In Android Studio: `Tools → Firebase → Realtime Database → Connect to Firebase`.
   - Add your `google-services.json` file in `/app` directory.

4. **Build & Run**
   - Sync Gradle Files.
   - Run the app on a physical device or emulator.

---

## 🧱 App Architecture

The app follows a **modular architecture** with separation of concerns:

```
📂 com.example.genre
 ┣ 📂 activities
 ┃ ┣ SplashActivity.java
 ┃ ┣ MainActivity.java
 ┃ ┣ PlayerActivity.java
 ┃ ┗ SearchActivity.java
 ┣ 📂 fragments
 ┃ ┣ HomeFragment.java
 ┃ ┗ DetailFragment.java
 ┣ 📂 adapters
 ┃ ┣ FeatureAdapter.java
 ┃ ┗ MovieGenreAdapter.java
 ┣ 📂 models
 ┃ ┣ FeatureModel.java
 ┃ ┗ CastModel.java
 ┣ 📂 layouts
 ┃ ┣ activity_main.xml
 ┃ ┣ fragment_home.xml
 ┃ ┗ fragment_detail.xml
```

**Core Libraries Used**
- `ExoPlayer` for adaptive streaming  
- `Glide` for efficient image loading and caching  
- `FirebaseUI` for automatic RecyclerView population from Firebase  
- `Material Components` for UI styling  

---

## 📊 Database Structure (Firebase Realtime DB)

```json
{
  "Featured": {
    "movieId1": {
      "title": "Inception",
      "genre": "SciFi",
      "desc": "A skilled thief leads a team into dreams.",
      "thumb": "URL",
      "cover": "URL",
      "cast": "Leonardo DiCaprio, Ellen Page",
      "movie": "VIDEO_URL"
    }
  },
  "Cast": {
    "movieId1": {
      "castMember1": {"name": "Leonardo DiCaprio", "role": "Cobb"}
    }
  }
}
```

---

## 🔐 Authentication Flow
- Firebase Email & Password Authentication.
- Secure login, logout, and user data management.
- Real-time session handling for smoother user experience.

---

## 📚 Bibliography & References
- [Firebase Documentation](https://firebase.google.com/docs)
- [ExoPlayer Developer Guide](https://exoplayer.dev/)
- [Glide Image Library](https://bumptech.github.io/glide/)
- [Android Developer Docs](https://developer.android.com/)
- [Firebase Authentication Guide](https://medium.com/@Adekola_Olawale/firebase-authentication)

---

## 🧠 Future Enhancements
- Add **Watchlist & Favorites**
- Implement **In-App Downloads / Offline Mode**
- Support for **Multiple Languages**
- Integrate **AdMob** for monetization
- Enhance UI with **Jetpack Compose**
- Build **Recommendation Engine** using Firebase ML

---

## 💡 Conclusion

**Genre** bridges creators and audiences through a **legal, high-quality, and user-friendly streaming platform**.  
It empowers users to enjoy diverse content for free while ensuring creators receive fair recognition and support.  
By leveraging Firebase and Android Studio, it delivers a **fast, scalable, and modern OTT experience**.

---

### Developer
**Author:** *Jinu P* 
**Technologies:** Java | XML | Firebase | ExoPlayer  
**Designed with:** Android Studio & Figma  
