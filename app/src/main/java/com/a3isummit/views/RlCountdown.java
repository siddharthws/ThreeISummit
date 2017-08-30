package com.a3isummit.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.a3isummit.threeisummit.R;

/**
 * Created by Siddharth on 30-08-2017.
 */

public class RlCountdown extends RelativeLayout
{
    public RlCountdown(Context context) {
        super(context);
        InitView(context);
    }

    public RlCountdown(Context context, AttributeSet attrs) {
        super(context, attrs);
        InitView(context);
    }

    public RlCountdown(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        InitView(context);
    }

    private void InitView(Context context)
    {
        // Inflate Layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_rl_countdown, this, true);

    }
}
