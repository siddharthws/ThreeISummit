package com.brandslam.threeisummit;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;

/**
 * Created by Siddharth on 24-11-2016.
 */

public class AppLoadingTask extends AsyncTask<Void, Integer, Void>
{
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "APP_LOADING_TASK";

    // ----------------------- Classes ----------------------- //
    // ----------------------- Interfaces ----------------------- //
    public interface IfaceAppLoadingListener
    {
        void onLoadComplete();
    }
    private IfaceAppLoadingListener listener = null;
    public void SetOnAppLoadingListener(IfaceAppLoadingListener listener)
    {
        this.listener = listener;
    }

    // ----------------------- Globals ----------------------- //
    private Context parentContext = null;

    // ----------------------- Constructor ----------------------- //
    public AppLoadingTask(Context parentContext)
    {
        this.parentContext = parentContext;
    }

    // ----------------------- Overrides ----------------------- //
    // Overrides for AsyncTask

    @Override
    protected Void doInBackground(Void... params)
    {
        SystemClock.sleep(3000);

        return null;
    }

    @Override
    public void onPostExecute(Void result)
    {
        // Call Listener's load complete
        if (listener != null)
        {
            listener.onLoadComplete();
        }
    }

    // ----------------------- Public APIs ----------------------- //
    // ----------------------- Private APIs ----------------------- //
}
