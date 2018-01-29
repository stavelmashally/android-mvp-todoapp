package com.example.stav.todoapp.presentation.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.stav.todoapp.App;
import com.example.stav.todoapp.R;
import com.example.stav.todoapp.di.component.ActivityComponent;
import com.example.stav.todoapp.di.component.DaggerActivityComponent;
import com.example.stav.todoapp.di.module.ActivityModule;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by stav on 1/16/18.
 */

public abstract class BaseActivity extends AppCompatActivity{

    protected String TAG = getClass().getSimpleName();
    private ActivityComponent mActivityComponent;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnbinder = ButterKnife.bind(this);
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(App.get(this).component())
                .activityModule(new ActivityModule(this))
                .build();
        inject();
        initUi();
    }

    /*
     * get the activity layout.
     */
    protected abstract @LayoutRes int getLayout();

    /*
     * inject the dependencies.
     */
    protected abstract void inject();

    /*
     * initialize the ui elements.
     */
    protected abstract void initUi();

    /*
     * return the activity component
     * for injecting the dependencies.
     */
    public ActivityComponent getActivityComponent(){
        return mActivityComponent;
    }

    /*
     * for starting a new activity.
     */
    protected void start(Class<? extends BaseActivity> activity) {
        startActivity(new Intent(this, activity));
    }

    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        snackbar.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}