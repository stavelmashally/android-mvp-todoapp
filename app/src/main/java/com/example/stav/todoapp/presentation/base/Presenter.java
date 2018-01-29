package com.example.stav.todoapp.presentation.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by stav on 1/16/18.
 */

public abstract class Presenter<T extends BaseView> {

    private T mView;
    private CompositeDisposable mCompositeDisposable;
    
    public Presenter(CompositeDisposable compositeDisposable) {
        mCompositeDisposable = compositeDisposable;
    }

    public T getView() {
        return mView;
    }

    public CompositeDisposable getCompositeDisposable(){
        return mCompositeDisposable;
    }

    public void attachView(T view){
        mView = view;
    }

    public void detachView(){
        mView = null;
        mCompositeDisposable.dispose();
    }
}
