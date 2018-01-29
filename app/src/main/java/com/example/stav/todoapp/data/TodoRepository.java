package com.example.stav.todoapp.data;

import com.example.stav.todoapp.data.network.model.TodoRequest;
import com.example.stav.todoapp.data.model.Todo;

import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by stav on 1/20/18.
 */

public interface TodoRepository {

    Completable removeTodoById(String id);

    Completable updateTodoById(String id);

    Observable<List<Todo>> getAllTodos();

    Completable saveTodo(TodoRequest todoRequest);

}
