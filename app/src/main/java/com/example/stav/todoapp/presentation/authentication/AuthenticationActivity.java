package com.example.stav.todoapp.presentation.authentication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import com.example.stav.todoapp.R;
import com.example.stav.todoapp.presentation.base.BaseActivity;
import com.example.stav.todoapp.utils.Constants;

public class AuthenticationActivity extends BaseActivity {

    private static FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // show the login fragment first
        if (savedInstanceState == null) {
            replaceToLoginFragment();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_authentication;
    }

    @Override
    protected void inject() {
    }

    @Override
    protected void initUi() {
        mFragmentManager = getSupportFragmentManager();
    }

    protected void replaceToLoginFragment() {
        mFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frame_container, new LoginFragment(), Constants.LOGIN_FRAGMENT)
                .commit();
    }

    @Override
    public void onBackPressed() {
        // Find the tag of register fragment
        Fragment registerFragment =
                mFragmentManager.findFragmentByTag(Constants.REGISTER_FRAGMENT);

        // If not null replace to login fragment
        if (registerFragment != null) {
            replaceToLoginFragment();
        } else {
            super.onBackPressed();
        }
    }
}
