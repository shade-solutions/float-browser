# Float Browser - Build Guide

## 🛠️ Building the APK

### Prerequisites

1. **Install Android Studio**
   - Download from: https://developer.android.com/studio
   - Install Android SDK 21+ (Android 5.0+)
   - Install Android SDK 34 (Android 14) for target

2. **Install Java Development Kit**
   - Java 8 or newer required
   - Verify: `java -version`

3. **Install Git**
   - Download from: https://git-scm.com/
   - Verify: `git --version`

### Step-by-Step Build Process

#### Method 1: Using Android Studio (Recommended)

1. **Clone the Repository**
   ```bash
   git clone https://github.com/sh20raj/float-browser.git
   cd float-browser
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Click "Open an Existing Project"
   - Navigate to the `float-browser` folder
   - Click "OK"

3. **Sync Project**
   - Android Studio will automatically sync Gradle
   - Wait for "Gradle sync finished" message
   - Resolve any dependency issues if prompted

4. **Build Release APK**
   - Go to `Build` → `Generate Signed Bundle / APK`
   - Select "APK" and click "Next"
   - Create a new keystore or use existing one
   - Fill in keystore details:
     ```
     Keystore path: /path/to/your/keystore.jks
     Password: [your-password]
     Key alias: float-browser
     Key password: [your-key-password]
     ```
   - Click "Next" → Select "release" → "Finish"

5. **Locate APK**
   ```
   app/build/outputs/apk/release/app-release.apk
   ```

#### Method 2: Command Line Build

1. **Clone and Navigate**
   ```bash
   git clone https://github.com/sh20raj/float-browser.git
   cd float-browser
   ```

2. **Create Keystore (First time only)**
   ```bash
   keytool -genkey -v -keystore float-browser.jks -keyalg RSA -keysize 2048 -validity 10000 -alias float-browser
   ```

3. **Create signing.properties**
   ```bash
   # Create app/signing.properties
   storeFile=../float-browser.jks
   storePassword=your_store_password
   keyAlias=float-browser
   keyPassword=your_key_password
   ```

4. **Build Release APK**
   ```bash
   # Make gradlew executable (Linux/Mac)
   chmod +x gradlew
   
   # Build release APK
   ./gradlew assembleRelease
   
   # Windows
   gradlew.bat assembleRelease
   ```

5. **APK Location**
   ```
   app/build/outputs/apk/release/app-release.apk
   ```

### Build Optimization

#### Size Optimization (Target <1MB)

1. **Enable R8 (Already configured)**
   ```gradle
   buildTypes {
       release {
           minifyEnabled true
           shrinkResources true
           proguardFiles getDefaultProguardFile('proguard-android-optimize.txt')
       }
   }
   ```

2. **Check APK Size**
   ```bash
   # Check final APK size
   ls -lh app/build/outputs/apk/release/app-release.apk
   
   # Analyze APK contents
   ./gradlew analyzeReleaseBundle
   ```

3. **APK Analyzer (Android Studio)**
   - Go to `Build` → `Analyze APK`
   - Select your APK file
   - Review size breakdown

### Testing the APK

#### Install on Device

1. **Enable Developer Options**
   - Go to Settings → About Phone
   - Tap "Build Number" 7 times
   - Go back to Settings → Developer Options
   - Enable "USB Debugging"

2. **Install via ADB**
   ```bash
   # Install APK
   adb install app/build/outputs/apk/release/app-release.apk
   
   # Uninstall if needed
   adb uninstall com.shaswatraj.floatbrowser
   ```

3. **Install via File Manager**
   - Copy APK to device
   - Open with file manager
   - Tap to install
   - Allow "Install from Unknown Sources" if prompted

#### Performance Testing

1. **APK Size Check**
   ```bash
   # Should be under 1MB
   du -h app-release.apk
   ```

2. **Memory Usage**
   ```bash
   # Check memory usage while running
   adb shell dumpsys meminfo com.shaswatraj.floatbrowser
   ```

3. **Launch Time**
   ```bash
   # Measure cold start time
   adb shell am start -W com.shaswatraj.floatbrowser/.MainActivity
   ```

### Troubleshooting

#### Common Build Issues

1. **Gradle Sync Failed**
   ```bash
   # Clean and rebuild
   ./gradlew clean
   ./gradlew build
   ```

2. **SDK Not Found**
   - Open Android Studio
   - Go to File → Project Structure
   - Set correct SDK path

3. **Keystore Issues**
   ```bash
   # Verify keystore
   keytool -list -v -keystore float-browser.jks
   ```

4. **APK Too Large**
   - Check ProGuard rules in `proguard-rules.pro`
   - Remove unused resources
   - Use vector drawables only

#### Build Variants

1. **Debug Build** (for testing)
   ```bash
   ./gradlew assembleDebug
   ```

2. **Release Build** (for distribution)
   ```bash
   ./gradlew assembleRelease
   ```

3. **Bundle for Play Store**
   ```bash
   ./gradlew bundleRelease
   ```

### Continuous Integration

#### GitHub Actions (Optional)

Create `.github/workflows/build.yml`:
```yaml
name: Build APK
on: [push, pull_request]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
    - uses: actions/setup-java@v3
      with:
        java-version: '11'
        distribution: 'temurin'
    - name: Build APK
      run: ./gradlew assembleRelease
    - name: Upload APK
      uses: actions/upload-artifact@v3
      with:
        name: app-release
        path: app/build/outputs/apk/release/app-release.apk
```

### Final Checklist

- [ ] APK builds successfully
- [ ] APK size is under 1MB
- [ ] App installs and launches
- [ ] All features work correctly
- [ ] No crashes or major bugs
- [ ] Performance meets targets
- [ ] Signed with release keystore
- [ ] Ready for distribution

**Next Step**: See `PUBLISH_GUIDE.md` for publishing instructions!