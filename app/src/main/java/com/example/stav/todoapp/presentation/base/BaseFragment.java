package com.example.stav.todoapp.presentation.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.stav.todoapp.App;
import com.example.stav.todoapp.di.component.ActivityComponent;
import com.example.stav.todoapp.di.component.DaggerActivityComponent;
import com.example.stav.todoapp.di.module.ActivityModule;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends android.support.v4.app.Fragment
{
    private Unbinder unbinder;
    private BaseActivity mActivity;
    private ActivityComponent mActivityComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(App.get(getActivity()).component())
                .activityModule(new ActivityModule(getActivity()))
                .build();
        inject();
        initUi();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(getFragmentLayout(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            mActivity = (BaseActivity) context;
        }
    }

    protected void start(Class<? extends BaseActivity> activity) {
        startActivity(new Intent(getActivity(), activity));
    }

    /*
     * Every fragment has to inflate a layout in the onCreateView method.
     * this method avoid duplicate inflate code in every fragment.
     */
    protected abstract int getFragmentLayout();

    /*
     * initialize the ui elements.
     */
    protected abstract void initUi();

    /*
     * inject the dependencies.
     */
    protected abstract void inject();

    /*
     * return the activity component
     * for injecting the dependencies.
     */
    public ActivityComponent getActivityComponent(){
        return mActivityComponent;
    }

    public void showSnackBar(String message) {
        if (mActivity != null){
            mActivity.showSnackBar(message);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}