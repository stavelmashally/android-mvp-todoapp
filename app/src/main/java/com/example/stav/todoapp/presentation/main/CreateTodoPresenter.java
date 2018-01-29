package com.example.stav.todoapp.presentation.main;

import com.example.stav.todoapp.data.TodoRepository;
import com.example.stav.todoapp.data.network.model.TodoRequest;
import com.example.stav.todoapp.di.ActivityScope;
import com.example.stav.todoapp.presentation.base.Presenter;
import com.example.stav.todoapp.utils.ValidationUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by stav on 1/19/18.
 */

@ActivityScope
public class CreateTodoPresenter extends Presenter<CreateTodoContract.View>
        implements CreateTodoContract.Presenter{

    private TodoRepository mTodoRepository;

    @Inject
    public CreateTodoPresenter(TodoRepository todoRepository,
                               CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
        mTodoRepository = todoRepository;
    }

    @Override
    public void saveTodo(String text) {
        if (!ValidationUtils.isValidText(text)){
            getView().setErrorTextField();
            return;
        }
        getView().showLoading();
        getCompositeDisposable()
                .add(mTodoRepository.saveTodo(new TodoRequest(text))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::saveTodoSuccess, this::saveTodoFailure));
    }

    private void saveTodoSuccess(){
        getView().hideLoading();
        getView().navigateToMainScreen();
    }

    private void saveTodoFailure(Throwable error){
        error.printStackTrace();
        getView().hideLoading();
        getView().saveTodoFailure();
    }
}
