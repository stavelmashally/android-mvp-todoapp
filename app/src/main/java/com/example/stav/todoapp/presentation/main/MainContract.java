package com.example.stav.todoapp.presentation.main;

import com.example.stav.todoapp.data.model.Todo;
import com.example.stav.todoapp.presentation.base.BaseView;

import java.util.List;

/**
 * Created by stav on 1/16/18.
 */

public interface MainContract {

    interface View extends BaseView{

        void showTodos(List<Todo> todoList);

        void getTodosFailure(String error);

        void updateTodoSuccess();

        void updateTodoFailure(String message);

        void removeTodoSuccess();

        void removeTodoFailure(String message);

        void showEmpty();

        void navigateToAuthenticationScreen();
    }

    interface Presenter{

        void getTodos();

        void updateTodo(String id);

        void removeTodo(String id);

        void logout();
    }
}
