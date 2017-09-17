package com.a3isummit.views;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.a3isummit.adapters.DrawerListAdapter;
import com.a3isummit.adapters.ListItemInterfaces;
import com.a3isummit.adapters.ListItemObject;
import com.a3isummit.animations.AnimHelper;
import com.a3isummit.animations.AnimObject;
import com.a3isummit.animations.PowerInterpolator;
import com.a3isummit.threeisummit.R;

/**
 * Created by Siddharth on 17-04-2017.
 */

public class RlDrawer extends       RelativeLayout
                        implements  ListItemInterfaces.ListItem
{
    // ----------------------- Constants ----------------------- //
    public static final String TAG = "RL_DRAWER";

    // Macros for action IDs for drawer items
    public static final int DRAWER_ACTION_NONE              = 0;
    public static final int DRAWER_ACTION_INVITE            = 1;
    public static final int DRAWER_ACTION_FEEDBACK          = 2;
    public static final int DRAWER_ACTION_GALLERY         = 3;
    public static final int DRAWER_ACTION_LOCATE            = 4;
    public static final int DRAWER_ACTION_ABOUT             = 5;
    public static final int DRAWER_ACTION_GATE_PASS         = 6;
    public static final int DRAWER_ACTION_EXIT              = 7;
    public static final int DRAWER_ACTION_TESTIMONIAL       = 8;
    public static final int DRAWER_ACTION_GUEST             = 9;

    // Drawer List Items
    public static final ListItemObject.Generic[] drawerItems =
            {
                    new ListItemObject.Generic(ListItemObject.TYPE_DRAWER_ITEM,             DRAWER_ACTION_INVITE,                   "Invite",           0,                                  0, android.R.color.transparent),
                    new ListItemObject.Generic(ListItemObject.TYPE_DRAWER_ITEM,             DRAWER_ACTION_TESTIMONIAL,              "Testimonials",     0,                                  0, android.R.color.transparent),
                    new ListItemObject.Generic(ListItemObject.TYPE_DRAWER_ITEM,             DRAWER_ACTION_GUEST,                    "Guests",           0,                                  0, android.R.color.transparent),
                    new ListItemObject.Generic(ListItemObject.TYPE_DRAWER_ITEM,             DRAWER_ACTION_FEEDBACK,                 "Feedback",         0,                                  0, android.R.color.transparent),
                    new ListItemObject.Generic(ListItemObject.TYPE_DRAWER_ITEM,             DRAWER_ACTION_GALLERY,                 "Gallery",         0,                                  0, android.R.color.transparent),
                    new ListItemObject.Generic(ListItemObject.TYPE_DRAWER_ITEM,             DRAWER_ACTION_LOCATE,                   "Locate Us",        0,                                  0, android.R.color.transparent),
                    new ListItemObject.Generic(ListItemObject.TYPE_DRAWER_ITEM,             DRAWER_ACTION_ABOUT,                    "About Us",         0,                                  0, android.R.color.transparent),
                    new ListItemObject.Generic(ListItemObject.TYPE_DRAWER_ITEM,             DRAWER_ACTION_GATE_PASS,                "Gate Pass",        0,                                  0, android.R.color.transparent),
                    new ListItemObject.Generic(ListItemObject.TYPE_DRAWER_ITEM,             DRAWER_ACTION_EXIT,                     "Exit",             0,                                  0, android.R.color.transparent),
            };

    // ----------------------- Classes ---------------------------//
    // ----------------------- Interfaces ----------------------- //
    public interface DrawerItemClickListener
    {
        void onDrawerItemClick(int actionId);
    }
    private DrawerItemClickListener listener = null;
    public void SetItemClickListener(DrawerItemClickListener listener)
    {
        this.listener = listener;
    }

    // ----------------------- Globals ----------------------- //
    // UI
    private View vwCover = null;
    private ListView lvDrawer = null;
    private RelativeLayout rlListContainer = null;

    private DrawerListAdapter   listAdapter   = null;
    private AnimHelper          animHelper  = null;

    // ----------------------- Constructor ----------------------- //

    public RlDrawer(Context context)
    {
        super(context);
        InitView(context);
    }

    public RlDrawer(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        InitView(context);
    }

    public RlDrawer(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        InitView(context);
    }

    // ----------------------- Overrides ----------------------- //

    @Override
    public void onItemClick(ListItemObject.Base item)
    {
        // Call listener
        if (listener != null)
        {
            listener.onDrawerItemClick(item.id);
        }

        // Close Drawer
        Close();
    }

    public boolean IsShowing()
    {
        return (vwCover.getVisibility() == VISIBLE);
    }

    // ----------------------- Public APIs ----------------------- //
    public void Open()
    {
        animHelper.Animate(new AnimObject.Slide(rlListContainer, new PowerInterpolator(true, 2), -1 * GetListWidth(), 0, AnimObject.Slide.SLIDE_AXIS_X, null));

        // Make List visible
        rlListContainer.setVisibility(View.VISIBLE);

        // Fade in cover
        animHelper.Animate(new AnimObject.Base(AnimObject.TYPE_FADE_IN, vwCover, new PowerInterpolator(true, 1), null));
    }

    public void Close()
    {
        // Don't perform hide if already hidden
        if (vwCover.getVisibility() != VISIBLE)
        {
            return;
        }

        // Fade out cover
        animHelper.Animate(new AnimObject.Base(AnimObject.TYPE_FADE_OUT, vwCover, new PowerInterpolator(true, 1), null));

        // Start exit animation for drawer
        animHelper.Animate(new AnimObject.Slide(rlListContainer, new PowerInterpolator(false, 3), 0, -1 * GetListWidth(), AnimObject.Slide.SLIDE_AXIS_X, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                rlListContainer.setVisibility(GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }));
    }

    // ----------------------- Private APIs ----------------------- //
    private void InitView(Context context)
    {
        // Inflate Layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_rl_drawer, this, true);

        // SetAlarm UI
        vwCover = (View) view.findViewById(R.id.vw_drawer_cover);
        lvDrawer = (ListView) view.findViewById(R.id.lv_drawer);
        rlListContainer = (RelativeLayout) view.findViewById(R.id.rl_drawer_list_container);

        // Initialize List
        listAdapter = new DrawerListAdapter(context, lvDrawer);

        // Add List Data
        for (int i = 0; i < drawerItems.length; i++)
        {
            listAdapter.Add(RlDrawer.drawerItems[i]);

        }

        // Set listeners
        listAdapter.SetItemClickListener(this);

        // Init runnable
        animHelper = new AnimHelper(getContext());

        vwCover.setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    Close();
                }
                return false;
            }
        });

        // Hide card usign translation
        rlListContainer.setVisibility(View.GONE);
    }

    private int GetListWidth()
    {
        if (getContext() != null)
        {
            return getContext().getResources().getDimensionPixelSize(R.dimen.drawer_list_width);
        }

        return 0;
    }
}
