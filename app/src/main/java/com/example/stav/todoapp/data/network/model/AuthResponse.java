package com.example.stav.todoapp.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stav on 1/20/18.
 */

public class AuthResponse {

    @SerializedName("id")
    private String userId;

    @SerializedName("token")
    private String token;

    public AuthResponse(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }
}
