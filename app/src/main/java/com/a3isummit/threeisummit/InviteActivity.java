package com.a3isummit.threeisummit;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a3isummit.adapters.GenericListAdapter;
import com.a3isummit.adapters.ListItemObject;
import com.a3isummit.animations.AnimHelper;
import com.a3isummit.contacts.SyncContactsTask;
import com.a3isummit.debug.Dbg;
import com.a3isummit.macros.MacRequestCodes;
import com.a3isummit.views.RlAbout;
import com.a3isummit.views.RlCountdown;
import com.a3isummit.views.RlDrawer;
import com.a3isummit.views.RlEvent;

import java.util.Iterator;

public class InviteActivity extends BaseActivity implements SyncContactsTask.IfaceSyncContactListener, AdapterView.OnItemClickListener, ActivityInterfaces.ContactPermission {

    // ----------------------- Globals ----------------------- //
    private ActivityViewHolders.Invite ui = null;
    private SyncContactsTask syncContacts = null;
    private GenericListAdapter listAdpater = null;

    // ----------------------- Overrides ----------------------- //
    @Override
    public void SetViewHolder()
    {
        // Init holder
        ui = new ActivityViewHolders.Invite();
        ui.vwContent = getLayoutInflater().inflate(R.layout.activity_invite, null);

        // Find all views form layout
        ui.rlSyncProgress           = (RelativeLayout)     ui.vwContent.findViewById(R.id.rl_contact_sync);
        ui.pbSync                   = (ProgressBar)        ui.vwContent.findViewById(R.id.pb_sync);
        ui.tvSync                   = (TextView)     ui.vwContent.findViewById(R.id.tv_contact_sync);
        ui.lvContacts               = (ListView)         ui.vwContent.findViewById(R.id.lv_contacts);

        // Set Holder
        holder = ui;
    }

    @Override
    public void Init()
    {
        syncContacts = new SyncContactsTask(this);
        syncContacts.SetSyncContactListener(this);

        ui.lvContacts.setOnItemClickListener(this);
        listAdpater = new GenericListAdapter(this, ui.lvContacts);

        if ((SyncContactsTask.contacts == null) || (SyncContactsTask.contacts.size() == 0))
        {
            // Check permission
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PermissionChecker.PERMISSION_GRANTED)
            {
                SetContactPermissionListener(this);
                RequestPermission(new String[]{Manifest.permission.READ_CONTACTS}, 0);
            }
            else
            {
                onContactPermissionSuccess();
            }
        }
        else
        {
            onContactsSyncSuccess();
        }
    }

    @Override
    public void onContactsSyncProgress(int total, int serviced) {
        ui.pbSync.setProgress((serviced * 100) / total);
        ui.tvSync.setText("Syncing Contacts : " + serviced + " / " + total);
    }

    @Override
    public void onContactsSyncSuccess() {
        ui.rlSyncProgress.setVisibility(View.GONE);
        ui.lvContacts.setVisibility(View.VISIBLE);

        Iterator<String> contactKeys = SyncContactsTask.contacts.keySet().iterator();
        while (contactKeys.hasNext())
        {
            String key = contactKeys.next();
            String listitem = SyncContactsTask.contacts.get(key) + "\n" + key;
            listAdpater.Add(new ListItemObject.Generic(-1, -1, listitem, 0, 0, 0));
        }
    }

    @Override
    public void onContactsSyncFailure() {
        Dbg.Toast(this, "Failed to Sync Contacts", Toast.LENGTH_SHORT);
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String title = ((ListItemObject.Generic) listAdpater.getItem(position)).title;
        String[] sep = title.split("\n");
        String phone = sep[1];

        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("smsto:" + phone));
        sendIntent.putExtra("sms_body", "Hello " + sep[0] + ", You are invited to 3I Summit. Click here to download : ");
        startActivity(sendIntent);
    }

    @Override
    public void onContactPermissionSuccess() {
        syncContacts.execute();
    }

    @Override
    public void onContactPermissionFailure() {
        Dbg.Toast(this, "Failed to Sync Contacts", Toast.LENGTH_SHORT);
        finish();
    }

    // Helper API to start this activity
    public static void Start(BaseActivity activity)
    {
        BaseActivity.Start(activity, InviteActivity.class, -1, null, MacRequestCodes.INVALID, null);
    }
}