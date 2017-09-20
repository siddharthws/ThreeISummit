package com.brandslam.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by Siddharth on 07-06-2017.
 */

public class TouchWrapper extends FrameLayout
{
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "TOUCH_WRAPPER";

    // ----------------------- Classes ---------------------------//
    // ----------------------- Interfaces ----------------------- //
    public interface IfaceTouchListener
    {
        void onSwipeLeft();
        void onSwipeRight();
    }
    private IfaceTouchListener listener = null;
    public void SetTouchListener (IfaceTouchListener listener)
    {
        this.listener = listener;
    }

    // ----------------------- Globals ----------------------- //
    private int MIN_DISTANCE = 100;
    float x1=0;
    float x2=0;
    float y1=0;
    float y2=0;
    // ----------------------- Constructor ----------------------- //
    public TouchWrapper(Context context)
    {
        super(context);
    }

    public TouchWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // ----------------------- Overrides ----------------------- //
    @Override
    public boolean dispatchTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1=event.getY();
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2=event.getY();
                float deltaX = x2 - x1;
                float deltaY=y2-y1;

                if(!(Math.abs(deltaY)>MIN_DISTANCE))
                {
                    if (Math.abs(deltaX) > MIN_DISTANCE)
                    {
                        // Left to Right swipe action
                        if (x2 > x1)
                        {
                            listener.onSwipeRight();


                        }

                        // Right to left swipe action
                        else
                        {
                            listener.onSwipeLeft();
                        }

                    }
                }

        }

        return super.dispatchTouchEvent(event);
    }

    // ----------------------- Public APIs ----------------------- //
    // ----------------------- Private APIs ----------------------- //
}
