package com.example.stav.todoapp.presentation.authentication;

import com.example.stav.todoapp.data.UserRepository;
import com.example.stav.todoapp.data.network.model.LoginRequest;
import com.example.stav.todoapp.data.network.model.AuthResponse;
import com.example.stav.todoapp.data.network.model.RegisterRequest;
import com.example.stav.todoapp.di.ActivityScope;
import com.example.stav.todoapp.presentation.base.Presenter;
import com.example.stav.todoapp.utils.ErrorUtils;
import com.example.stav.todoapp.utils.ValidationUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by stav on 1/22/18.
 */

@ActivityScope
public class RegisterPresenter extends Presenter<RegisterContract.View>
        implements RegisterContract.Presenter {

    private UserRepository mUserRepository;

    @Inject
    public RegisterPresenter(CompositeDisposable compositeDisposable,
                             UserRepository userRepository) {
        super(compositeDisposable);
        mUserRepository = userRepository;
    }

    @Override
    public void register(String email, String password, String confirmPassword, String userName) {

        if (ValidationUtils.isNullOrEmpty(email, password)) {
            return;
        }
        if (!ValidationUtils.isValidEmail(email)) {
            getView().setErrorEmailField();
            return;
        }
        if (!ValidationUtils.isValidPassword(password)) {
            getView().setErrorPasswordField();
            return;
        }

        if (!password.equals(confirmPassword)){
            getView().setErrorConfirmPasswordField();
            return;
        }

        getView().showLoading();
        getCompositeDisposable()
                .add(mUserRepository.registerUser(new RegisterRequest(email, password, userName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::registerSuccess, this::registerFailure));
    }

    private void registerSuccess(AuthResponse authResponse){
        getView().hideLoading();
        getView().navigateToMainScreen();
    }

    private void registerFailure(Throwable error){
        getView().hideLoading();
        getView().registerFailure(ErrorUtils.getErrorMessage(error));
        error.printStackTrace();
    }
}
