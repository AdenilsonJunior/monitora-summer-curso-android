package com.curso.ifsp.pokedex.helper;

/**
 * Created by adenilson on 08/10/16.
 */

public class LoginHelper {

    public static boolean validaLogin(String username, String password){
        if(username.equals("user@user.com") && password.equals("123")){
            return true;
        }else{
            return false;
        }
    }

    public static boolean validaUsername(String username) {
        return username.contains("@");
    }
}
