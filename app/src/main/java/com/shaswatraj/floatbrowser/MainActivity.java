package com.shaswatraj.floatbrowser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity {
    
    private WebView webView;
    private EditText addressBar;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageButton backButton, forwardButton, refreshButton, menuButton;
    
    private SharedPreferences preferences;
    private boolean isIncognitoMode = false;
    private boolean isJavaScriptEnabled = true;
    private boolean isDarkTheme = false;
    
    private static final String PREF_NAME = "FloatBrowserPrefs";
    private static final String PREF_JAVASCRIPT = "javascript_enabled";
    private static final String PREF_DARK_THEME = "dark_theme";
    private static final String PREF_HOMEPAGE = "homepage";
    private static final String DEFAULT_HOMEPAGE = "https://www.google.com";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Initialize preferences
        preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        loadPreferences();
        
        // Apply theme before setContentView
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        
        setContentView(R.layout.activity_main);
        
        initializeViews();
        setupWebView();
        setupAddressBar();
        setupNavigationButtons();
        setupSwipeRefresh();
        
        // Handle intent if app was opened with a URL
        handleIntent(getIntent());
        
        // Load homepage if no URL provided
        if (webView.getUrl() == null) {
            String homepage = preferences.getString(PREF_HOMEPAGE, DEFAULT_HOMEPAGE);
            webView.loadUrl(homepage);
        }
    }
    
    private void initializeViews() {
        webView = findViewById(R.id.webview);
        addressBar = findViewById(R.id.address_bar);
        progressBar = findViewById(R.id.progress_bar);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        backButton = findViewById(R.id.btn_back);
        forwardButton = findViewById(R.id.btn_forward);
        refreshButton = findViewById(R.id.btn_refresh);
        menuButton = findViewById(R.id.btn_menu);
    }
    
    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
        WebSettings settings = webView.getSettings();
        
        // Basic settings
        settings.setJavaScriptEnabled(isJavaScriptEnabled);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(true);
        settings.setDefaultTextEncodingName("utf-8");
        
        // Performance optimizations
        settings.setCacheMode(isIncognitoMode ? WebSettings.LOAD_NO_CACHE : WebSettings.LOAD_DEFAULT);
        settings.setAppCacheEnabled(!isIncognitoMode);
        settings.setDatabaseEnabled(!isIncognitoMode);
        
        // Security settings
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        
        // User agent for better compatibility
        settings.setUserAgentString(settings.getUserAgentString() + " FloatBrowser/1.0");
        
        webView.setWebViewClient(new FloatWebViewClient());
        webView.setWebChromeClient(new FloatWebChromeClient());
    }
    
    private void setupAddressBar() {
        addressBar.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO || 
                (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                String url = addressBar.getText().toString().trim();
                loadUrl(url);
                return true;
            }
            return false;
        });
    }
    
    private void setupNavigationButtons() {
        backButton.setOnClickListener(v -> {
            if (webView.canGoBack()) {
                webView.goBack();
            }
        });
        
        forwardButton.setOnClickListener(v -> {
            if (webView.canGoForward()) {
                webView.goForward();
            }
        });
        
        refreshButton.setOnClickListener(v -> webView.reload());
        
        menuButton.setOnClickListener(v -> openOptionsMenu());
    }
    
    private void setupSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            webView.reload();
        });
        
        // Redmi-optimized colors
        swipeRefreshLayout.setColorSchemeResources(
            R.color.redmi_orange,
            R.color.redmi_blue,
            R.color.redmi_green
        );
    }
    
    private void loadUrl(String url) {
        if (url.isEmpty()) return;
        
        // Add protocol if missing
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            if (url.contains(".") && !url.contains(" ")) {
                url = "https://" + url;
            } else {
                // Search query
                url = "https://www.google.com/search?q=" + Uri.encode(url);
            }
        }
        
        webView.loadUrl(url);
        hideKeyboard();
    }
    
    private void hideKeyboard() {
        WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        controller.hide(WindowInsetsCompat.Type.ime());
    }
    
    private void updateNavigationButtons() {
        backButton.setEnabled(webView.canGoBack());
        forwardButton.setEnabled(webView.canGoForward());
        
        // Update button alpha for visual feedback
        backButton.setAlpha(webView.canGoBack() ? 1.0f : 0.5f);
        forwardButton.setAlpha(webView.canGoForward() ? 1.0f : 0.5f);
    }
    
    private void loadPreferences() {
        isJavaScriptEnabled = preferences.getBoolean(PREF_JAVASCRIPT, true);
        isDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);
    }
    
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri uri = intent.getData();
            if (uri != null) {
                webView.loadUrl(uri.toString());
            }
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        
        // Update menu items based on current state
        MenuItem incognitoItem = menu.findItem(R.id.action_incognito);
        incognitoItem.setTitle(isIncognitoMode ? "Exit Incognito" : "Incognito Mode");
        
        MenuItem jsItem = menu.findItem(R.id.action_javascript);
        jsItem.setTitle(isJavaScriptEnabled ? "Disable JavaScript" : "Enable JavaScript");
        
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.action_incognito) {
            toggleIncognitoMode();
            return true;
        } else if (id == R.id.action_javascript) {
            toggleJavaScript();
            return true;
        } else if (id == R.id.action_clear_cache) {
            clearCache();
            return true;
        } else if (id == R.id.action_view_source) {
            viewSource();
            return true;
        } else if (id == R.id.action_settings) {
            openSettings();
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    private void toggleIncognitoMode() {
        isIncognitoMode = !isIncognitoMode;
        
        if (isIncognitoMode) {
            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            webView.getSettings().setAppCacheEnabled(false);
            webView.getSettings().setDatabaseEnabled(false);
            Toast.makeText(this, "Incognito mode enabled", Toast.LENGTH_SHORT).show();
        } else {
            webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            webView.getSettings().setAppCacheEnabled(true);
            webView.getSettings().setDatabaseEnabled(true);
            Toast.makeText(this, "Incognito mode disabled", Toast.LENGTH_SHORT).show();
        }
        
        invalidateOptionsMenu();
    }
    
    @SuppressLint("SetJavaScriptEnabled")
    private void toggleJavaScript() {
        isJavaScriptEnabled = !isJavaScriptEnabled;
        webView.getSettings().setJavaScriptEnabled(isJavaScriptEnabled);
        
        preferences.edit()
                .putBoolean(PREF_JAVASCRIPT, isJavaScriptEnabled)
                .apply();
        
        Toast.makeText(this, 
                isJavaScriptEnabled ? "JavaScript enabled" : "JavaScript disabled", 
                Toast.LENGTH_SHORT).show();
        
        invalidateOptionsMenu();
        webView.reload();
    }
    
    private void clearCache() {
        webView.clearCache(true);
        webView.clearHistory();
        webView.clearFormData();
        Toast.makeText(this, "Cache cleared", Toast.LENGTH_SHORT).show();
    }
    
    private void viewSource() {
        String currentUrl = webView.getUrl();
        if (currentUrl != null) {
            webView.loadUrl("view-source:" + currentUrl);
        }
    }
    
    private void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
    
    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }
    
    // Custom WebViewClient
    private class FloatWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
            addressBar.setText(url);
        }
        
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
            updateNavigationButtons();
        }
        
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    
    // Custom WebChromeClient
    private class FloatWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            progressBar.setProgress(newProgress);
            
            if (newProgress == 100) {
                progressBar.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.VISIBLE);
            }
        }
        
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            // Could update title in address bar or action bar
        }
    }
}