package com.a3isummit.threeisummit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.a3isummit.adapters.ListItemObject;
import com.a3isummit.adapters.TestimonialAdapter;
import com.a3isummit.macros.MacRequestCodes;
import com.a3isummit.objects.TestimonialObject;
import com.a3isummit.server.ServerInterfaces;
import com.a3isummit.server.TestimonialAddServerTask;
import com.a3isummit.server.TestimonialFetchServerTask;
import com.a3isummit.statics.AppPreferences;

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

        AppPreferences.Init(this);
        TestimonialFetchServerTask testimonialFetchServerTask = new TestimonialFetchServerTask(this, AppPreferences.getApp_id());
        testimonialFetchServerTask.SetBasicInterface(this);
        testimonialFetchServerTask.execute();

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

    }
}
