package com.example.stav.todoapp.data.preferences;

/**
 * Created by stav on 1/20/18.
 */

public interface SharedPrefs {

    void setCurrentUserId(String userId);

    String getCurrentUserId();

    void setToken(String token);

    String getToken();

    void removeCredentials();
}
