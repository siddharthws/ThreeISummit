package com.a3isummit.threeisummit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

import com.a3isummit.animations.AnimHelper;
import com.a3isummit.debug.Dbg;
import com.a3isummit.macros.MacRequestCodes;
import com.a3isummit.server.FeedbackServerTask;
import com.a3isummit.server.ServerInterfaces;
import com.a3isummit.statics.AppPreferences;
import com.a3isummit.views.RlAbout;
import com.a3isummit.views.RlCountdown;
import com.a3isummit.views.RlDrawer;
import com.a3isummit.views.RlEvent;

public class GalleryActivity extends BaseActivity {
    private ActivityViewHolders.Gallery ui = null;

    public void SetViewHolder()
    {
        // Init holder
        ui = new ActivityViewHolders.Gallery();
        ui.vwContent = getLayoutInflater().inflate(R.layout.activity_gallery, null);

        // Find all views form layout
        ui.imageView1=(ImageView)ui.vwContent.findViewById(R.id.imageView1);
        ui.imageView2=(ImageView)ui.vwContent.findViewById(R.id.imageView2);
        ui.imageView3=(ImageView)ui.vwContent.findViewById(R.id.imageView3);
        ui.submit = (Button) ui.vwContent.findViewById(R.id.bt1);

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
