package com.pumpit.app.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    public static final String KEY_USER_ID = "userId";
    public static final String KEY_AUTH_TYPE = "authorityType";
    private static final String PREF_NAME = "PUMP_IT_USER_SESSION";
    SharedPreferences sharedPrefer;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this.context = context;
        sharedPrefer = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPrefer.edit();
    }

    public void createLoginSession(String userId, String catTyp) {

        editor.putString(KEY_USER_ID, userId);

        editor.putString(KEY_AUTH_TYPE, catTyp);

        editor.commit();
    }

    public HashMap<String, String> getUserDetails() {

        HashMap<String, String> user = new HashMap<String, String>();
        user.put("userId", sharedPrefer.getString(KEY_USER_ID, null));
        user.put("catType", sharedPrefer.getString(KEY_AUTH_TYPE, null));

        return user;
    }
}
