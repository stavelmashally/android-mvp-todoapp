package com.example.stav.todoapp.data.repositories.local;

import com.example.stav.todoapp.data.preferences.SharedPrefs;
import javax.inject.Inject;

/**
 * Created by stav on 1/9/18.
 */

public class TodoLocalData {

    private SharedPrefs mSharedPrefs;

    @Inject
    public TodoLocalData(SharedPrefs sharedPrefs) {
        mSharedPrefs = sharedPrefs;
    }
}
