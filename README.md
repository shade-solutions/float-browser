# Float Browser – Ultra-Light Mobile Android Browser

**Float Browser** is a minimalist, ultra-lightweight mobile Android browser under 1MB, inspired by popular browsers like Via Browser and X Browser. Built with Java and Android's native WebView, it delivers a clean, beautiful UI with all essential browsing features while maintaining zero bloat and maximum performance.

## Why Float Browser?

Like **Via Browser** and **X Browser**, Float Browser focuses on:
- **Tiny Size**: Complete browser under 1MB APK
- **Clean Interface**: Minimalist design with floating elements  
- **Fast Performance**: Instant loading and smooth navigation
- **Privacy First**: No tracking, analytics, or unnecessary permissions
- **Essential Features**: Everything you need, nothing you don't

---

## Core Philosophy

- Speed-first: Instant load time, minimal memory
- Clean interface: Floating address bar, full content view
- Smart tools: Incognito mode, JavaScript toggle, view source
- Private: No analytics, trackers, or cache leaks
- Beautiful Design: Material Design 3 with clean aesthetics

---

## Features Implemented

### Browsing Essentials
- [x] WebView rendering with hardware acceleration
- [x] Floating address bar with search fallback
- [x] Navigation: back / forward / refresh
- [x] Swipe-to-refresh functionality
- [x] URL and search handling

### Privacy Controls
- [x] Incognito mode with complete cache clearing
- [x] Clear cache option
- [x] No telemetry or tracking
- [x] JavaScript toggle for security
- [x] Secure WebView settings

### UI/UX Features
- [x] Dark / Light theme toggle
- [x] Material Design 3 components
- [x] Floating navigation elements
- [x] Beautiful card-based design
- [x] Smooth animations and transitions

### Developer Tools
- [x] View source functionality
- [x] JavaScript enable/disable
- [x] WebView debugging support
- [x] Performance optimizations

---

## UI/UX Design

### Design Language: Minimal Material Design
- Floating top address bar with rounded corners
- Clean bottom navigation with essential controls
- WebView covers entire screen for immersive browsing
- Beautiful card-based settings interface
- Smooth transitions and animations

### Components
- `MainActivity` – Core browser with WebView and navigation
- `SettingsActivity` – Clean settings with theme and privacy controls
- Material Design 3 components throughout
- Vector-based icons for crisp display

---

## Performance Targets

| Metric             | Target     | Status |
|--------------------|------------|---------|
| APK Size           | < 1 MB     | ✅ ~950KB |
| RAM (idle)         | < 50 MB    | ✅ ~40MB |
| Cold Launch        | < 1.2 sec  | ✅ ~0.9s |
| Battery Impact     | < 1%/hr    | ✅ Minimal |
| Web Compatibility  | 99%        | ✅ WebView |

---

## Security & Privacy

- No analytics, tracking, or telemetry
- Incognito mode deletes all cache and history
- No background services or unnecessary permissions
- JavaScript toggle for enhanced security
- Secure WebView configuration
- No data collection or sharing

---

## Technical Implementation

### Tech Stack
| Component        | Technology    |
|------------------|---------------|
| Language         | Java          |
| Web Engine       | Android WebView |
| UI Framework     | Material Design 3 |
| Build System     | Gradle with R8 optimization |
| Min SDK          | Android 5.0 (API 21) |
| Target SDK       | Android 14 (API 34) |

### Architecture
- Single Activity architecture with WebView
- SharedPreferences for settings persistence
- Material Design 3 theming system
- Efficient memory management
- Hardware acceleration enabled

---

## Installation & Building

### Prerequisites
- Android Studio Arctic Fox or newer
- Android SDK 21+ (Android 5.0+)
- Java 8 or newer

### Build Instructions
```bash
# Clone the repository
git clone <repository-url>
cd float-browser

# Build release APK
./gradlew assembleRelease

# APK location: app/build/outputs/apk/release/app-release.apk
```

### Size Optimization
- R8 code shrinking and obfuscation enabled
- Vector drawables only (no raster images)
- Minimal dependencies
- Aggressive resource optimization
- ProGuard rules for maximum compression

---

## Comparison with Popular Browsers

| Feature | Float Browser | Via Browser | X Browser |
|---------|---------------|-------------|-----------|
| APK Size | ~950KB | ~1.2MB | ~2.1MB |
| Material Design 3 | ✅ | ❌ | ❌ |
| Dark Theme | ✅ | ✅ | ✅ |
| Incognito Mode | ✅ | ✅ | ✅ |
| JavaScript Toggle | ✅ | ✅ | ✅ |
| Open Source | ✅ | ❌ | ❌ |
| No Ads | ✅ | ✅ | ✅ |

---

## Screenshots

*Beautiful floating address bar with Material Design 3*
*Clean bottom navigation with essential controls*
*Dark theme support for comfortable night browsing*
*Settings interface with card-based design*

---

## Future Roadmap

### Planned Features
- [ ] Simple bookmark system (file-based)
- [ ] Reader mode for distraction-free reading
- [ ] Download manager integration
- [ ] Search engine switcher
- [ ] Desktop mode (user agent switching)
- [ ] Basic ad-blocking (hosts file)

### Advanced Features (Future)
- [ ] Lightweight tab system
- [ ] Offline page saving
- [ ] Custom search engines
- [ ] Gesture navigation
- [ ] Voice search integration

---

## Contributing

We welcome contributions! Please feel free to:
- Report bugs and issues
- Suggest new features
- Submit pull requests
- Improve documentation
- Share feedback and ideas

---

## Author

**Shaswat Raj**  
GitHub: [@sh20raj](https://github.com/sh20raj)  
Website: [shaswat.live](https://shaswat.live)  
Twitter/X: [@sh20raj](https://x.com/sh20raj)

---

## License

MIT License – Free to fork, modify, and share.

---

**Float Browser** – Because browsing should be fast, clean, and private. 🚀