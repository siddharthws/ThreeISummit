package com.a3isummit.threeisummit;

import android.view.View;
import android.widget.*;

import com.a3isummit.views.RlAbout;
import com.a3isummit.views.RlCountdown;
import com.a3isummit.views.RlDrawer;
import com.a3isummit.views.RlEvent;
import com.a3isummit.views.TouchWrapper;

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
        public ImageButton ibCountdown=null;
        public ImageButton ibEvent=null;
        public ImageButton ibAbout=null;

        // Drawer Layout
        public RlDrawer rlDrawer = null;

        // Contents
        public RlCountdown rlCountdown = null;
        public RlAbout rlAbout = null;
        public RlEvent rlEvent = null;
        public TouchWrapper tw = null;
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

    public static class Testimonial extends Base
    {
        public ListView lvList = null;
    }

    public static class TestimonialAdd extends Base
    {
        public TextView tmTextview = null;
        public Button submit = null;
    }


    public static class Guest extends Base
    {
        public ListView lvList = null;
    }

    public static class GatePass extends Base
    {
        public ImageView imageView =null;
        public TextView textView = null;
        public Button submit = null;
    }

    public static class Gallery extends Base
    {
        public ImageView imageView1 =null;
        public ImageView imageView2 =null;
        public ImageView imageView3 =null;

        public Button submit = null;
    }
}
