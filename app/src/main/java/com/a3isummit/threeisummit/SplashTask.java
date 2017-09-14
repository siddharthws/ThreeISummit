package com.a3isummit.threeisummit;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by uchiha on 02-04-2017.
 */

public class SplashTask extends AsyncTask<Void,Void,Void>{
    private static final String TAG="hii";


    private SplashScreen splash=null;
    public SplashTask(SplashScreen splashscreen) {

    }


    public  interface OnSplashScreen{

        void ClickSplashscreen();

    }

    public  OnSplashScreen list;
    public void setSplashlistner(OnSplashScreen list)
    {
        this.list=list;
    }
    @Override
    public void  onPreExecute()
    {
        Log.e(TAG,"pre");
        return;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.e(TAG,"back");
        SystemClock.sleep(3000);

        return null;
    }

    @Override
    public void  onPostExecute(Void result)
    {
        Log.e(TAG,"post");
        if (list !=null)
        {
            list.ClickSplashscreen();

        }


    }

}
