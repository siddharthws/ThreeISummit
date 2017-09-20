package com.a3isummit.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.a3isummit.threeisummit.R;

/**
 * Created by Siddharth on 14-09-2017.
 */

public class TestimonialAdapter extends BaseListAdapter
{
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "GENERIC_LIST_ADAPTER";

    // ----------------------- Interfaces ----------------------- //
    // ----------------------- Globals ----------------------- //

    // ----------------------- Constructor ----------------------- //
    public TestimonialAdapter(Context parentContext, ListView lvList)
    {
        super(parentContext, lvList, R.layout.list_testimonial);
    }

    // ----------------------- Overrides ----------------------- //
    @Override
    protected void SetViewHolder(View view)
    {
        // Init Holder
        ListViewHolders.Testimonial holder = new ListViewHolders.Testimonial();
        holder.tvName      = (TextView)   view.findViewById(R.id.tv_name);
        holder.tvText      = (TextView)   view.findViewById(R.id.tv_text);
        holder.tvDate      = (TextView)   view.findViewById(R.id.tv_date);

        // Assign holder to view
        view.setTag(holder);
    }

    @Override
    protected void SetView(View view, ListItemObject.Base data, int position)
    {
        ListItemObject.Testimonial listItem = (ListItemObject.Testimonial) data;
        ListViewHolders.Testimonial holder = (ListViewHolders.Testimonial) view.getTag();

        holder.tvText.setText(listItem.testimonial.text);
        holder.tvName.setText(listItem.testimonial.name);
        holder.tvDate.setText(listItem.testimonial.date);
    }
}
