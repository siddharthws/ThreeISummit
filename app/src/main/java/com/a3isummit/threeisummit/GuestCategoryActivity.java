package com.a3isummit.threeisummit;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.a3isummit.adapters.GuestCategoryAdapter;
import com.a3isummit.adapters.ListItemObject;
import com.a3isummit.macros.MacRequestCodes;
import com.a3isummit.objects.GuestObject;
import com.a3isummit.server.ConnectCheckTask;
import com.a3isummit.server.GuestServerCategoryTask;
import com.a3isummit.server.ServerInterfaces;
import com.a3isummit.statics.AppPreferences;

import java.util.ArrayList;

public class GuestCategoryActivity extends BaseActivity implements ServerInterfaces.IfaceGuestFetch {
    private ActivityViewHolders.Guest ui = null;
    private GuestCategoryAdapter listAdapter = null;


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
        if(ConnectCheckTask.isNetworkAvailable(this)){

            AppPreferences.Init(this);
            GuestServerCategoryTask guestServerCategoryTask = new GuestServerCategoryTask(this, AppPreferences.getApp_id());
            guestServerCategoryTask.SetBasicInterface(this);
            guestServerCategoryTask.execute();
        }
        else {
            Toast.makeText(getApplicationContext(),"Please Connect to Internet",Toast.LENGTH_SHORT).show();
        }
    }

    public static void Start(BaseActivity activity)
    {
        BaseActivity.Start(activity, GuestCategoryActivity.class, -1, null, MacRequestCodes.INVALID, null);
    }

    // Button Click APIs
    public void ButtonClickBack(View view)
    {
        finish();
    }

    @Override
    public void onServerSuccessCat(ArrayList<GuestObject.GuestObjectCategory> guestObjects) {

        listAdapter = new GuestCategoryAdapter(this, ui.lvList);
        for (GuestObject.GuestObjectCategory guest : guestObjects) {
            listAdapter.Add(new ListItemObject.GuestCategory(guest));
        }

        ui.lvList.setOnItemClickListener(onItemClickListener);

    }

    public AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            if(ConnectCheckTask.isNetworkAvailable(GuestCategoryActivity.this)) {
                Intent myIntent = new Intent(GuestCategoryActivity.this, GuestActivity.class);
                myIntent.putExtra("itemtype", i);
                GuestCategoryActivity.this.startActivity(myIntent);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Please Connect to Internet",Toast.LENGTH_SHORT).show();
            }
        }
    };



    @Override
    public void onServerSuccess(ArrayList<GuestObject> guestObjects) {

    }

    @Override
    public void onServerFailure() {

    }
}
