package com.example.stav.todoapp.presentation.authentication;

import android.app.ProgressDialog;
import android.support.design.widget.TextInputLayout;

import com.example.stav.todoapp.R;
import com.example.stav.todoapp.presentation.base.BaseFragment;
import com.example.stav.todoapp.presentation.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by stav on 1/21/18.
 */

public class RegisterFragment extends BaseFragment implements RegisterContract.View {

    @BindView(R.id.layout_input_email)
    TextInputLayout tilEmail;

    @BindView(R.id.layout_input_username)
    TextInputLayout tilUserName;

    @BindView(R.id.layout_input_password)
    TextInputLayout tilPassword;

    @BindView(R.id.layout_input_confirm_password)
    TextInputLayout tilConfirmPassword;

    @Inject
    RegisterPresenter mRegisterPresenter;

    private ProgressDialog mProgressDialog;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initUi() {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(getResources().getString(R.string.signup_loading));
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mRegisterPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRegisterPresenter.detachView();
    }

    @OnClick(R.id.btn_sign_up)
    public void signUpClick(){
        String email = tilEmail.getEditText().getText().toString();
        String userName = tilUserName.getEditText().getText().toString();
        String password = tilPassword.getEditText().getText().toString();
        String confirmPassword = tilConfirmPassword.getEditText().getText().toString();
        mRegisterPresenter.register(email, password, confirmPassword, userName);
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
    public void registerFailure(String error) {
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
    public void setErrorConfirmPasswordField() {
        tilConfirmPassword.setError(getString(R.string.doesnt_match));
    }

    @Override
    public void navigateToMainScreen() {
        start(MainActivity.class);
        getActivity().finish();
    }

}
