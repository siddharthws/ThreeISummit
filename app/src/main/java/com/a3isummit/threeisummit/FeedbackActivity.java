package com.a3isummit.threeisummit;

import android.view.View;
import android.widget.*;

import com.a3isummit.debug.Dbg;
import com.a3isummit.macros.MacRequestCodes;
import com.a3isummit.server.ConnectCheckTask;
import com.a3isummit.server.FeedbackServerTask;
import com.a3isummit.server.ServerInterfaces;
import com.a3isummit.statics.AppPreferences;

public class FeedbackActivity extends BaseActivity implements ServerInterfaces.IfaceBasic {
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
        ui.progressBar1=(ProgressBar)findViewById(R.id.progressBar1);
        ui.progressBar1.setVisibility(View.INVISIBLE);
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
        String username=ui.fbEdittext1.getText().toString();

        //name of user
        String suggestion=ui.fbEdittext2.getText().toString();
        if(ConnectCheckTask.isNetworkAvailable(this)){
            if((suggestion=="")||(suggestion.length()==0))
            {
                Toast.makeText(this,"Please Enter Data",Toast.LENGTH_SHORT).show();

            }
            else{
                ui.progressBar1.setVisibility(View.VISIBLE);
                AppPreferences.Init(this);
                FeedbackServerTask feedbackServerTask = new FeedbackServerTask(this,AppPreferences.getApp_id(),username,suggestion);
                feedbackServerTask.SetBasicInterface(this);
                feedbackServerTask.execute();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Please Connect to Internet",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onServerSuccess() {
        ui.progressBar1.setVisibility(View.GONE);
        Dbg.Toast(this, "It is valuable to us.....", Toast.LENGTH_SHORT);
        ui.fbEdittext1.setText("");
        ui.fbEdittext2.setText("");
    }

    @Override
    public void onServerFailure() {
        ui.progressBar1.setVisibility(View.INVISIBLE);
        Dbg.Toast(getApplicationContext(), "Failed to Send Feedback...", Toast.LENGTH_SHORT);

    }

}
