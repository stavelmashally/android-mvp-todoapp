package com.example.stav.todoapp.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stav on 1/24/18.
 */

public class RegisterRequest {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("username")
    private String userName;

    public RegisterRequest(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
