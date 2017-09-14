package com.a3isummit.threeisummit;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Siddharth on 01-04-2017.
 */

public class RegistrationHelper
{
    public static final String PREF_FILENAME = "pref_login";
    public static final String PREF_USERNAME = "user_name";
    public static final String PREF_MOBILE= "mobile_number";

    public RegistrationHelper()
    {

    }

    public void Login (Context context, String username)
    {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = sharedPref.edit();

        prefEdit.putString(PREF_USERNAME, username);

        prefEdit.commit();
    }
    public void clear (Context context)
    {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = sharedPref.edit();

        prefEdit.clear();

        prefEdit.commit();
    }

    public void Logout (Context context)
    {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = sharedPref.edit();

        prefEdit.clear();

        prefEdit.commit();
    }

    public boolean IsLoggedIn(Context context)
    {
        boolean bLoggedIn = false;
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);

        if (!sharedPref.getString(PREF_USERNAME, "").equals(""))
        {
            bLoggedIn = true;
        }

        return bLoggedIn;
    }

    public String GetUsername(Context context)
    {
        String username = "";
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);

        username = sharedPref.getString(PREF_USERNAME, "");

        return username;
    }
    public int GetCenter(Context context)
    {
        int center = 0;
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);

        center = sharedPref.getInt(PREF_MOBILE, 0);

        return center;
    }
    public void setExtra(Context context,String key,int value)
    {

        SharedPreferences sharedPref = context.getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = sharedPref.edit();

        prefEdit.putInt(key, value);
        prefEdit.commit();



    }
    public Boolean isanyonethere(Context context)
    {
        String username = "";
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = sharedPref.edit();

        if(!sharedPref.getString(PREF_USERNAME, "").equals(""))
        {
            return true;
        }


return false;

    }
}
