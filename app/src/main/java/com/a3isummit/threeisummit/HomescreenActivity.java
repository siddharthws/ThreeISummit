package com.a3isummit.threeisummit;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import com.a3isummit.animations.AnimHelper;
import com.a3isummit.animations.AnimObject;
import com.a3isummit.animations.PowerInterpolator;
import com.a3isummit.macros.MacRequestCodes;
import com.a3isummit.views.RlDrawer;

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
