package com.a3isummit.debug;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.a3isummit.macros.MacApp;

/**
 * Created by Siddharth on 03-11-2016.
 */

public class Dbg
{
    // ----------------------- Constants ----------------------- //
    // ----------------------- Interfaces ----------------------- //
    // ----------------------- Globals ----------------------- //
    // ----------------------- Constructor ----------------------- //
    // ----------------------- Overrides ----------------------- //
    // ----------------------- Public APIs ----------------------- //
    // Logging APIs
    public static void info (String TAG,
                             String msg)
    {
        if (!MacApp.bRelease)
        {
            Log.i(TAG, msg);
        }
    }

    public static void error (String TAG,
                              String msg)
    {
        if (!MacApp.bRelease)
        {
            Log.e(TAG, msg);
        }
    }

    public static void stack (Exception e)
    {
        if (!MacApp.bRelease)
        {
            e.printStackTrace();
        }
    }

    // Toasting APIs
    public static void Toast (Context context,
                              String msg,
                              int duration)
    {
        if (context != null)
        {
            Toast.makeText(context, msg, duration).show();
        }
    }

    // ----------------------- Private APIs ----------------------- //
}
