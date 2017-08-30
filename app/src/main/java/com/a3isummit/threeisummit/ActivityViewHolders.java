package com.a3isummit.threeisummit;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a3isummit.views.RlAbout;
import com.a3isummit.views.RlCountdown;
import com.a3isummit.views.RlDrawer;
import com.a3isummit.views.RlEvent;

/**
 * Created by Siddharth on 28-06-2017.
 */

public class ActivityViewHolders
{
    public static abstract class Base
    {
        // Content
        public FrameLayout      flContentContainer  = null;
        public View             vwContent           = null;
    }

    public static class AppLoading extends Base
    {
        // Progress bar for app load
        public ProgressBar pbLoading = null;

        // Loading text
        public TextView tvLoading = null;
    }

    public static class Registration extends Base
    {
    }

    public static class Homescreen extends Base
    {
        // Toolbar Buttons
        public ImageButton ibDrawer     = null;

        // Drawer Layout
        public RlDrawer rlDrawer = null;

        // Contents
        public RlCountdown rlCountdown = null;
        public RlAbout rlAbout = null;
        public RlEvent rlEvent = null;
    }

    public static class Invite extends Base
    {
        public RelativeLayout rlSyncProgress = null;
        public ProgressBar pbSync = null;
        public TextView tvSync = null;
        public ListView lvContacts = null;
    }
}
