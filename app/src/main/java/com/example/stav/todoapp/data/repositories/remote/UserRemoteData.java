package com.example.stav.todoapp.data.repositories.remote;

import com.example.stav.todoapp.data.network.model.LoginRequest;
import com.example.stav.todoapp.data.network.model.AuthResponse;
import com.example.stav.todoapp.data.network.model.RegisterRequest;
import com.example.stav.todoapp.data.network.services.UserService;
import javax.inject.Inject;
import io.reactivex.Observable;

/**
 * Created by stav on 1/9/18.
 */

public class UserRemoteData {

    private UserService mUserService;

    @Inject
    UserRemoteData(UserService userService) {
        this.mUserService = userService;
    }

    public Observable<AuthResponse> authenticate(){
        return mUserService.authenticate();
    }

    public Observable<AuthResponse> login(LoginRequest loginRequest){
        return mUserService.login(loginRequest);
    }

    public Observable<AuthResponse> register(RegisterRequest registerRequest){
        return mUserService.registerUser(registerRequest);
    }

}
