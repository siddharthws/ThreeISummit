package com.a3isummit.threeisummit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.a3isummit.adapters.GuestAdapter;
import com.a3isummit.adapters.ListItemObject;
import com.a3isummit.macros.MacRequestCodes;
import com.a3isummit.objects.GuestObject;
import com.a3isummit.objects.TestimonialObject;
import com.a3isummit.server.GuestServerTask;
import com.a3isummit.server.ServerInterfaces;
import com.a3isummit.statics.AppPreferences;

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
    public void Init()
    {
        AppPreferences.Init(this);
        GuestServerTask guestServerTask = new GuestServerTask(this, AppPreferences.getApp_id());
        guestServerTask.SetBasicInterface(this);
        guestServerTask.execute();
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
    public void onServerFailure() {

    }
}
