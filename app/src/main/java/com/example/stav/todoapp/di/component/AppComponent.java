package com.example.stav.todoapp.di.component;

import android.content.Context;

import com.example.stav.todoapp.App;
import com.example.stav.todoapp.data.TodoRepository;
import com.example.stav.todoapp.data.UserRepository;
import com.example.stav.todoapp.data.network.services.TodoService;
import com.example.stav.todoapp.data.preferences.SharedPrefs;
import com.example.stav.todoapp.di.ApplicationContext;
import com.example.stav.todoapp.di.module.AppModule;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by stav on 1/3/18.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(App App);

    @ApplicationContext
    Context context();

    Gson gson();

    Retrofit retrofit();

    SharedPrefs sharedPrefs();

    TodoService todoService();

    TodoRepository todoRepository();

    UserRepository userRepository();
}
