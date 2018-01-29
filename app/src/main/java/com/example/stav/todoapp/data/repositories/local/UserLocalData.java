package com.example.stav.todoapp.data.repositories.local;


import com.example.stav.todoapp.data.preferences.SharedPrefs;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;

/**
 * Created by stav on 1/9/18.
 */

public class UserLocalData {

    private SharedPrefs mSharedPrefs;

    @Inject
    public UserLocalData(SharedPrefs sharedPrefs) {
        mSharedPrefs = sharedPrefs;
    }

    public void setCurrentUserId(String userId){
        mSharedPrefs.setCurrentUserId(userId);
    }

    public String getCurrenrUserId(){
        return mSharedPrefs.getCurrentUserId();
    }

    public String getToken(){
        return mSharedPrefs.getToken();
    }

    public void setToken(String token){
        mSharedPrefs.setToken(token);
    }

    public Completable logout(){
        mSharedPrefs.removeCredentials();
        return Completable.complete();
    }
}
