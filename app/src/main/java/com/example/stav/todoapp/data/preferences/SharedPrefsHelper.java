package com.example.stav.todoapp.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.stav.todoapp.di.ApplicationContext;
import com.example.stav.todoapp.utils.Constants;

import javax.inject.Inject;

/**
 * Created by stav on 1/20/18.
 */

public class SharedPrefsHelper implements SharedPrefs{

    private static final String KEY_TOKEN = "PREFS_KEY_TOKEN";
    private static final String KEY_CURRENT_USER_ID = "PREFS_KEY_USER_ID";

    private SharedPreferences mPreferences;

    @Inject
    public SharedPrefsHelper(@ApplicationContext Context context) {
        mPreferences = context.getSharedPreferences(
                Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void setCurrentUserId(String userId) {
        mPreferences.edit().putString(KEY_CURRENT_USER_ID, userId).apply();
    }

    @Override
    public String getCurrentUserId() {
        return mPreferences.getString(KEY_CURRENT_USER_ID, null);
    }

    @Override
    public String getToken(){
        return mPreferences.getString(KEY_TOKEN, null);
    }

    @Override
    public void setToken(String token){
        mPreferences.edit().putString(KEY_TOKEN, token).apply();
    }

    @Override
    public void removeCredentials() {
        mPreferences.edit()
                .remove(KEY_TOKEN)
                .remove(KEY_CURRENT_USER_ID)
                .apply();
    }
}
