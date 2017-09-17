package com.a3isummit.threeisummit;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3isummit.macros.MacRequestCodes;
import com.a3isummit.statics.AppPreferences;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class GatePassActivity extends BaseActivity {
    private ActivityViewHolders.GatePass ui = null;

    public void SetViewHolder()
    {
        // Init holder
        ui = new ActivityViewHolders.GatePass();
        ui.vwContent = getLayoutInflater().inflate(R.layout.activity_gate_pass, null);


        // Find all views form layout
        ui.imageView = (ImageView) ui.vwContent.findViewById(R.id.imageView);
        ui.textView = (TextView) ui.vwContent.findViewById(R.id.textView2);
        ui.submit = (Button) ui.vwContent.findViewById(R.id.btg1);

        // Set Holder
        holder = ui;
    }

    @Override
    public void Init()
    {
        AppPreferences.Init(this);
        int app_gate_pass=AppPreferences.getApp_GatePass();
        QRCodeWriter writer = new QRCodeWriter();
        try {
            ByteMatrix bitMatrix = writer.encode(""+app_gate_pass, BarcodeFormat.QR_CODE, 512, 512);
            int width = 512;
            int height = 512;
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (bitMatrix.get(x, y)==0)
                        bmp.setPixel(x, y, Color.BLACK);
                    else
                        bmp.setPixel(x, y, Color.WHITE);
                }
            }
            ui.imageView.setImageBitmap(bmp);
            ui.textView.setText(""+app_gate_pass);
        } catch (WriterException e) {
            Log.e("QR ERROR", ""+e);
        }
    }

    public static void Start(BaseActivity activity)
    {
        BaseActivity.Start(activity, GatePassActivity.class, -1, null, MacRequestCodes.INVALID, null);
    }


    // Button Click APIs
    public void GatePassButtonClickBack(View view)
    {
        finish();
    }

}
