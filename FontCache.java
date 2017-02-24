package com.codingexercide.newscorp.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Created by veswanaranha on 2/10/17.
 */

public class FontCache {

    private static Hashtable<String, Typeface> fontCache = new Hashtable<String, Typeface>();

    //Caching Typeface to increase speed and to avoid memory leaks since it's not super fast to read from assets all the time

    public static Typeface get(String name, Context context) {
        Typeface tf = fontCache.get(name);
        if(tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), name);
            }
            catch (Exception e) {
                return null;
            }
            fontCache.put(name, tf);
        }
        return tf;
    }
}