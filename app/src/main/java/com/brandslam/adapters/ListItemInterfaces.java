package com.brandslam.adapters;

/**
 * Created by Siddharth on 29-07-2017.
 * Interfaces for different list items.
 * These are used by list adapters to convey list events to user classes.
 */

public class ListItemInterfaces
{
    // List items with a single click event
    public interface ListItem
    {
        void onItemClick(ListItemObject.Base item);
    }
}
