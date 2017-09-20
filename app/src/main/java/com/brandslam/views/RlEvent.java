package com.brandslam.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.brandslam.threeisummit.R;

/**
 * Created by Siddharth on 30-08-2017.
 */

public class RlEvent extends RelativeLayout
{
    public RlEvent(Context context) {
        super(context);
        InitView(context);
    }

    public RlEvent(Context context, AttributeSet attrs) {
        super(context, attrs);
        InitView(context);
    }

    public RlEvent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        InitView(context);
    }

    private void InitView(Context context)
    {
        // Inflate Layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_rl_event, this, true);

    }



}