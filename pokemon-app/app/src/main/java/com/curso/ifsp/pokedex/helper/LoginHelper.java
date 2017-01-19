package com.curso.ifsp.pokedex.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by adenilson on 08/10/16.
 */

public class LoginHelper {

    public static final String TAG_LOGIN = "login";
    public static final String KEY_LOGGED = "logged";

    public static boolean validaLogin(String username, String password) {
        if (username.trim().equals("user@user.com") && password.equals("123")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validaUsername(String username) {
        return username.contains("@");
    }


    public static void setLogged(Activity activity, boolean logged) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(TAG_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(KEY_LOGGED, logged);
        edit.apply();
    }

    public static boolean isLogged(Activity activity){
        boolean logged;

        SharedPreferences sharedPreferences = activity.getSharedPreferences(TAG_LOGIN, Context.MODE_PRIVATE);
        logged = sharedPreferences.getBoolean(KEY_LOGGED, false);

        return logged;
    }

}
