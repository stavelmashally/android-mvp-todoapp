package com.example.stav.todoapp.data.repositories;

import com.example.stav.todoapp.data.TodoRepository;
import com.example.stav.todoapp.data.network.model.TodoRequest;
import com.example.stav.todoapp.data.repositories.local.TodoLocalData;
import com.example.stav.todoapp.data.repositories.remote.TodoRemoteData;
import com.example.stav.todoapp.data.model.Todo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;


/**
 * Created by stav on 1/9/18.
 */

public class TodoDataRepository implements TodoRepository {

    private TodoLocalData mTodoLocalData;
    private TodoRemoteData mTodoRemoteData;

    @Inject
    TodoDataRepository(TodoLocalData todoLocalData, TodoRemoteData todoRemoteData){
        mTodoLocalData = todoLocalData;
        mTodoRemoteData = todoRemoteData;
    }

    @Override
    public Observable<List<Todo>> getAllTodos() {
        return mTodoRemoteData.getAllTodos();
    }

    @Override
    public Completable saveTodo(TodoRequest todoRequest) {
        return mTodoRemoteData.saveTodo(todoRequest);
    }

    @Override
    public Completable removeTodoById(String id) {
        return mTodoRemoteData.removeTodoById(id);
    }

    @Override
    public Completable updateTodoById(String id) {
        return mTodoRemoteData.updateTodoById(id);
    }
}
