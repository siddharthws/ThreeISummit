package com.brandslam.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.brandslam.threeisummit.R;

/**
 * Created by Siddharth on 17-02-2017.
 */

public class GenericListAdapter extends BaseListAdapter
{
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "GENERIC_LIST_ADAPTER";

    // ----------------------- Interfaces ----------------------- //
    // ----------------------- Globals ----------------------- //

    // ----------------------- Constructor ----------------------- //
    public GenericListAdapter(Context parentContext, ListView lvList)
    {
        super(parentContext, lvList, R.layout.list_generic);
    }

    // ----------------------- Overrides ----------------------- //
    @Override
    protected void SetViewHolder(View view)
    {
        // Init Holder
        ListViewHolders.Generic holder = new ListViewHolders.Generic();
        holder.tvTitle      = (TextView)   view.findViewById(R.id.tv_title);
        holder.ivStart      = (ImageView)           view.findViewById(R.id.ivListIconStart);
        holder.ivEnd        = (ImageView)           view.findViewById(R.id.ivListIconEnd);

        // Assign holder to view
        view.setTag(holder);
    }

    @Override
    protected void SetView(View view, ListItemObject.Base data, int position)
    {
        ListItemObject.Generic genericItem = (ListItemObject.Generic) data;
        ListViewHolders.Generic holder = (ListViewHolders.Generic) view.getTag();

        // Set background given by caller
        if (genericItem.background != 0)
        {
            view.setBackground(ContextCompat.getDrawable(parentContext, genericItem.background));
        }

        holder.ivStart.setVisibility(View.VISIBLE);
        holder.ivEnd.setVisibility(View.VISIBLE);

        // Set Item text
        holder.tvTitle.setText(genericItem.title);

        // Set item images
        if (genericItem.startImageId != 0)
        {
            holder.ivStart.setImageDrawable(ContextCompat.getDrawable(parentContext, genericItem.startImageId));
        }
        else
        {
            holder.ivStart.setVisibility(View.GONE);
        }

        if (genericItem.endImageId != 0)
        {
            holder.ivEnd.setImageDrawable(ContextCompat.getDrawable(parentContext, genericItem.endImageId));
        }
        else
        {
            holder.ivEnd.setVisibility(View.GONE);
        }
    }

    // ----------------------- Public APIs ----------------------- //
    // ----------------------- Private APIs ----------------------- //
    // ----------------------- Private APIs ----------------------- //
}
