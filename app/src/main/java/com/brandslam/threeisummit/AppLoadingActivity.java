package com.brandslam.threeisummit;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.brandslam.macros.MacRequestCodes;
import com.brandslam.statics.AppPreferences;
import com.brandslam.statics.AppStatics;
import com.brandslam.threeisummit.R;

public class AppLoadingActivity     extends     BaseActivity
                                    implements  AppLoadingTask.IfaceAppLoadingListener,
                                                ActivityInterfaces.ResultRegistration
{
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "APP_LOADING_ACTIVITY";

    // ----------------------- Interfaces ----------------------- //
    // ----------------------- Globals ----------------------- //
    private ActivityViewHolders.AppLoading ui = null;
    private AppLoadingTask appLoadTask = null;

    // ----------------------- Constructor ----------------------- //
    // ----------------------- Overrides ----------------------- //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Mark App as Initialized
        AppStatics.bInitialized = true;

        // Call Super
        super.onCreate(savedInstanceState);
    }

    @Override
    public void SetViewHolder()
    {
        Log.i(TAG,"inside app_loading");
        // Init holder
        ui = new ActivityViewHolders.AppLoading();
        ui.vwContent = getLayoutInflater().inflate(R.layout.activity_app_loading, null);


        // Activity View
       // ui.pbLoading    = (ProgressBar)    ui.vwContent.findViewById(R.id.pb_load_progress);
        //ui.tvLoading    = (TextView)       ui.vwContent.findViewById(R.id.tvAppLoadActionLabel);

        // Set Holder
        holder = ui;
    }

    @Override
    public void Init()
    {
        // Set Screen size
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        AppStatics.SCREEN_DENSITY   = metrics.density;
        AppStatics.SCREEN_SIZE      = new Point(metrics.widthPixels, metrics.heightPixels);

        // Initialize AppPreferences
        AppPreferences.Init(this);

        // Check if user is registered
        // TODO : Remove Hack once server has been implemented
        if (AppPreferences.GetName().length() == 0)
        {

            // Start Registration Activity
            SetRegistrationResultListener(this);
            RegistrationActivity.Start(this);
        }
        else
        {
            onRegistrationComplete();
        }
    }

    @Override
    public void onRegistrationComplete()
    {
        appLoadTask = new AppLoadingTask(this);
        appLoadTask.SetOnAppLoadingListener(this);
        appLoadTask.execute();

    }

    @Override
    public void onRegistrationFailed()
    {
        finish();
    }

    @Override
    public void onLoadComplete()
    {
        // Start Homescreen
        HomescreenActivity.Start(this);
        finish();
    }

    // ----------------------- Public APIs ----------------------- //
    // Helper API to start the activity
    public static void Start(BaseActivity activity, Bundle extras)
    {
        Start(activity, AppLoadingActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK, extras, MacRequestCodes.INVALID, null);
    }

    // ----------------------- Private APIs ----------------------- //
}