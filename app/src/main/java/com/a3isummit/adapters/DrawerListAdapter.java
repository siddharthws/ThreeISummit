package com.a3isummit.adapters;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a3isummit.threeisummit.R;

/**
 * Created by Siddharth on 23-11-2016.
 */

public class DrawerListAdapter extends BaseListAdapter implements AdapterView.OnItemClickListener {
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "DRAWER_LIST_ADAPTER";

    // ----------------------- Globals ----------------------- //
    private ListItemInterfaces.ListItem clickListener = null;
    public void SetItemClickListener(ListItemInterfaces.ListItem listener)
    {
        this.clickListener = listener;
    }

    // ----------------------- Globals ----------------------- //
    // ----------------------- Constructor ----------------------- //
    public DrawerListAdapter(Context parentContext, ListView lvList)
    {
        super(parentContext, lvList, R.layout.list_item_drawer);

        lvList.setOnItemClickListener(this);
    }

    // ----------------------- Overrides ----------------------- //
    @Override
    protected void SetViewHolder(View view)
    {
        // Init Holder
        ListViewHolders.Drawer holder   = new ListViewHolders.Drawer();

        // Find List Item view's UI elements
        holder.rlDrawerItem             = (RelativeLayout) view.findViewById(R.id.rl_drawer_item);
        holder.ivIcon                   = (ImageView) view.findViewById(R.id.ivDrawerIcon);
        holder.tvTitle                  = (TextView) view.findViewById(R.id.tvDrawerTitle);
        holder.vwItemSep                =  view.findViewById(R.id.vw_drawer_item_separator);
        holder.vwGroupSep               =  view.findViewById(R.id.vw_drawer_group_separator);

        // Assign holder to List item view
        view.setTag(holder);
    }

    @Override
    protected void SetView(View view, ListItemObject.Base data, int position)
    {
        ListItemObject.Generic itemData = (ListItemObject.Generic) data;
        ListViewHolders.Drawer   holder = (ListViewHolders.Drawer) view.getTag();

        // SetAlarm View properties
        if (itemData.type == ListItemObject.TYPE_DRAWER_GROUP_SEPARATOR)
        {
            // Group Separator View
            holder.vwItemSep.setVisibility(View.GONE);
            holder.vwGroupSep.setVisibility(View.VISIBLE);
            holder.rlDrawerItem.setVisibility(View.GONE);
        }
        else if (itemData.type == ListItemObject.TYPE_DRAWER_ITEM_SEPARATOR)
        {
            // Item Separator View
            holder.vwItemSep.setVisibility(View.VISIBLE);
            holder.vwGroupSep.setVisibility(View.GONE);
            holder.rlDrawerItem.setVisibility(View.GONE);
        }
        else
        {
            // Drawer Item View
            holder.vwItemSep.setVisibility(View.GONE);
            holder.vwGroupSep.setVisibility(View.GONE);
            holder.rlDrawerItem.setVisibility(View.VISIBLE);

            // Set title
            holder.tvTitle.setText(itemData.title);
        }
    }

    // Listview Overrides
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        // Get List Item Object
        ListItemObject.Base clickedItem = (ListItemObject.Base) getItem(position);

        // Call Listener
        if (clickListener != null)
        {
            clickListener.onItemClick(clickedItem);
        }
    }

    // ----------------------- Public APIs ----------------------- //
    // ----------------------- Private APIs ----------------------- //
}
