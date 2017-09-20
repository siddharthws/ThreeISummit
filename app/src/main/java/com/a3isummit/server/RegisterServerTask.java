package com.a3isummit.server;

import android.content.Context;
import android.widget.Toast;

import com.a3isummit.debug.Dbg;
import com.a3isummit.macros.MacServer;

import com.a3isummit.statics.AppPreferences;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Siddharth on 02-09-2017.
 */

public class RegisterServerTask extends BaseServerTask
{
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "SEND_SMS_SERVER_TASK";

    // Server info for this server task
    private static final ServerObject INFO = new ServerObject(  MacServer.REQUEST_TYPE_REGISTER,
                                                                MacServer.REQUEST_METHOD_POST,
                                                                MacServer.BASE_SERVER_URL + MacServer.SERVLET_USERS);

    // ----------------------- Classes ---------------------------//
    // ----------------------- Interfaces ----------------------- //
    private ServerInterfaces.IfaceBasic listener = null;
    public void SetBasicInterface(ServerInterfaces.IfaceBasic listener)
    {
        this.listener = listener;
    }

    // ----------------------- Globals ----------------------- //
    private boolean                 bShowDialogs    = false;
    private boolean                 bSuccess        = false;
    private String                  phoneNo = "", name = "", email_id = "";


    // ----------------------- Constructor ----------------------- //
    public RegisterServerTask(Context parentContext, String phoneNo, String name, String email_id)
    {
        super(parentContext, INFO);

        this.phoneNo = phoneNo;
        this.name = name;
        this.email_id=email_id;

    }

    // ----------------------- Overrides ----------------------- //

    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    public Void doInBackground (Void... params)
    {
        // Init Request JSON
        requestJson = new JSONObject();

        try
        {
            requestJson.put(MacServer.KEY_PHONE_NUMBER, phoneNo);
            requestJson.put(MacServer.KEY_USER_NAME, name);
            requestJson.put(MacServer.KEY_EMAIL_ID, email_id);
        }
        catch (JSONException e)
        {
            Dbg.error(TAG, "Error while putting data in JSON");
            return null;
        }

        // Call Super
        super.doInBackground(params);

        // Validate result
        if (IsResponseValid())
        {
            bSuccess = true;
        }

        return null;
    }

    @Override
    public void onPostExecute (Void result)
    {
        // Call listener
        if (listener != null)
        {
            // Validate Result
            if (bSuccess)
            {
                int app_id=-1;
                int app_gate_pass=-1;
                try {
                    app_id=responseJson.getInt(MacServer.KEY_APP_ID);
                    app_gate_pass=responseJson.getInt(MacServer.KEY_APP_GATE_PASS);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //set shared Preferences
                AppPreferences.SetUserInfo(parentContext,app_id,name,phoneNo,email_id, app_gate_pass);



                // Call Success Listener
                listener.onServerSuccess();
            }
            else
            {
                // Show error toast
                Dbg.Toast(parentContext, "Failed to register...", Toast.LENGTH_SHORT);

                // Call Failure Listener
                listener.onServerFailure();
            }
        }
    }

    // ----------------------- Public APIs ----------------------- //
    // ----------------------- Private APIs ----------------------- //
}
