package com.example.stav.todoapp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stav on 1/9/18.
 */

public class User {

    @SerializedName("email")
    private String email;

    @SerializedName("username")
    private String userName;

    @SerializedName("password")
    private String password;

    public User(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
