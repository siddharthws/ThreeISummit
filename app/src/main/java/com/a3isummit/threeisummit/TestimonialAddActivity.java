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
import com.a3isummit.server.TestimonialAddServerTask;
import com.a3isummit.statics.AppPreferences;
import com.a3isummit.views.RlAbout;
import com.a3isummit.views.RlCountdown;
import com.a3isummit.views.RlDrawer;
import com.a3isummit.views.RlEvent;

public class TestimonialAddActivity extends BaseActivity implements ServerInterfaces.IfaceBasic {
    private ActivityViewHolders.TestimonialAdd ui = null;

    public void SetViewHolder()
    {
        // Init holder
        ui = new ActivityViewHolders.TestimonialAdd();
        ui.vwContent = getLayoutInflater().inflate(R.layout.activity_testimonial_add, null);

        // Find all views form layout
        ui.tmTextview = (TextView) ui.vwContent.findViewById(R.id.edtxt1);

        ui.submit = (Button) ui.vwContent.findViewById(R.id.bt1);

        // Set Holder
        holder = ui;
    }

    @Override
    public void Init()
    {

    }

    public static void Start(BaseActivity activity)
    {
        BaseActivity.Start(activity, TestimonialAddActivity.class, -1, null, MacRequestCodes.INVALID, null);
    }

    // Button Click APIs
    public void ButtonClickBack(View view)
    {
        finish();
    }

    public void ButtonClickFetch(View view)
    {
        //testimonial
        String suggestion=ui.tmTextview.getText().toString();



        AppPreferences.Init(this);
        TestimonialAddServerTask testimonialAddServerTask = new TestimonialAddServerTask(this, AppPreferences.getApp_id(), AppPreferences.GetName(),suggestion);
        testimonialAddServerTask.SetBasicInterface(this);
        testimonialAddServerTask.execute();

    }

    @Override
    public void onServerSuccess() {
        Dbg.Toast(this, "Have a Nice Day...", Toast.LENGTH_SHORT);
        finish();

    }

    @Override
    public void onServerFailure() {

        Dbg.Toast(getApplicationContext(), "Failed to Register your Views...", Toast.LENGTH_SHORT);

    }

}
