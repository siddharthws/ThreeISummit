package com.a3isummit.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.a3isummit.threeisummit.R;

/**
 * Created by Siddharth on 14-09-2017.
 */

public class GuestCategoryAdapter extends BaseListAdapter {
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "GUEST_ADAPTER";

    // ----------------------- Interfaces ----------------------- //
    // ----------------------- Globals ----------------------- //

    // ----------------------- Constructor ----------------------- //
    public GuestCategoryAdapter(Context parentContext, ListView lvList)
    {
        super(parentContext, lvList, R.layout.activity_guest_category);
    }

    // ----------------------- Overrides ----------------------- //
    @Override
    protected void SetViewHolder(View view)
    {
        // Init Holder
        ListViewHolders.GuestCategory holder = new ListViewHolders.GuestCategory();
        holder.tvCategory         = (TextView)   view.findViewById(R.id.tv_category);


        // Assign holder to view
        view.setTag(holder);
    }

    @Override
    protected void SetView(View view, ListItemObject.Base data, int position)
    {
        ListItemObject.GuestCategory listItem = (ListItemObject.GuestCategory) data;
        ListViewHolders.GuestCategory holder = (ListViewHolders.GuestCategory) view.getTag();

        holder.tvCategory.setText(listItem.guestCat.category);
    }
}
