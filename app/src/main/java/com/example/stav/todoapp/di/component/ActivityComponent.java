package com.example.stav.todoapp.di.component;

import com.example.stav.todoapp.di.module.ActivityModule;
import com.example.stav.todoapp.di.ActivityScope;
import com.example.stav.todoapp.presentation.authentication.LoginFragment;
import com.example.stav.todoapp.presentation.authentication.RegisterFragment;
import com.example.stav.todoapp.presentation.main.CreateTodoFragment;
import com.example.stav.todoapp.presentation.main.MainActivity;
import com.example.stav.todoapp.presentation.splash.SplashActivity;

import dagger.Component;

/**
 * Created by stav on 1/13/18.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(CreateTodoFragment createTodoFragment);
    void inject(SplashActivity splashActivity);
    void inject(LoginFragment loginFragment);
    void inject(RegisterFragment registerFragment);
}
