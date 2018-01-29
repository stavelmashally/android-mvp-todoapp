package com.example.stav.todoapp.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stav on 1/28/18.
 */

public class TodoRequest {

    @SerializedName("text")
    private String text;

    public TodoRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
