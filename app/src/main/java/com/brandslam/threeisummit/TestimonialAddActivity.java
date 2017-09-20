package com.brandslam.threeisummit;

import android.view.View;
import android.widget.*;

import com.brandslam.debug.Dbg;
import com.brandslam.macros.MacRequestCodes;
import com.brandslam.server.ConnectCheckTask;
import com.brandslam.server.ServerInterfaces;
import com.brandslam.server.TestimonialAddServerTask;
import com.brandslam.statics.AppPreferences;
import com.brandslam.threeisummit.R;

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
        ui.progressBar2=(ProgressBar)findViewById(R.id.progressBar2);
        ui.progressBar2.setVisibility(View.INVISIBLE);
    }

    public static void Start(BaseActivity activity)
    {
        Start(activity, TestimonialAddActivity.class, -1, null, MacRequestCodes.INVALID, null);
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
        if(ConnectCheckTask.isNetworkAvailable(this)){
            if(suggestion.length()!=0&&suggestion!="")
            {
                ui.progressBar2.setVisibility(View.VISIBLE);
                AppPreferences.Init(this);
                TestimonialAddServerTask testimonialAddServerTask = new TestimonialAddServerTask(this, AppPreferences.getApp_id(), AppPreferences.GetName(),suggestion);
                testimonialAddServerTask.SetBasicInterface(this);
                testimonialAddServerTask.execute();
            }
            else {
                Toast.makeText(getApplicationContext(),"Please Enter Data",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Please Connect to Internet",Toast.LENGTH_SHORT).show();
        }






    }

    @Override
    public void onServerSuccess() {
        ui.progressBar2.setVisibility(View.GONE);
        Dbg.Toast(this, "Have a Nice Day...", Toast.LENGTH_SHORT);
        finish();

    }

    @Override
    public void onServerFailure() {
        ui.progressBar2.setVisibility(View.INVISIBLE);
        Dbg.Toast(getApplicationContext(), "Failed to Register your Views...", Toast.LENGTH_SHORT);

    }

}