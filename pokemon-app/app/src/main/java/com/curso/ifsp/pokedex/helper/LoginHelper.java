package com.curso.ifsp.pokedex.helper;

/**
 * Created by adenilson on 08/10/16.
 */

public class LoginHelper {

    public static boolean validaLogin(String username, String password){
        if(username.equals("user") && password.equals("123")){
            return true;
        }else{
            return false;
        }
    }
}
