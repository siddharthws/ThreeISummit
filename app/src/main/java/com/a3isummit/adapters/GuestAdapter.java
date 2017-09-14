package com.a3isummit.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.a3isummit.threeisummit.R;

/**
 * Created by Siddharth on 14-09-2017.
 */

public class GuestAdapter extends BaseListAdapter {
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "GUEST_ADAPTER";

    // ----------------------- Interfaces ----------------------- //
    // ----------------------- Globals ----------------------- //

    // ----------------------- Constructor ----------------------- //
    public GuestAdapter(Context parentContext, ListView lvList)
    {
        super(parentContext, lvList, R.layout.list_guest);
    }

    // ----------------------- Overrides ----------------------- //
    @Override
    protected void SetViewHolder(View view)
    {
        // Init Holder
        ListViewHolders.Guest holder = new ListViewHolders.Guest();
        holder.tvName           = (TextView)   view.findViewById(R.id.tv_name);
        holder.tvDesignation    = (TextView)   view.findViewById(R.id.tv_designation);
        holder.tvCompany        = (TextView)   view.findViewById(R.id.tv_company);

        // Assign holder to view
        view.setTag(holder);
    }

    @Override
    protected void SetView(View view, ListItemObject.Base data, int position)
    {
        ListItemObject.Guest listItem = (ListItemObject.Guest) data;
        ListViewHolders.Guest holder = (ListViewHolders.Guest) view.getTag();

        holder.tvName.setText(listItem.guest.name);
        holder.tvDesignation.setText(listItem.guest.designation);
        holder.tvCompany.setText(listItem.guest.company);
    }
}
