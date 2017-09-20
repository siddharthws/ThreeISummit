package com.a3isummit.threeisummit;

import android.view.View;

import com.a3isummit.macros.MacRequestCodes;

public class GalleryActivity extends BaseActivity {
    private ActivityViewHolders.Gallery ui = null;

    public void SetViewHolder()
    {
        // Init holder
        ui = new ActivityViewHolders.Gallery();
        ui.vwContent = getLayoutInflater().inflate(R.layout.activity_gallery, null);

        // Find all views form layout


        // Set Holder
        holder = ui;
    }

    @Override
    public void Init()
    {
        //write logic here
    }

    public static void Start(BaseActivity activity)
    {
        BaseActivity.Start(activity, GalleryActivity.class, -1, null, MacRequestCodes.INVALID, null);
    }

    // Button Click APIs
    public void ButtonClickBack(View view)
    {
        finish();
    }
}
