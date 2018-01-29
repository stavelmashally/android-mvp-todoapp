package com.example.stav.todoapp.presentation.splash;

import com.example.stav.todoapp.presentation.base.BaseView;

/**
 * Created by stav on 1/20/18.
 */

public interface SplashContract {

    interface View extends BaseView{

        void navigateToMainScreen();

        void navigateToAuthenticationScreen();
    }

    interface Presenter{

        void authenticate();
    }
}
