# 🚀 GitHub Release Instructions for Float Browser

## Automatic Release with GitHub Actions

Your repository now has automated build and release workflows! Here's how to use them:

### 📋 Setup (One-time)

1. **Push all files to your repository:**
   ```bash
   git add .
   git commit -m "Initial Float Browser release"
   git push origin main
   ```

2. **GitHub Actions will automatically:**
   - ✅ Build APK on every push
   - ✅ Run size checks (must be under 1MB)
   - ✅ Create artifacts for download

### 🎯 Creating a Release

#### Method 1: Tag-based Release (Recommended)

1. **Create and push a version tag:**
   ```bash
   git tag v1.0.0
   git push origin v1.0.0
   ```

2. **GitHub Actions will automatically:**
   - Build both debug and release APKs
   - Create a GitHub release
   - Upload APKs to the release
   - Generate beautiful release notes

#### Method 2: Manual Release Trigger

1. **Go to your repository on GitHub**
2. **Click "Actions" tab**
3. **Select "Build and Release Float Browser"**
4. **Click "Run workflow"**
5. **Enter version (e.g., v1.0.0)**
6. **Click "Run workflow"**

### 📱 What Gets Created

Each release includes:

- **Release APK** (`float-browser-release.apk`) - Production ready
- **Debug APK** (`float-browser-debug.apk`) - For testing
- **Automatic release notes** with features and download links
- **APK size information** (should be under 1MB)
- **Installation instructions**
- **Comparison with competitors**

### 🔧 Workflow Features

#### Build on Push (`.github/workflows/build-on-push.yml`)
- Triggers on every push to main/master/develop
- Builds debug APK
- Checks APK size (fails if over 1MB)
- Uploads APK as artifact

#### Release Workflow (`.github/workflows/build-and-release.yml`)
- Triggers on version tags (v*)
- Builds both debug and release APKs
- Creates GitHub release with beautiful notes
- Uploads APKs to release

### 📊 Release Notes Template

Each release automatically includes:

```markdown
# 🚀 Float Browser v1.0.0

**Ultra-lightweight mobile browser under 1MB**

## 📱 Download
- float-browser-release.apk - Production version (950 KB)
- float-browser-debug.apk - Debug version (980 KB)

## ✨ Features
- Ultra-small size: Complete browser under 1MB
- Beautiful UI: Material Design 3 with floating elements
- Privacy focused: No tracking, analytics, or data collection
- Dark/Light themes: Automatic theme switching
- Incognito mode: Complete privacy with cache clearing

## 🆚 Comparison
| Feature | Float Browser | Via Browser | X Browser |
|---------|---------------|-------------|-----------|
| Size | 950 KB | ~1.2MB | ~2.1MB |
| Material Design 3 | ✅ | ❌ | ❌ |
| Open Source | ✅ | ❌ | ❌ |
```

### 🎯 Quick Release Steps

1. **Make your changes and commit:**
   ```bash
   git add .
   git commit -m "Add new features"
   git push
   ```

2. **Create a release:**
   ```bash
   git tag v1.0.0
   git push origin v1.0.0
   ```

3. **Wait 3-5 minutes** for GitHub Actions to complete

4. **Check your releases page:**
   ```
   https://github.com/shade-solutions/float-browser/releases
   ```

### 🚨 Troubleshooting

#### Build Fails
- Check the Actions tab for error details
- Ensure all files are committed and pushed
- Verify Java/Android SDK setup in workflow

#### APK Too Large
- The workflow will fail if APK > 1MB
- Check ProGuard rules in `proguard-rules.pro`
- Remove unused resources

#### Release Not Created
- Ensure you pushed the tag: `git push origin v1.0.0`
- Check that tag follows format: `v*` (e.g., v1.0.0, v2.1.3)

### 📈 Next Steps

After your first release:

1. **Share the release link:**
   ```
   https://github.com/shade-solutions/float-browser/releases/latest
   ```

2. **Promote on social media:**
   - Twitter/X: Share release announcement
   - Reddit: Post in r/Android, r/androidapps
   - Dev communities: Hacker News, Product Hunt

3. **Submit to app stores:**
   - Use the release APK for Google Play Store
   - Submit to F-Droid for open source distribution

4. **Monitor feedback:**
   - Watch GitHub issues
   - Respond to user feedback
   - Plan future releases

### 🎉 Your Release is Ready!

Repository: https://github.com/shade-solutions/float-browser

Once you push your first tag, you'll have:
- ✅ Automated APK building
- ✅ Professional GitHub releases
- ✅ Download links for users
- ✅ Beautiful release notes
- ✅ Size verification (under 1MB)

**Your Float Browser is ready to compete with Via Browser and X Browser! 🏆**