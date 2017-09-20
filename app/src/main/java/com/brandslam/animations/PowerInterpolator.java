package com.brandslam.animations;

/**
 * Created by Siddharth on 30-08-2017.
 */

public class PowerInterpolator extends BaseInterpolator
{
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "POWER_INTERPOLATOR";

    // ----------------------- Classes ---------------------------//
    // ----------------------- Interfaces ----------------------- //
    // ----------------------- Globals ----------------------- //
    public int power = 0;

    // ----------------------- Constructor ----------------------- //
    public PowerInterpolator(boolean bIn, int power)
    {
        super(bIn);
        this.power = power;
    }

    // ----------------------- Overrides ----------------------- //
    @Override
    public float GetIn(float t)
    {
        return In(t, power);
    }

    @Override
    public float GetOut(float t)
    {
        return Out(t, power);
    }

    // ----------------------- Public APIs ----------------------- //
    // ----------------------- Private APIs ----------------------- //
}
