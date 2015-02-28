package com.sostan.whisper;

import android.content.Context;
import android.content.SharedPreferences.Editor;

/**
 * Created by 35517_000 on 2015/2/27.
 */
public class Config {

    public static final String APP_ID = "com.sostan.whisper";
    public static final String KEY_TOKEN = "token";
    public static final String CHARSET = "utf-8";
    public static final String SERVER_URL = "";

    public static String getCachedToken(Context context) {
        return context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).getString(KEY_TOKEN,null);
    }

    public static void cacheToken(Context context, String token) {
        Editor e = context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).edit();
        e.putString(KEY_TOKEN,token);
        e.commit();
    }
}
