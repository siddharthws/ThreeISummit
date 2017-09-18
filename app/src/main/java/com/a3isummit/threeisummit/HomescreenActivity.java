package com.a3isummit.threeisummit;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import android.widget.RelativeLayout;
import android.widget.Toast;
import com.a3isummit.animations.AnimHelper;
import com.a3isummit.animations.AnimObject;
import com.a3isummit.animations.PowerInterpolator;
import com.a3isummit.macros.MacRequestCodes;
import com.a3isummit.statics.AppStatics;
import com.a3isummit.views.RlAbout;
import com.a3isummit.views.RlCountdown;
import com.a3isummit.views.RlDrawer;
import com.a3isummit.views.RlEvent;
import com.a3isummit.views.TouchWrapper;

/**
 * Homescreen activity
 */

public class HomescreenActivity extends     BaseActivity implements RlDrawer.DrawerItemClickListener, TouchWrapper.IfaceTouchListener {
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "HOMESCREEN_ACTIVITY";

    // ----------------------- Interfaces ----------------------- //
    // ----------------------- Globals ----------------------- //
    private ActivityViewHolders.Homescreen ui = null;
    private AnimHelper animHelper = null;

    // ----------------------- Overrides ----------------------- //
    @Override
    public void SetViewHolder()
    {
        // Init holder
        ui = new ActivityViewHolders.Homescreen();
        ui.vwContent = getLayoutInflater().inflate(R.layout.activity_homescreen, null);

        // Find all views form layout
        ui.ibDrawer         = (ImageButton)     ui.vwContent.findViewById(R.id.ib_toolbar_drawer);
        ui.rlDrawer         = (RlDrawer)        ui.vwContent.findViewById(R.id.rl_drawer);
        ui.rlCountdown      = (RlCountdown)     ui.vwContent.findViewById(R.id.rl_countdown);
        ui.rlAbout              = (RlAbout)         ui.vwContent.findViewById(R.id.rl_about);
        ui.rlEvent              = (RlEvent)         ui.vwContent.findViewById(R.id.rl_event);
        ui.ibAbout              =(ImageButton) ui.vwContent.findViewById(R.id.ibAbout);
        ui.ibEvent              =(ImageButton) ui.vwContent.findViewById(R.id.ibEvent);
        ui.ibCountdown      =(ImageButton) ui.vwContent.findViewById(R.id.ibCountdown);
        ui.tw = (TouchWrapper) ui.vwContent.findViewById(R.id.touch_wrapper);

        // Set Holder
        holder = ui;
    }

    @Override
    public void Init()
    {
        // Initialize Animation Helper
        animHelper = new AnimHelper(this);

        // Set Listeners
        ui.rlDrawer.SetItemClickListener(this);
        ui.tw.SetTouchListener(this);
    }

    // ----------------------- Public APIs ----------------------- //
    // Helper API to start this activity
    public static void Start(BaseActivity activity)
    {
        BaseActivity.Start(activity, HomescreenActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TOP, null, MacRequestCodes.INVALID, null);
    }

    // Button Click APIs
    public void ButtonClickDrawer(View view)
    {
        // Open Drawer
        ui.rlDrawer.Open();
    }

    View slideOutView = null;
    boolean bAnimating = false;

    public void ButtonClickCountdown(View view)
    {
        if (bAnimating) {
            return;
        }

        if (ui.rlCountdown.getVisibility() == View.VISIBLE) {
            slideOutView = ui.rlCountdown;
        } else if (ui.rlAbout.getVisibility() == View.VISIBLE) {
            slideOutView = ui.rlAbout;
        } else if (ui.rlEvent.getVisibility() == View.VISIBLE) {
            slideOutView = ui.rlEvent;
        }

        if (slideOutView.equals(ui.rlCountdown)) {
            return;
        }

        // Slide In Animation
        ui.rlCountdown.setVisibility(View.VISIBLE);
        animHelper.Animate(new AnimObject.Slide(ui.rlCountdown,new PowerInterpolator(true,2),-1* AppStatics.SCREEN_SIZE.x,0,AnimObject.Slide.SLIDE_AXIS_X,null));

        // Slide Out Animation
        bAnimating = true;
        animHelper.Animate(new AnimObject.Slide(
                    slideOutView,
                    new PowerInterpolator(true, 2),
                    0, 1 * AppStatics.SCREEN_SIZE.x,
                    AnimObject.Slide.SLIDE_AXIS_X,
                    new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        slideOutView.setVisibility(View.GONE);
                        bAnimating = false;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }));


        // Set Button Props
        ui.ibAbout.setBackgroundColor(Color.TRANSPARENT);
        ui.ibEvent.setBackgroundColor(Color.TRANSPARENT);
        ui.ibCountdown.setBackgroundColor(getResources().getColor(R.color.green));
    }

    public void ButtonClickAbout(View view)
    {
        if (bAnimating) {
            return;
        }

        if (ui.rlAbout.getVisibility() == View.VISIBLE) {
            slideOutView = ui.rlAbout;
        } else if (ui.rlCountdown.getVisibility() == View.VISIBLE) {
            slideOutView = ui.rlCountdown;
        } else if (ui.rlEvent.getVisibility() == View.VISIBLE) {
            slideOutView = ui.rlEvent;
        }

        if (slideOutView.equals(ui.rlAbout)) {
            return;
        }

        int mul = 1;
        if (slideOutView.equals(ui.rlCountdown)) {
            mul = -1;
        }

        // Slide In Animation
        ui.rlAbout.setVisibility(View.VISIBLE);
        animHelper.Animate(new AnimObject.Slide(ui.rlAbout,new PowerInterpolator(true,2),-1 * mul * AppStatics.SCREEN_SIZE.x,0,AnimObject.Slide.SLIDE_AXIS_X,null));

        // Slide Out Animation
        bAnimating = true;
        animHelper.Animate(new AnimObject.Slide(
                slideOutView,
                new PowerInterpolator(true, 2),
                0, 1 * mul * AppStatics.SCREEN_SIZE.x,
                AnimObject.Slide.SLIDE_AXIS_X,
                new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        bAnimating = false;
                        slideOutView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }));


        ui.ibEvent.setBackgroundColor(Color.TRANSPARENT);
        ui.ibCountdown.setBackgroundColor(Color.TRANSPARENT);
        ui.ibAbout.setBackgroundColor(getResources().getColor(R.color.green));
    }

    public void ButtonClickEvent(View view)
    {
        if (bAnimating) {
            return;
        }

        if (ui.rlEvent.getVisibility() == View.VISIBLE) {
            slideOutView = ui.rlEvent;
        } else if (ui.rlAbout.getVisibility() == View.VISIBLE) {
            slideOutView = ui.rlAbout;
        } else if (ui.rlCountdown.getVisibility() == View.VISIBLE) {
            slideOutView = ui.rlCountdown;
        }

        if (slideOutView.equals(ui.rlEvent)) {
            return;
        }
        // Slide In Animation
        ui.rlEvent.setVisibility(View.VISIBLE);
        animHelper.Animate(new AnimObject.Slide(ui.rlEvent,new PowerInterpolator(true,2),1* AppStatics.SCREEN_SIZE.x,0,AnimObject.Slide.SLIDE_AXIS_X,null));


        // Slide Out Animation
        bAnimating = true;
        animHelper.Animate(new AnimObject.Slide(
                slideOutView,
                new PowerInterpolator(true, 2),
                0, -1 * AppStatics.SCREEN_SIZE.x,
                AnimObject.Slide.SLIDE_AXIS_X,
                new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        bAnimating = false;
                        slideOutView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }));


        ui.ibAbout.setBackgroundColor(Color.TRANSPARENT);
        ui.ibCountdown.setBackgroundColor(Color.TRANSPARENT);
        ui.ibEvent.setBackgroundColor(getResources().getColor(R.color.green));
    }

    @Override
    public void onDrawerItemClick(int actionId)
    {
        switch (actionId)
        {
            case RlDrawer.DRAWER_ACTION_TESTIMONIAL:
            {
                TestimonialActivity.Start(this);
                break;
            }

            case RlDrawer.DRAWER_ACTION_GALLERY:
                {

                }
                break;
            case RlDrawer.DRAWER_ACTION_RATE_US:
            {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.whatsapp#details-reviews")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + "com.whatsapp#details-reviews")));
                }

            }
            break;


            case RlDrawer.DRAWER_ACTION_GUEST:
            {
                GuestActivity.Start(this);
                break;
            }

            case RlDrawer.DRAWER_ACTION_FEEDBACK:
            {
                FeedbackActivity.Start(this);
                break;
            }

            case RlDrawer.DRAWER_ACTION_GATE_PASS:
            {
                GatePassActivity.Start(this);
                break;
            }

            case RlDrawer.DRAWER_ACTION_EXIT:
            {
                finish();
                break;
            }

            case RlDrawer.DRAWER_ACTION_LOCATE:
            {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=18.611845,73.748792(3I Summit)");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
                break;
            }

            case RlDrawer.DRAWER_ACTION_INVITE:
            {
                InviteActivity.Start(this);
                break;
            }
            case RlDrawer.DRAWER_ACTION_ABOUT:
            {
                ButtonClickAbout(null);
            }
            break;
        }
    }

    @Override
    public void onSwipeLeft() {

        if (ui.rlCountdown.getVisibility() == View.VISIBLE) {
            slideOutView = ui.rlCountdown;
        } else if (ui.rlAbout.getVisibility() == View.VISIBLE) {
            slideOutView = ui.rlAbout;
        } else if (ui.rlEvent.getVisibility() == View.VISIBLE) {
            slideOutView = ui.rlEvent;
        }

        if(slideOutView.equals(ui.rlCountdown))
        {
            ButtonClickAbout(null);
        }
        else if(slideOutView.equals(ui.rlAbout))
        {
            ButtonClickEvent(null);
        }
    }

    @Override
    public void onSwipeRight() {

        if (ui.rlCountdown.getVisibility() == View.VISIBLE) {
            slideOutView = ui.rlCountdown;
        } else if (ui.rlAbout.getVisibility() == View.VISIBLE) {
            slideOutView = ui.rlAbout;
        } else if (ui.rlEvent.getVisibility() == View.VISIBLE) {
            slideOutView = ui.rlEvent;
        }
        if(slideOutView.equals(ui.rlEvent))
        {
            ButtonClickAbout(null);

        }
        else if(slideOutView.equals(ui.rlAbout))
        {
            ButtonClickCountdown(null);

        }
    }
}
