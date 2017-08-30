package com.a3isummit.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/*
 * View Holder Objects for List Item Views
 */

public class ListViewHolders
{
    public static class Generic
    {
        public ImageView            ivStart;
        public TextView    tvTitle;
        public ImageView            ivEnd;
    }

    // Drawer List Items
    public static class Drawer
    {
        public RelativeLayout       rlDrawerItem;
        public ImageView            ivIcon;
        public TextView             tvTitle;
        public View                 vwItemSep;
        public View                 vwGroupSep;
    }
}
