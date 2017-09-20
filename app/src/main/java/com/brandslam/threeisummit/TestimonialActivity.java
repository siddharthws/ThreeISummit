package com.brandslam.threeisummit;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.brandslam.adapters.ListItemObject;
import com.brandslam.adapters.TestimonialAdapter;
import com.brandslam.macros.MacRequestCodes;
import com.brandslam.objects.TestimonialObject;
import com.brandslam.server.ConnectCheckTask;
import com.brandslam.server.ServerInterfaces;
import com.brandslam.server.TestimonialFetchServerTask;
import com.brandslam.statics.AppPreferences;
import com.brandslam.threeisummit.R;

import java.util.ArrayList;

public class TestimonialActivity extends BaseActivity implements ServerInterfaces.IfaceTestFetch {
    private ActivityViewHolders.Testimonial ui = null;
    private TestimonialAdapter listAdapter = null;

    public void SetViewHolder()
    {
        // Init holder
        ui = new ActivityViewHolders.Testimonial();
        ui.vwContent = getLayoutInflater().inflate(R.layout.activity_testimonial, null);

        // Find all views form layout
        ui.lvList = (ListView) ui.vwContent.findViewById(R.id.lv_list);

        // Set Holder
        holder = ui;
    }

    @Override
    public void Init()
    {
        if(ConnectCheckTask.isNetworkAvailable(this)){
            AppPreferences.Init(this);
            TestimonialFetchServerTask testimonialFetchServerTask = new TestimonialFetchServerTask(this, AppPreferences.getApp_id());
            testimonialFetchServerTask.SetBasicInterface(this);
            testimonialFetchServerTask.execute();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please Connect to Internet",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        //call on Resume
        TestimonialFetchServerTask testimonialFetchServerTask = new TestimonialFetchServerTask(this, AppPreferences.getApp_id());
        testimonialFetchServerTask.SetBasicInterface(this);
        testimonialFetchServerTask.execute();

    }

    public static void Start(BaseActivity activity)
    {
        BaseActivity.Start(activity, TestimonialActivity.class, -1, null, MacRequestCodes.INVALID, null);
    }

    // Button Click APIs
    public void ButtonClickBack(View view)
    {
        finish();
    }

    public void ButtonClickAdd(View view)
    {
        Intent myIntent = new Intent(this, TestimonialAddActivity.class);
        this.startActivity(myIntent);
    }

    @Override
    public void onServerSuccess(ArrayList<TestimonialObject> testimonialObjects) {

        listAdapter = new TestimonialAdapter(this, ui.lvList);
        for (TestimonialObject testimonial : testimonialObjects) {
            listAdapter.Add(new ListItemObject.Testimonial(testimonial));
        }
    }

    @Override
    public void onServerFailure() {

        finish(); }
}
