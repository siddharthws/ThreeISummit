package com.brandslam.views;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brandslam.threeisummit.HomescreenActivity;
import com.brandslam.threeisummit.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Siddharth on 30-08-2017.
 */

public class RlCountdown extends RelativeLayout
{


    private TextView txtDay, txtHour, txtMinute, txtSecond;
    private TextView tvEventStart;
    private Handler handler;
    private  Runnable runnable;

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

    private void InitView(final Context context) {
        // Inflate Layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_rl_countdown, this, true);



        txtDay = (TextView) findViewById(R.id.txtDay);
        txtHour = (TextView) findViewById(R.id.txtHour);
        txtMinute = (TextView) findViewById(R.id.txtMinute);
        txtSecond = (TextView) findViewById(R.id.txtSecond);
        tvEventStart = (TextView) findViewById(R.id.tveventStart);


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    // Please here set your event date//YYYY-MM-DD
                    Date futureDate = dateFormat.parse("2017-09-25");
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        txtDay.setText("" + String.format("%02d", days));
                        txtHour.setText("" + String.format("%02d", hours));
                        txtMinute.setText("" + String.format("%02d", minutes));
                        txtSecond.setText("" + String.format("%02d", seconds));


                       switch((int)days)
                       {
                           case 24:
                                        String title="24 Days more to Go";
                                        String text="Open The Countdown";
                                        notifyCall(context,title,text);
                                        break;

                           case 20:
                                        title="20 Days more to Go";
                                        text="Open The Countdown";
                                        notifyCall(context,title,text);
                                        break;

                           case 10:
                                        title="10 more Days to Go";
                                        text="Open The Countdown";
                                        notifyCall(context,title,text);
                                        break;

                           case 5:
                                        title="5 more Days to Go";
                                        text="Open The Countdown";
                                        notifyCall(context,title,text);
                                        break;

                           case 2:
                                        title="2 Days more to Go";
                                        text="Open The Countdown";
                                        notifyCall(context,title,text);
                                        break;

                           case 1:
                                       title="1 more Day to Go";
                                       text="Open The Countdown";
                                       notifyCall(context,title,text);
                                       break;

                           case 0: {
                                    switch((int)hours) {

                                        case 12:
                                                    title = "12 Hours Remaining";
                                                    text = "Open The Countdown";
                                                    notifyCall(context, title, text);
                                                    break;

                                        case 6:
                                                    title = "6 Hours Remaining";
                                                    text = "Open The Countdown";
                                                    notifyCall(context, title, text);
                                                    break;

                                        case 3:
                                                    title = "3 Hours Remaining";
                                                    text = "Open The Countdown";
                                                    notifyCall(context, title, text);
                                                    break;

                                        case 1:
                                                    title = "1 Hour Remaining";
                                                    text = "Open The Countdown";
                                                    notifyCall(context, title, text);
                                                    break;
                                    }
                                    break;
                           }
                           default:     break;
                       }
                    } else {
                        tvEventStart.setVisibility(View.VISIBLE);
                        tvEventStart.setText("The Event started..Enjoy your Day");
                        textViewGone();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }

    public void textViewGone() {
        findViewById(R.id.LinearLayout1).setVisibility(View.GONE);
        findViewById(R.id.LinearLayout2).setVisibility(View.GONE);
        findViewById(R.id.LinearLayout3).setVisibility(View.GONE);
        findViewById(R.id.LinearLayout4).setVisibility(View.GONE);
        findViewById(R.id.textViewheader2).setVisibility(View.GONE);
    }

    public void notifyCall(Context context,String title, String text)
    {
        Intent notificationIntent = new Intent(context, HomescreenActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, (int)System.currentTimeMillis(), notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.reminder)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true);




        builder.setContentIntent(contentIntent).setAutoCancel(true);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }
}
