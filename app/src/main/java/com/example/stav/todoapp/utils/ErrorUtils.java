package com.example.stav.todoapp.utils;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by stav on 1/15/18.
 */

public class ErrorUtils {

    private static final String NETWORK_CONNECTION_ERROR = "No internet connection";
    private static final String DEFAULT_ERROR = "Some error occurred";

    // handling network errors
    public static String getErrorMessage(Throwable error){

        // non 2.x.x error
        if (error instanceof HttpException){
            ResponseBody responseBody = ((HttpException)error).response().errorBody();
            try {
                JSONObject jsonObject = new JSONObject(responseBody.string());
                return jsonObject.getString("error");
            } catch (Exception e){
                e.getMessage();
            }
        }

        // network error
        if (error instanceof IOException) {
            return NETWORK_CONNECTION_ERROR;
        }
        return DEFAULT_ERROR;
    }
}
