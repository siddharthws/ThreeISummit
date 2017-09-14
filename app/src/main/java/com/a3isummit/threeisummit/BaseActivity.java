package com.a3isummit.threeisummit;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.a3isummit.application.App;
import com.a3isummit.macros.MacRequestCodes;
import com.a3isummit.statics.AppStatics;

import java.util.ArrayList;

/**
 * Created by Siddharth on 25-07-2017.
 */

public abstract class BaseActivity extends AppCompatActivity
{
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "BASE_ACTIVITY";

    // ----------------------- Classes ---------------------------//
    // ----------------------- Interfaces ----------------------- //
    // ----------------------- Globals ----------------------- //
    // View Holder
    protected ActivityViewHolders.Base holder     = null;

    // permission related initData
    private boolean bResumeFromPermission = false;
    private String[] permissions = null;
    private int[] grantResults = null;

    // Permission Listeners
    private ActivityInterfaces.ContactPermission    contactPermissionListener   = null;

    // Activity result related data
    private boolean bResumeFromResult = false;
    private int resumeRequestCode = 0, resumeResultCode = 0;
    private Intent resumeResultIntent = null;

    // Result Listeners
    private ActivityInterfaces.ResultRegistration          registrationListener             = null;

    // ----------------------- Constructor ----------------------- //
    // ----------------------- Abstracts ----------------------- //
    // Function to initialize View Holder object for every subclass
    public abstract void SetViewHolder();

    // Function to initialize UI elements
    public abstract void Init();

    // ----------------------- Overrides ----------------------- //
    // Lifecycle overrides
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Call super
        super.onCreate(savedInstanceState);

        // If app has not been initialized, launch app loading activity
        if (!AppStatics.bInitialized)
        {
            // Start app loading activity
            AppLoadingActivity.Start(this, null);

            // Finish this activity
            finish();

            // Stop execution
            return;
        }

        // Set status bar color
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        // Set content view
        setContentView(R.layout.activity_base);

        // Init View holders through subclass
        SetViewHolder();

        // Find Base Activity Views
        holder.flContentContainer = (FrameLayout) findViewById(R.id.fl_content_container);

        // Add content to base layout
        holder.flContentContainer.addView(holder.vwContent);


        // Init Views
        Init();
    }

    @Override
    public void onRestart()
    {
        // Call Super
        super.onRestart();
    }

    @Override
    public void onStart()
    {
        // Call Super
        super.onStart();
    }

    @Override
    public void onResume()
    {
        // Call Super
        super.onResume();

        // Set this to current activity
        App.SetCurrentActivity(this);

        if (bResumeFromPermission)
        {
            // Call current listeners
            CallPermissionListeners();

            // Reset initData
            bResumeFromPermission = false;
            grantResults = null;
            permissions = null;
        }
        // Check for result resule
        else if (bResumeFromResult)
        {
            // Call current listeners
            CallResultListeners();

            // Reset data
            this.bResumeFromResult = false;
            this.resumeRequestCode = 0;
            this.resumeResultCode = 0;
            this.resumeResultIntent = null;
        }
    }

    @Override
    public void onPause()
    {
        // Clear this as current activity
        App.ClearReferences(this);

        // Call Super
        super.onPause();
    }

    @Override
    public void onStop()
    {
        // Call Super
        super.onStop();
    }

    @Override
    public void onDestroy()
    {
        // Call Super
        super.onDestroy();
    }

    @Override
    public void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    // Result overrides
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // Set resume data
        this.bResumeFromResult = true;
        this.resumeRequestCode = requestCode;
        this.resumeResultCode = resultCode;
        this.resumeResultIntent = data;

        // Call Super
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        // Set resume initData
        this.permissions = permissions;
        this.grantResults = grantResults;
        bResumeFromPermission = true;

        // Call Super
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    // ----------------------- Public APIs ----------------------- //
    public static void Start(BaseActivity activity, Class<?> cls, int flags, Bundle extras, int requestCode, String action)
    {
        // SetAlarm Intent
        Intent intent = new Intent(activity, cls);

        // Set Extras
        if (extras != null)
        {
            intent.putExtras(extras);
        }

        // Set Action
        if (action != null)
        {
            intent.setAction(action);
        }

        // Set Flags
        if (flags != -1)
        {
            intent.setFlags(flags);
        }

        // Launch Activity
        if (requestCode == MacRequestCodes.INVALID)
        {
            // No result expected
            activity.startActivity(intent);
        }
        else
        {
            // Result expected
            activity.startActivityForResult(intent, requestCode);
        }
    }

    public void SetContactPermissionListener(ActivityInterfaces.ContactPermission listener)
    {
        this.contactPermissionListener = listener;
    }

    // APIs to set event listeners
    public void SetRegistrationResultListener(ActivityInterfaces.ResultRegistration listener)
    {
        this.registrationListener = listener;
    }

    // Request permission
    public void RequestPermission(String[] permissions, int requestCode)
    {
        ActivityCompat.requestPermissions(  this,
                permissions,
                requestCode);
    }

    // ----------------------- Private APIs ----------------------- //
    private void CallResultListeners()
    {
        switch (resumeRequestCode)
        {
            case MacRequestCodes.CODE_ACTIVITY_RESULT_REGISTRATION:
            {
                if (registrationListener != null)
                {
                    if (resumeResultCode == Activity.RESULT_OK)
                    {
                        // Call Listener
                        registrationListener.onRegistrationComplete();
                    }
                    else
                    {
                        // Call Listener
                        registrationListener.onRegistrationFailed();
                    }
                }
                break;
            }
        }
    }

    private void CallPermissionListeners()
    {
        if ((permissions == null) || (grantResults == null))
        {
            return;
        }

        // Check result and call appropraite listener
        for (int i = 0; i < permissions.length; i++)
        {
            if (permissions[i].equals(Manifest.permission.READ_CONTACTS))
            {
                if (contactPermissionListener != null)
                {
                    if (grantResults[i] == PermissionChecker.PERMISSION_GRANTED)
                    {
                        contactPermissionListener.onContactPermissionSuccess();
                    }
                    else
                    {
                        contactPermissionListener.onContactPermissionFailure();
                    }
                }
            }
        }
    }
}
