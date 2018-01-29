package com.example.stav.todoapp.data.network.services;

import com.example.stav.todoapp.data.network.model.LoginRequest;
import com.example.stav.todoapp.data.network.model.AuthResponse;
import com.example.stav.todoapp.data.network.model.RegisterRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by stav on 1/3/18.
 */

public interface UserService {

    @GET("me")
    Observable<AuthResponse> authenticate();

    @POST("login")
    Observable<AuthResponse> login(@Body LoginRequest loginRequest);

    @POST("register")
    Observable<AuthResponse> registerUser(@Body RegisterRequest registerRequest);
}
