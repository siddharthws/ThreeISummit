package com.brandslam.threeisummit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.brandslam.threeisummit.R;

public class ClassRoomOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_room_one);
    }

    // Button Click APIs
    public void ButtonClickBack(View view)
    {
        finish();
    }
}
