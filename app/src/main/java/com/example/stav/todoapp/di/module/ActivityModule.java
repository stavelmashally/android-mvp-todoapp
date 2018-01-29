package com.example.stav.todoapp.di.module;

import android.app.Activity;

import com.example.stav.todoapp.presentation.adapters.TodoAdapter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by stav on 1/13/18.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity activity(){
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    TodoAdapter provideTodoAdapter(){
        return new TodoAdapter(mActivity);
    }

}
