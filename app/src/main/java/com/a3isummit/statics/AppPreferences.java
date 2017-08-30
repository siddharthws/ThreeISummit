package com.a3isummit.statics;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Siddharth on 30-08-2017.
 */

public class AppPreferences
{
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "APP_PREFERENCES";

    // Preference File
    public static final String PREF_FILE_NAME               = "settings_preferences_file";

    //Preference Keys
    // User information
    public static final String PREF_USER_NAME               = "pref_user_name";
    public static final String PREF_USER_PHONE              = "pref_user_phone";

    // ----------------------- Classes ---------------------------//
    // ----------------------- Interfaces ----------------------- //
    // ----------------------- Globals ----------------------- //
    // User Information
    private static String   name             = null;
    private static String   phone             = null;

    // ----------------------- Constructor ----------------------- //
    // ----------------------- Overrides ----------------------- //
    // ----------------------- Public APIs ----------------------- //
    // Initialize Statics
    public static void Init(Context context)
    {
        SharedPreferences sharedPref        = context.getSharedPreferences(         PREF_FILE_NAME,
                                                                                    Context.MODE_PRIVATE);

        // Read user information
        name            = sharedPref.getString(PREF_USER_NAME, "");
        phone           = sharedPref.getString(PREF_USER_PHONE, "");
    }

    // ----------------------- Private APIs ----------------------- //

    /*
     * SET APIs
     */
    // User Info
    public static void SetUserInfo (Context context, String name, String phone)
    {
        SharedPreferences sharedPref        = context.getSharedPreferences(     PREF_FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEdit   = sharedPref.edit();

        // Write App registration data
        prefEdit.putString(PREF_USER_NAME,      name);
        prefEdit.putString(PREF_USER_PHONE,     phone);
        prefEdit.apply();

        // Set cache
        AppPreferences.name = name;
        AppPreferences.phone = phone;
    }

    /*
     * GET APIs
     */
    // User Info
    public static String GetName ()
    {
        return name;
    }

    public static String GetPhone ()
    {
        return phone;
    }
}
