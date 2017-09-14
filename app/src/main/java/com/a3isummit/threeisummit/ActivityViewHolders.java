package com.a3isummit.threeisummit;

import android.view.View;
import android.widget.*;

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
        //TextViews for Register Page
        public EditText tv_username=null;
        public  EditText tv_mobile=null;
        public  EditText tv_email=null;
        public ImageView iv_image=null;

        //Button for onclick
        public Button b_register=null;

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

    public static class Invite extends Base {
        public RelativeLayout rlSyncProgress = null;
        public ProgressBar pbSync = null;
        public TextView tvSync = null;
        public ListView lvContacts = null;
    }

    public static class Feedback extends Base
    {
        public TextView fbTextview = null;
        public EditText fbEdittext1 = null;
        public EditText fbEdittext2 = null;
        public Button submit = null;
    }
}
