package com.example.sachinpc.styleomegav10;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by SachinPC on 7/11/2017.
 */

public class SharedPrefClass {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;
    int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREF_NAME = "UserSession";
private static HashMap<String,String> CartMap=  new HashMap();
    private ArrayList<String> Cart = new ArrayList<>();
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String User_NAME = "User_name";
    public SharedPrefClass(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();

    }
    public void createLoginSession(String name){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(User_NAME, name);



        // commit changes
        editor.commit();
    }
    public String getUserName() {
        String user= pref.getString(User_NAME, null);

        // user email id


        // return user
        return user;
    }
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void CartSave(String Item){
        CartMap.put(Item,"1");


        CartMap.put("Hello","2");
        editor.putStringSet("Hello",CartMap.keySet()).commit();

    }
    public Set Cart(){
return pref.getStringSet("Hello",null);
        //return pref.getStringSet("Hello",null);
   //return CartMap.keySet();
    }

}

