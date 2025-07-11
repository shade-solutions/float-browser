#!/bin/bash

echo "🚀 Building Float Browser APK..."
echo "Repository: https://github.com/shade-solutions/float-browser"
echo ""

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 8+ first."
    exit 1
fi

echo "✅ Java version:"
java -version
echo ""

# Make gradlew executable
chmod +x gradlew

echo "🔧 Building debug APK (for testing)..."
./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo "✅ Debug APK built successfully!"
    echo "📱 Location: app/build/outputs/apk/debug/app-debug.apk"
    
    # Check APK size
    if [ -f "app/build/outputs/apk/debug/app-debug.apk" ]; then
        SIZE=$(du -h app/build/outputs/apk/debug/app-debug.apk | cut -f1)
        echo "📊 APK Size: $SIZE"
    fi
    
    echo ""
    echo "🎯 To install on device:"
    echo "adb install app/build/outputs/apk/debug/app-debug.apk"
    echo ""
    echo "🔐 To build release APK:"
    echo "./gradlew assembleRelease"
    
else
    echo "❌ Build failed. Check the error messages above."
    exit 1
fi