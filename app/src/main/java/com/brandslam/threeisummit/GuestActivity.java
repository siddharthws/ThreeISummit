package com.brandslam.threeisummit;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.brandslam.adapters.GuestAdapter;
import com.brandslam.adapters.ListItemObject;
import com.brandslam.macros.MacRequestCodes;
import com.brandslam.objects.GuestObject;
import com.brandslam.server.ConnectCheckTask;
import com.brandslam.server.GuestServerTask;
import com.brandslam.server.ServerInterfaces;
import com.brandslam.statics.AppPreferences;
import com.brandslam.threeisummit.R;

import java.util.ArrayList;

public class GuestActivity extends BaseActivity implements ServerInterfaces.IfaceGuestFetch {
    private ActivityViewHolders.Guest ui = null;
    private GuestAdapter listAdapter = null;

    public void SetViewHolder()
    {
        // Init holder
        ui = new ActivityViewHolders.Guest();
        ui.vwContent = getLayoutInflater().inflate(R.layout.activity_guest, null);

        // Find all views form layout
        ui.lvList = (ListView) ui.vwContent.findViewById(R.id.lv_list);

        // Set Holder
        holder = ui;
    }

    @Override
    public void Init() {

        if (ConnectCheckTask.isNetworkAvailable(this)) {

            int catType = -1;
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                catType = extras.getInt("itemtype");
            }

            AppPreferences.Init(this);
            GuestServerTask guestServerTask = new GuestServerTask(this, AppPreferences.getApp_id(), catType);
            guestServerTask.SetBasicInterface(this);
            guestServerTask.execute();
        } else {

            Toast.makeText(getApplicationContext(), "Please Connect to Internet", Toast.LENGTH_SHORT).show();
        }
    }

    public static void Start(BaseActivity activity)
    {
        BaseActivity.Start(activity, GuestActivity.class, -1, null, MacRequestCodes.INVALID, null);
    }

    // Button Click APIs
    public void ButtonClickBack(View view)
    {
        finish();
    }

    @Override
    public void onServerSuccess(ArrayList<GuestObject> guestObjects) {

        listAdapter = new GuestAdapter(this, ui.lvList);
        for (GuestObject guest : guestObjects) {
            listAdapter.Add(new ListItemObject.Guest(guest));
        }
    }

    @Override
    public void onServerSuccessCat(ArrayList<GuestObject.GuestObjectCategory> guestObjects) {

    }

    @Override
    public void onServerFailure() {

    }
}
