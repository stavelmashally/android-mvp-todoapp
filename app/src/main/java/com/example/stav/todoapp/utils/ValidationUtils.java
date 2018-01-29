package com.example.stav.todoapp.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Created by stav on 1/15/18.
 */

public class ValidationUtils
{
    /**
     * Method to check whether the string is empty or not.
     * @param string1
     * @param string2
     * @return boolean true if strings are empty
     */
    public static boolean isNullOrEmpty(String string1, String string2)
    {
        return TextUtils.isEmpty(string1) && TextUtils.isEmpty(string2);
    }

    /**
     * Method to check valid password
     * @param password - Password should be at least 6 characters
     * @return boolean true if password is valid
     */
    public static boolean isValidPassword(@NonNull String password)
    {
        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }

    /**
     * Method to check valid email
     * @param email - Email id of the user
     * @return boolean true if email is valid
     */
    public static boolean isValidEmail(@NonNull String email)
    {
        return !TextUtils.isEmpty(email) &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Method to check valid text
     * @param text - Text of the todo
     * @return boolean true if text is valid
     */
    public static boolean isValidText(@NonNull String text){
        return !TextUtils.isEmpty(text) && text.length() >= 5;
    }
}
