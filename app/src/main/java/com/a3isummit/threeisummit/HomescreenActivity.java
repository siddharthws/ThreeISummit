package com.a3isummit.threeisummit;

import android.content.Intent;
import android.view.View;

import com.a3isummit.macros.MacRequestCodes;

/**
 * Homescreen activity
 */

public class HomescreenActivity extends     BaseActivity
{
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "HOMESCREEN_ACTIVITY";

    // ----------------------- Interfaces ----------------------- //
    // ----------------------- Globals ----------------------- //
    private ActivityViewHolders.Homescreen ui = null;

    // ----------------------- Overrides ----------------------- //
    @Override
    public void SetViewHolder()
    {
        // Init holder
        ui = new ActivityViewHolders.Homescreen();
        ui.vwContent = getLayoutInflater().inflate(R.layout.activity_homescreen, null);

        // Set Holder
        holder = ui;
    }

    @Override
    public void Init()
    {
    }

    // ----------------------- Public APIs ----------------------- //
    // Helper API to start this activity
    public static void Start(BaseActivity activity)
    {
        BaseActivity.Start(activity, HomescreenActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TOP, null, MacRequestCodes.INVALID, null);
    }

    // Button Click APis
    public void ButtonClickDrawer(View view)
    {
    }

    // Button Click APis
    public void ButtonClickBack(View view)
    {
    }
}
