package com.example.stav.todoapp.data;


import com.example.stav.todoapp.data.network.model.LoginRequest;
import com.example.stav.todoapp.data.network.model.AuthResponse;
import com.example.stav.todoapp.data.network.model.RegisterRequest;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by stav on 1/20/18.
 */

public interface UserRepository {

    Observable<AuthResponse> authenticate();

    Observable<AuthResponse> login(LoginRequest loginRequest);

    Observable<AuthResponse> registerUser(RegisterRequest registerRequest);

    Completable logout();
}
