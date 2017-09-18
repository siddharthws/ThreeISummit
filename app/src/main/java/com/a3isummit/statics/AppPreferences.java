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
    public static final String PREF_APP_ID               = "pref_app_id";
    public static final String PREF_USER_NAME               = "pref_user_name";
    public static final String PREF_USER_PHONE              = "pref_user_phone";
    public static final String PREF_USER_EMAIL               ="pref_user_email";
    public static final String PREF_APP_GATE_PASS               = "pref_app_gate_pass";

    // ----------------------- Classes ---------------------------//
    // ----------------------- Interfaces ----------------------- //
    // ----------------------- Globals ----------------------- //
    // User Information
    private static String   name             = null;
    private static String   phone             = null;
    private static String email                =null;
    private static int app_id                = -1;
    private static int app_gate_pass                = -1;


    // ----------------------- Constructor ----------------------- //
    // ----------------------- Overrides ----------------------- //
    // ----------------------- Public APIs ----------------------- //
    // Initialize Statics
    public static void Init(Context context)
    {
        SharedPreferences sharedPref        = context.getSharedPreferences(         PREF_FILE_NAME,
                                                                                    Context.MODE_PRIVATE);

        // Read user information
        app_id          = sharedPref.getInt(PREF_APP_ID, -1);
        name            = sharedPref.getString(PREF_USER_NAME, "");
        phone           = sharedPref.getString(PREF_USER_PHONE, "");
        email            =sharedPref.getString(PREF_USER_EMAIL,"");
        app_gate_pass          = sharedPref.getInt(PREF_APP_GATE_PASS, -1);
    }

    // ----------------------- Private APIs ----------------------- //

    /*
     * SET APIs
     */
    // User Info
    public static void SetUserInfo (Context context, int app_id,String name, String phone,String email, int app_gate_pass)
    {
        SharedPreferences sharedPref        = context.getSharedPreferences(     PREF_FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEdit   = sharedPref.edit();

        // Write App registration data
        prefEdit.putInt(PREF_APP_ID, app_id);
        prefEdit.putString(PREF_USER_NAME,      name);
        prefEdit.putString(PREF_USER_PHONE,     phone);
        prefEdit.putString(PREF_USER_EMAIL,email);
        prefEdit.putInt(PREF_APP_GATE_PASS, app_gate_pass);
        prefEdit.apply();

        // Set cache
        AppPreferences.app_id=app_id;
        AppPreferences.name = name;
        AppPreferences.phone = phone;
        AppPreferences.email=email;
        AppPreferences.app_gate_pass=app_gate_pass;
    }



    /*
         * GET APIs
         */
    // User Info
    public static String GetName ()
    {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    public static String GetPhone ()
    {
        return phone;
    }

    public static int getApp_id() {
        return app_id;
    }

    public static int getApp_GatePass() {
        return app_gate_pass;
    }
}
