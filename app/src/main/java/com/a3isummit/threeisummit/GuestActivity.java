package com.a3isummit.threeisummit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.a3isummit.adapters.GuestAdapter;
import com.a3isummit.adapters.ListItemObject;
import com.a3isummit.macros.MacRequestCodes;
import com.a3isummit.objects.GuestObject;

public class GuestActivity extends BaseActivity {
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
        listAdapter = new GuestAdapter(this, ui.lvList);
        for (GuestObject guest : GuestObject.TEMP_DATA) {
            listAdapter.Add(new ListItemObject.Guest(guest));
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
}
