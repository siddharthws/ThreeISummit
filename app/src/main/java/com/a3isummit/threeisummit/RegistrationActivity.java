package com.a3isummit.threeisummit;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.a3isummit.animations.AnimHelper;
import com.a3isummit.animations.AnimObject;
import com.a3isummit.animations.AnimationFactory;
import com.a3isummit.animations.PowerInterpolator;
import com.a3isummit.debug.Dbg;
import com.a3isummit.macros.MacRequestCodes;
import com.a3isummit.server.RegisterServerTask;

import com.a3isummit.server.ServerInterfaces;
import com.a3isummit.statics.AppPreferences;

public class RegistrationActivity extends         BaseActivity implements ServerInterfaces.IfaceBasic {
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "REGISTRATION_ACTIVITY";
    ViewAnimator viewAnimator=null;
    RegisterServerTask registerServerTask=null;
    Animation rotation=null;

    // ----------------------- Classes ---------------------------//
    // ----------------------- Interfaces ----------------------- //
    // ----------------------- Globals ----------------------- //
   
    // UI holder of activity view
    private ActivityViewHolders.Registration ui = null;
    private AnimHelper animHelper = null;

    // ----------------------- Constructor ----------------------- //
    // ----------------------- Overrides ----------------------- //
    @Override
    public void SetViewHolder()
    {
        Log.i(TAG,"inside Registration_Loading");
        // Init Holder
        ui = new ActivityViewHolders.Registration();

            ui.vwContent = getLayoutInflater().inflate(R.layout.activity_registration, null);


        // Set Holder
        holder = ui;
    }

    @Override
    public void Init()
    {
        animHelper =new AnimHelper(this);

        viewAnimator= (ViewAnimator) findViewById(R.id.register_view_flipper);
        animHelper.Animate(new AnimObject.Base(AnimObject.TYPE_ROTATE_WITH_FADE_IN, viewAnimator, new PowerInterpolator(false, 1),  null));
        /*while(true)
        {

            AnimationFactory.flipTransition(viewAnimator, AnimationFactory.FlipDirection.LEFT_RIGHT);
        }*/
        //rotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation_animation);
       // rotation.setFillAfter(true);
        //viewAnimator.startAnimation(rotation);

       /* long x=4000;                        //millisecond
        ui.iv_image= (ImageView) findViewById(R.id.register_image);
        //ObjectAnimator objectAnimatorx=ObjectAnimator.ofFloat(ui.iv_image,"x",420f);
        ObjectAnimator objectAnimatory=ObjectAnimator.ofFloat(ui.iv_image,View.ALPHA,1.0f,0.0f);
        ObjectAnimator rotation=ObjectAnimator.ofFloat(ui.iv_image,"rotation",360f);
        //objectAnimatorx.setDuration(x);
        //objectAnimatory.setDuration(x);

        rotation.setDuration(x);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(rotation);
        animatorSet.start();*/


        ui.tv_username= (EditText) findViewById(R.id.edit_text_username);
        ui.tv_mobile= (EditText) findViewById(R.id.edit_text_mobile);
        ui.tv_email= (EditText) findViewById(R.id.edit_text_email);
        ui.b_register= (Button) findViewById(R.id.button_register);
        ui.progressBar=(ProgressBar)findViewById(R.id.progressBar);
        ui.progressBar.setVisibility(View.INVISIBLE);

    }

    // ----------------------- Public APIs ----------------------- //
    public static void Start(BaseActivity activity)
    {
        BaseActivity.Start(activity, RegistrationActivity.class, -1, null, MacRequestCodes.CODE_ACTIVITY_RESULT_REGISTRATION, null);

    }



    // Button Click APis
    public void ButtonClickRegister(View view)
    {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(ui.tv_username.getText().toString().length()==0&&ui.tv_mobile.getText().toString().length()==0&&ui.tv_email.getText().toString().length()==0)
        {
            Toast.makeText(getApplicationContext(),"Fields cannot be Blank",Toast.LENGTH_SHORT).show();
        }
       else if(ui.tv_username.getText().toString().length()==0)
        {
            Toast.makeText(getApplicationContext(),"Name cannot be Blank",Toast.LENGTH_SHORT).show();
        }
        else if(ui.tv_mobile.getText().toString().length()==0)
        {
            Toast.makeText(getApplicationContext(),"Mobile cannot be Blank",Toast.LENGTH_SHORT).show();
        }
        else if (!ui.tv_email.getText().toString().trim().matches(emailPattern)||ui.tv_email.getText().toString().length()==0)
        {
            Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
        }
        else if(!TextUtils.isDigitsOnly(ui.tv_mobile.getText())){
            Toast.makeText(getApplicationContext(),"Use Digits Only",Toast.LENGTH_SHORT).show();
        }
        else if(ui.tv_mobile.getText().toString().length()!=10)
        {
            Toast.makeText(getApplicationContext(),"Invalid Number",Toast.LENGTH_SHORT).show();
        }
        else{
            ui.progressBar.setVisibility(View.VISIBLE);

            RegisterServerTask registerServerTask = new RegisterServerTask(this, ui.tv_mobile.getText().toString(), ui.tv_username.getText().toString(), ui.tv_email.getText().toString());
            registerServerTask.execute();

            registerServerTask.SetBasicInterface(this);
        }

    }
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    @Override
    public void onServerSuccess() {
        ui.progressBar.setVisibility(View.GONE);
        Dbg.Toast(this, "Registered Successfully", Toast.LENGTH_SHORT);
        setResult(RESULT_OK);
        finish();

    }

    @Override
    public void onServerFailure() {

        Toast.makeText(getApplicationContext(),"Cannot Register",Toast.LENGTH_SHORT).show();

    }

    // ----------------------- Private APIs ----------------------- //
}
