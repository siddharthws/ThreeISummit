package com.a3isummit.threeisummit;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import com.a3isummit.animations.AnimHelper;
import com.a3isummit.animations.AnimObject;
import com.a3isummit.animations.PowerInterpolator;
import com.a3isummit.macros.MacRequestCodes;
import com.a3isummit.views.RlAbout;
import com.a3isummit.views.RlCountdown;
import com.a3isummit.views.RlDrawer;
import com.a3isummit.views.RlEvent;

/**
 * Homescreen activity
 */

public class HomescreenActivity extends     BaseActivity implements RlDrawer.DrawerItemClickListener {
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "HOMESCREEN_ACTIVITY";

    // ----------------------- Interfaces ----------------------- //
    // ----------------------- Globals ----------------------- //
    private ActivityViewHolders.Homescreen ui = null;
    private AnimHelper animHelper = null;

    // ----------------------- Overrides ----------------------- //
    @Override
    public void SetViewHolder()
    {
        // Init holder
        ui = new ActivityViewHolders.Homescreen();
        ui.vwContent = getLayoutInflater().inflate(R.layout.activity_homescreen, null);

        // Find all views form layout
        ui.ibDrawer         = (ImageButton)     ui.vwContent.findViewById(R.id.ib_toolbar_drawer);
        ui.rlDrawer         = (RlDrawer)        ui.vwContent.findViewById(R.id.rl_drawer);
        ui.rlCountdown      = (RlCountdown)     ui.vwContent.findViewById(R.id.rl_countdown);
        ui.rlAbout          = (RlAbout)         ui.vwContent.findViewById(R.id.rl_about);
        ui.rlEvent          = (RlEvent)         ui.vwContent.findViewById(R.id.rl_event);

        // Set Holder
        holder = ui;
    }

    @Override
    public void Init()
    {
        // Initialize Animation Helper
        animHelper = new AnimHelper(this);

        // Set Listeners
        ui.rlDrawer.SetItemClickListener(this);
    }

    // ----------------------- Public APIs ----------------------- //
    // Helper API to start this activity
    public static void Start(BaseActivity activity)
    {
        BaseActivity.Start(activity, HomescreenActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TOP, null, MacRequestCodes.INVALID, null);
    }

    // Button Click APIs
    public void ButtonClickDrawer(View view)
    {
        // Open Drawer
        ui.rlDrawer.Open();
    }

    public void ButtonClickCountdown(View view)
    {
        // Hide other views
        ui.rlAbout.setVisibility(View.GONE);
        ui.rlEvent.setVisibility(View.GONE);

        // Make this view viisble
        ui.rlCountdown.setVisibility(View.VISIBLE);
    }

    public void ButtonClickAbout(View view)
    {
        // Hide other views
        ui.rlCountdown.setVisibility(View.GONE);
        ui.rlEvent.setVisibility(View.GONE);

        // Make this view viisble
        ui.rlAbout.setVisibility(View.VISIBLE);
    }

    public void ButtonClickEvent(View view)
    {
        // Hide other views
        ui.rlAbout.setVisibility(View.GONE);
        ui.rlCountdown.setVisibility(View.GONE);

        // Make this view viisble
        ui.rlEvent.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDrawerItemClick(int actionId)
    {
        switch (actionId)
        {
            case RlDrawer.DRAWER_ACTION_EXIT:
            {
                finish();
                break;
            }
        }
    }
}
