package com.example.stav.todoapp.presentation.splash;

import com.example.stav.todoapp.data.UserRepository;
import com.example.stav.todoapp.data.network.model.AuthResponse;
import com.example.stav.todoapp.di.ActivityScope;
import com.example.stav.todoapp.presentation.base.Presenter;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by stav on 1/20/18.
 */

@ActivityScope
public class SplashPresenter extends Presenter<SplashContract.View>
        implements SplashContract.Presenter {

    private UserRepository mUserRepository;

    @Inject
    public SplashPresenter(CompositeDisposable compositeDisposable,
                           UserRepository userRepository) {
        super(compositeDisposable);
        mUserRepository = userRepository;
    }

    @Override
    public void authenticate() {
        getCompositeDisposable()
                .add(mUserRepository.authenticate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::authenticateSuccess, this::authenticateFailure));
    }

    private void authenticateSuccess(AuthResponse authResponse){
        getView().navigateToMainScreen();
    }

    private void authenticateFailure(Throwable error){
        getView().navigateToAuthenticationScreen();
    }
}
