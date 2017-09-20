package com.a3isummit.threeisummit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.a3isummit.debug.Dbg;

public class SplashScreen extends AppCompatActivity implements SplashTask.OnSplashScreen {
    private SplashTask task=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        task=new SplashTask(this);
        task.setSplashlistner(this);
        task.execute();


        String packageName = getApplicationContext().getPackageName();
        Toast.makeText(getApplicationContext(),"Welcome to Indira "+packageName,Toast.LENGTH_SHORT).show();


    }

    @Override
    public void ClickSplashscreen() {
        Intent i=new Intent(SplashScreen.this,AppLoadingActivity.class);
        startActivity(i);
        finish();
    }
}
