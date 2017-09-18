package com.a3isummit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.a3isummit.debug.Dbg;

import java.util.ArrayList;

/**
 * Base functionality of list adapter
 * This class should be the parent for all List Adpater classes.
 */

public abstract class BaseListAdapter extends BaseAdapter
{
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "BASE_LIST_ADAPTER";

    // ----------------------- Interfaces ----------------------- //
    // ----------------------- Globals ----------------------- //
    protected   Context                                   parentContext     = null;
    protected   ListView                                  lvList            = null;
    protected   ArrayList<ListItemObject.Base>            listData          = null;
    private     int                                       itemLayoutId      = 0;

    // ----------------------- Constructor ----------------------- //
    public BaseListAdapter(Context parentContext, ListView lvList, int itemLayoutId)
    {
        super();

        this.parentContext = parentContext;
        this.lvList = lvList;
        this.listData = new ArrayList<>();
        this.itemLayoutId = itemLayoutId;

        // Set List Adapter this this
        lvList.setAdapter(this);

    }




    // ----------------------- Abstracts ----------------------- //
    // Initialize view holder object for list item
    protected abstract void SetViewHolder(View view);

    // Populate UI of list item using list data
    protected abstract void SetView(View view, ListItemObject.Base data, int position);

    // ----------------------- Overrides ----------------------- //
    // BaseAdapter overrides
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup)
    {
        // Gte data for this position
        ListItemObject.Base data = (ListItemObject.Base) getItem(position);

        // Optimization to avoid inflating views every time list is scrolled
        View rowView = convertView;

        // Inflate View and Init View holder if required
        if (rowView == null)
        {
            // Inflate View
            LayoutInflater inflater = (LayoutInflater) parentContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(itemLayoutId, viewGroup, false);

            // Init View holder
            SetViewHolder(rowView);
        }

        // Populate view with data
        SetView(rowView, data, position);

        return rowView;
    }

    @Override
    public long getItemId(int position)
    {
        ListItemObject.Base baseItem = (ListItemObject.Base) getItem(position);

        if (baseItem != null)
        {
            return baseItem.id;
        }

        Dbg.error(TAG, "Cannot get item ID");

        return -1;
    }

    @Override
    public int getCount()
    {
        int count = 0;

        if (listData != null)
        {
            count = listData.size();
        }

        return count;
    }

    @Override
    public Object getItem(int position)
    {
        if (listData.size() > position)
        {
            return listData.get(position);
        }

        return null;
    }

    // ----------------------- Public APIs ----------------------- //
    // APIs for data list related operations
    public ArrayList<? extends ListItemObject.Base> GetList()
    {
        return listData;
    }

    public void Clear()
    {
        if (listData != null)
        {
            listData.clear();
        }

        this.notifyDataSetChanged();
    }

    public void Add(ListItemObject.Base data)
    {
        if (listData != null)
        {
            Add(listData.size(), data);
        }
    }

    public void Add(int position, ListItemObject.Base data)
    {
        if (listData != null)
        {
            listData.add(position, data);
        }

        this.notifyDataSetChanged();
    }

    public void AddAll(ArrayList<? extends ListItemObject.Base> data)
    {
        if (listData != null)
        {
            listData.addAll(data);
        }

        this.notifyDataSetChanged();
    }

    public void Remove(int position)
    {
        if ((position >= 0) && (position < listData.size()))
        {
            listData.remove(position);
        }

        this.notifyDataSetChanged();
    }

    public void Remove(ListItemObject.Base item)
    {
        if (listData.contains(item))
        {
            listData.remove(item);
        }

        this.notifyDataSetChanged();
    }

    public int GetItemPosition(ListItemObject.Base item)
    {
        int position = 0;

        for (ListItemObject.Base listItem : listData)
        {
            if (listItem.equals(item))
            {
                return position;
            }

            position++;
        }

        return -1;
    }

    // ----------------------- Private APIs ----------------------- //
    // ----------------------- Private APIs ----------------------- //
}
