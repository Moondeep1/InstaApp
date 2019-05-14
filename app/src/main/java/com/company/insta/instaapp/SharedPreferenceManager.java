package com.company.insta.instaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by LENOVO on 5/14/2019.
 */

public class SharedPreferenceManager {
    private static final String FILENAME = "INSTAGRAMLOGIN";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String ID = "id";

    private static Context context;
    private static SharedPreferenceManager sharedPreferenceManager;

    private SharedPreferenceManager(Context context)
    {
        this.context=context;
    }
    public static synchronized SharedPreferenceManager getInstance(Context context){
        if(sharedPreferenceManager==null)
        {
            sharedPreferenceManager = new SharedPreferenceManager(context);
        }
        return sharedPreferenceManager;
    }
    public void storeUserData(User user)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME, user.getUsername());
        editor.putString(EMAIL, user.getEmail());
        editor.putInt(ID, user.getId());
        editor.apply();
    }
    public boolean isUserLoggedIn()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(USERNAME, null) !=null)
        {
            return true;
        }
        return false;
    }
    public void logUserOut()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        context.startActivity(new Intent(context, LoginActivity.class));
    }
    public User getUserData()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        User user = new User(sharedPreferences.getInt(ID, -1), sharedPreferences.getString(EMAIL, null), sharedPreferences.getString(USERNAME, null));
        return user;
    }

}
