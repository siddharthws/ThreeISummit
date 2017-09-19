package com.a3isummit.threeisummit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DhruvActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dhruv);
    }
    // Button Click APIs
    public void ButtonClickBack(View view)
    {
        finish();
    }
}
