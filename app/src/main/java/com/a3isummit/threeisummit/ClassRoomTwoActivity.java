package com.a3isummit.threeisummit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ClassRoomTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_room_two);
    }
    // Button Click APIs
    public void ButtonClickBack(View view)
    {
        finish();
    }
}
