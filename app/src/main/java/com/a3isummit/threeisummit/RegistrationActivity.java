package com.a3isummit.threeisummit;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import com.a3isummit.macros.MacRequestCodes;

public class RegistrationActivity extends         BaseActivity
{
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "REGISTRATION_ACTIVITY";

    // ----------------------- Classes ---------------------------//
    // ----------------------- Interfaces ----------------------- //
    // ----------------------- Globals ----------------------- //
    // UI holder of activity view
    private ActivityViewHolders.Registration ui = null;

    // ----------------------- Constructor ----------------------- //
    // ----------------------- Overrides ----------------------- //
    @Override
    public void SetViewHolder()
    {
        // Init Holder
        ui = new ActivityViewHolders.Registration();
        ui.vwContent = getLayoutInflater().inflate(R.layout.activity_registration, null);

        // Set Holder
        holder = ui;
    }

    @Override
    public void Init()
    {
    }

    // ----------------------- Public APIs ----------------------- //
    public static void Start(BaseActivity activity)
    {
        BaseActivity.Start(activity, RegistrationActivity.class, -1, null, MacRequestCodes.CODE_ACTIVITY_RESULT_REGISTRATION, null);
    }


    public void ButtonClickRegister(View view)
    {
        setResult(RESULT_OK);
        finish();

    }

    // ----------------------- Private APIs ----------------------- //
}
