package com.a3isummit.application;

import android.app.Activity;
import android.app.Application;

import com.a3isummit.threeisummit.BaseActivity;

/**
 * Created by Siddharth on 30-08-2017.
 */

public class App extends Application
{
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "APP";

    // ----------------------- Classes ---------------------------//
    // ----------------------- Interfaces ----------------------- //
    // ----------------------- Globals ----------------------- //
    private static BaseActivity currentActivity = null;

    // ----------------------- Constructor ----------------------- //
    // ----------------------- Overrides ----------------------- //
    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    // ----------------------- Public APIs ----------------------- //
    public static BaseActivity GetCurrentActivity()
    {
        return currentActivity;
    }

    // Set current activity variable
    public static void SetCurrentActivity(BaseActivity activity)
    {
        if (activity != null)
        {
            currentActivity = activity;
        }
    }

    // Clear current activity variable
    public static void ClearReferences(Activity activity)
    {
        if ((activity != null) && (currentActivity != null))
        {
            if (activity.equals(currentActivity))
            {
                currentActivity = null;
            }
        }
    }

    // ----------------------- Private APIs ----------------------- //
}
