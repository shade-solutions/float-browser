package com.shaswatraj.floatbrowser;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivity extends AppCompatActivity {
    
    private SharedPreferences preferences;
    private Switch darkThemeSwitch;
    private Switch javascriptSwitch;
    
    private static final String PREF_NAME = "FloatBrowserPrefs";
    private static final String PREF_JAVASCRIPT = "javascript_enabled";
    private static final String PREF_DARK_THEME = "dark_theme";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        
        // Setup action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Settings");
        }
        
        preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        
        initializeViews();
        loadCurrentSettings();
        setupSwitchListeners();
    }
    
    private void initializeViews() {
        darkThemeSwitch = findViewById(R.id.switch_dark_theme);
        javascriptSwitch = findViewById(R.id.switch_javascript);
    }
    
    private void loadCurrentSettings() {
        boolean isDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);
        boolean isJavaScriptEnabled = preferences.getBoolean(PREF_JAVASCRIPT, true);
        
        darkThemeSwitch.setChecked(isDarkTheme);
        javascriptSwitch.setChecked(isJavaScriptEnabled);
    }
    
    private void setupSwitchListeners() {
        darkThemeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferences.edit()
                    .putBoolean(PREF_DARK_THEME, isChecked)
                    .apply();
            
            // Apply theme change
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            
            Toast.makeText(this, "Theme will be applied on restart", Toast.LENGTH_SHORT).show();
        });
        
        javascriptSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferences.edit()
                    .putBoolean(PREF_JAVASCRIPT, isChecked)
                    .apply();
            
            Toast.makeText(this, 
                    isChecked ? "JavaScript enabled" : "JavaScript disabled", 
                    Toast.LENGTH_SHORT).show();
        });
    }
    
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}