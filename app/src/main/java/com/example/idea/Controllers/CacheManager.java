package com.example.idea.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.idea.LoginActivity;
import com.example.idea.Types.User;

public class CacheManager {

    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    public static final int PRIVATE_MODE = 0;
    // Shared pref file name
    public static final String PREF_NAME = "AndroidPref";

    // All Shared Preferences keys
    private static final String IS_LOGIN = "IsLoggedIn";
    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
    public static final String KEY_ID = "id";
    public static final String KEY_LIKED = "LIKED";
    public static final String KEY_DISLIKED = "DISLIKED";

    public User currentUser;

    // Constructor
    public CacheManager() {}
    @SuppressLint("CommitPrefEdits")
    public CacheManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * @param id String
     * @param user User
     */
    public void createLoginSession(String id, User user) {
        this.currentUser = user;
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        // Storing name in pref
        editor.putString(KEY_NAME, user.getDisplayName());
        // Storing pictures seen
        editor.putString(KEY_ID, id);
        editor.putString("uid", user.getUid());
        // commit changes
        editor.commit();
    }

    /**
     * Check login method will check user login status If false it will redirect
     * user to login page Else won't do anything
     */
    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }

    /**
     * Get stored session user data
     * @return currentUser User
     */
    public User getUser() {
        return currentUser;
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        this.currentUser = null;
        // After logout redirect user to Login Activity
        checkLogin();
    }

    /**
     * Quick check for login
     * @return boolean
     **/
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, true);
    }
}
