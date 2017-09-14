package com.a3isummit.threeisummit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity implements SplashTask.OnSplashScreen {
    private SplashTask task=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        task=new SplashTask(this);
        task.setSplashlistner(this);
        task.execute();
    }

    @Override
    public void ClickSplashscreen() {
        Intent i=new Intent(SplashScreen.this,AppLoadingActivity.class);
        startActivity(i);
        finish();
    }
}
