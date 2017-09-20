package com.brandslam.statics;

import android.graphics.Point;
import android.os.Looper;

/**
 * Created by Siddharth on 25-07-2017.
 */

public class AppStatics
{
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "APP_STATICS";

    // ----------------------- Globals ----------------------- //
    // App Init Flag
    public static boolean bInitialized = false;

    // Screen size
    public static Point SCREEN_SIZE = null;

    // Screen Density
    public static float SCREEN_DENSITY = 0f;

    // ----------------------- Public APIs ----------------------- //
    // Check if this thread is UI thread
    public static boolean IsOnUiThread()
    {
        return (Looper.myLooper() == Looper.getMainLooper());
    }

}
