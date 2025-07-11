@echo off
echo 🚀 Building Float Browser APK...
echo Repository: https://github.com/shade-solutions/float-browser
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Java is not installed. Please install Java 8+ first.
    pause
    exit /b 1
)

echo ✅ Java version:
java -version
echo.

echo 🔧 Building debug APK (for testing)...
gradlew.bat assembleDebug

if %errorlevel% equ 0 (
    echo ✅ Debug APK built successfully!
    echo 📱 Location: app\build\outputs\apk\debug\app-debug.apk
    
    if exist "app\build\outputs\apk\debug\app-debug.apk" (
        for %%A in (app\build\outputs\apk\debug\app-debug.apk) do echo 📊 APK Size: %%~zA bytes
    )
    
    echo.
    echo 🎯 To install on device:
    echo adb install app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo 🔐 To build release APK:
    echo gradlew.bat assembleRelease
    
) else (
    echo ❌ Build failed. Check the error messages above.
    pause
    exit /b 1
)

pause