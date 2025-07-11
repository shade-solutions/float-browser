# 🚀 Quick Build Guide for Float Browser

## Repository: https://github.com/shade-solutions/float-browser

### 📋 Prerequisites

1. **Java 8+** installed
   ```bash
   java -version
   ```

2. **Android SDK** (via Android Studio or command line tools)
   - Download: https://developer.android.com/studio

### 🛠️ Build Steps

#### Method 1: Using Android Studio (Recommended)

1. **Clone Repository**
   ```bash
   git clone https://github.com/shade-solutions/float-browser.git
   cd float-browser
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - File → Open → Select `float-browser` folder
   - Wait for Gradle sync

3. **Build APK**
   - Build → Generate Signed Bundle / APK
   - Select "APK" → Next
   - Create keystore or use existing
   - Build release APK

#### Method 2: Command Line (Quick)

1. **Clone and Build**
   ```bash
   # Clone repository
   git clone https://github.com/shade-solutions/float-browser.git
   cd float-browser
   
   # Make gradlew executable (Linux/Mac)
   chmod +x gradlew
   
   # Build debug APK (for testing)
   ./gradlew assembleDebug
   
   # Build release APK (for distribution)
   ./gradlew assembleRelease
   ```

2. **Windows Users**
   ```cmd
   git clone https://github.com/shade-solutions/float-browser.git
   cd float-browser
   gradlew.bat assembleRelease
   ```

### 📱 APK Location

After successful build:
```
Debug APK: app/build/outputs/apk/debug/app-debug.apk
Release APK: app/build/outputs/apk/release/app-release.apk
```

### 🔧 For Release APK (Signed)

1. **Create Keystore** (first time only)
   ```bash
   keytool -genkey -v -keystore float-browser.jks -keyalg RSA -keysize 2048 -validity 10000 -alias float-browser
   ```

2. **Create signing.properties**
   ```properties
   # app/signing.properties
   storeFile=../float-browser.jks
   storePassword=your_password
   keyAlias=float-browser
   keyPassword=your_key_password
   ```

3. **Build Signed APK**
   ```bash
   ./gradlew assembleRelease
   ```

### 📊 Expected Results

- **APK Size**: ~950KB (under 1MB ✅)
- **Build Time**: 30-60 seconds
- **Target**: Android 5.0+ (API 21+)

### 🧪 Test Your APK

1. **Install on Device**
   ```bash
   # Enable USB debugging on device
   adb install app/build/outputs/apk/release/app-release.apk
   ```

2. **Check APK Size**
   ```bash
   ls -lh app/build/outputs/apk/release/app-release.apk
   ```

### 🚨 Troubleshooting

#### Common Issues:

1. **Gradle Sync Failed**
   ```bash
   ./gradlew clean
   ./gradlew build
   ```

2. **SDK Not Found**
   - Set ANDROID_HOME environment variable
   - Or install Android Studio

3. **Permission Denied (Linux/Mac)**
   ```bash
   chmod +x gradlew
   ```

4. **Java Version Issues**
   - Use Java 8 or 11
   - Set JAVA_HOME correctly

### ✅ Success Checklist

- [ ] Repository cloned successfully
- [ ] Gradle sync completed
- [ ] APK builds without errors
- [ ] APK size is under 1MB
- [ ] APK installs and runs on device

### 🎯 Next Steps

1. **Test the browser** on your device
2. **Upload to GitHub releases** for distribution
3. **Submit to Google Play Store** (see PUBLISH_GUIDE.md)
4. **Share with users** and get feedback

**Your Float Browser APK is ready! 🎉**

Repository: https://github.com/shade-solutions/float-browser