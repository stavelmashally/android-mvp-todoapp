package com.example.stav.todoapp.presentation.authentication;

import android.app.ProgressDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;

import com.example.stav.todoapp.R;
import com.example.stav.todoapp.presentation.base.BaseFragment;
import com.example.stav.todoapp.presentation.main.MainActivity;
import com.example.stav.todoapp.utils.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by stav on 1/21/18.
 */

public class LoginFragment extends BaseFragment implements LoginContract.View {

    @BindView(R.id.til_email)
    TextInputLayout tilEmail;

    @BindView(R.id.til_password)
    TextInputLayout tilPassword;

    @Inject
    LoginPresenter mLoginPresenter;

    private ProgressDialog mProgressDialog;
    private FragmentManager mFragmentManager;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initUi() {
        mFragmentManager = getFragmentManager();
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(getResources().getString(R.string.signin_loading));
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onResume() {
        mLoginPresenter.attachView(this);
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLoginPresenter.detachView();
    }

    @OnClick(R.id.login_btn)
    public void loginClick(){
        String email = tilEmail.getEditText().getText().toString();
        String password = tilPassword.getEditText().getText().toString();
        mLoginPresenter.login(email, password);
    }

    @OnClick(R.id.create_account)
    public void createAccountClick(){
        // show the register screen
        mFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                .replace(R.id.frame_container, new RegisterFragment(), Constants.REGISTER_FRAGMENT)
                .commit();
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mProgressDialog.dismiss();
    }

    @Override
    public void loginFailure(String error) {
        showSnackBar(error);
    }

    @Override
    public void setErrorEmailField() {
        tilEmail.setError(getString(R.string.invalid_email));
    }

    @Override
    public void setErrorPasswordField() {
        tilPassword.setError(getString(R.string.invalid_password));
    }

    @Override
    public void navigateToMainScreen() {
        start(MainActivity.class);
        getActivity().finish();
    }
}
