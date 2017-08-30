package com.a3isummit.threeisummit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.a3isummit.animations.AnimHelper;
import com.a3isummit.macros.MacRequestCodes;
import com.a3isummit.views.RlAbout;
import com.a3isummit.views.RlCountdown;
import com.a3isummit.views.RlDrawer;
import com.a3isummit.views.RlEvent;

public class FeedbackActivity extends BaseActivity {
    private ActivityViewHolders.Feedback ui = null;

    public void SetViewHolder()
    {
        // Init holder
        ui = new ActivityViewHolders.Feedback();
        ui.vwContent = getLayoutInflater().inflate(R.layout.activity_feedback, null);

        // Find all views form layout
        ui.fbTextview = (TextView) ui.vwContent.findViewById(R.id.tv1);
        ui.fbEdittext1 = (EditText) ui.vwContent.findViewById(R.id.edtxt1);
        ui.fbEdittext2 = (EditText) ui.vwContent.findViewById(R.id.edtxt2);
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
        BaseActivity.Start(activity, FeedbackActivity.class, -1, null, MacRequestCodes.INVALID, null);
    }

    // Button Click APIs
    public void ButtonClickBack(View view)
    {
        finish();
    }

    public void ButtonClickFetch(View view)
    {
        //suggeston
        String suggestion=ui.fbEdittext1.getText().toString();

        //name of user
        String username=ui.fbEdittext2.getText().toString();
    }

}
