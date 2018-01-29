package com.example.stav.todoapp.data.repositories.remote;

import com.example.stav.todoapp.data.network.model.TodoRequest;
import com.example.stav.todoapp.data.network.services.TodoService;
import com.example.stav.todoapp.data.model.Todo;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by stav on 1/9/18.
 */

public class TodoRemoteData {

    private TodoService mTodoService;

    @Inject
    TodoRemoteData (TodoService todoService) {
        mTodoService = todoService;
    }

    public Observable<List<Todo>> getAllTodos() {
        return mTodoService.getTodos();
    }

    public Completable saveTodo(TodoRequest todoRequest){
        return mTodoService.saveTodo(todoRequest);
    }

    public Completable updateTodoById(String id){
        return mTodoService.updateTodoById(id);
    }

    public Completable removeTodoById(String id){
        return mTodoService.removeTodoById(id);
    }
}
