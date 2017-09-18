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

    // Testimonial List Items
    public static class Testimonial
    {
        public TextView tvName = null;
        public TextView tvDate = null;
        public TextView tvText = null;
    }

    // Testimonial List Items
    public static class Guest
    {
        public TextView tvName = null;
        public TextView tvDesignation = null;
        public TextView tvCompany = null;
    }
    public static class GuestCategory
    {
        public TextView tvCategory = null;

    }
}
