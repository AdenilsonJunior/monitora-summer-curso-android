package com.curso.ifsp.pokedex.utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by adenilson on 09/10/16.
 */

public class Utils {


    public static Drawable getDrawableByIdResource(Activity activity, int id){
        return ContextCompat.getDrawable(activity, id);
    }
}
