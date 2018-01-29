package com.example.stav.todoapp.presentation.main;

import com.example.stav.todoapp.presentation.base.BaseView;

/**
 * Created by stav on 1/19/18.
 */

public interface CreateTodoContract {

    interface View extends BaseView{

        void setErrorTextField();

        void saveTodoFailure();

        void navigateToMainScreen();
    }

    interface Presenter{

        void saveTodo(String text);
    }
}
