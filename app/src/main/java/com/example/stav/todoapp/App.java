package com.example.stav.todoapp;

import android.app.Application;
import android.content.Context;

import com.example.stav.todoapp.di.component.AppComponent;
import com.example.stav.todoapp.di.component.DaggerAppComponent;
import com.example.stav.todoapp.di.module.AppModule;

/**
 * Created by stav on 1/16/18.
 */

public class App extends Application {

    private AppComponent mAppComponent;

    public static App get (Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        mAppComponent.inject(this);
    }

    public AppComponent component() {
        return mAppComponent;
    }
}
