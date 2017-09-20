package com.brandslam.server;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.brandslam.debug.Dbg;
import com.brandslam.macros.MacServer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Siddharth on 02-09-2017.
 */

public class FeedbackServerTask extends BaseServerTask
{
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "FEEDBACK_TO_SERVER";

    // Server info for this server task
    private static final ServerObject INFO = new ServerObject(  MacServer.REQUEST_TYPE_FEEDBACK,
            MacServer.REQUEST_METHOD_POST,
            MacServer.BASE_SERVER_URL + MacServer.SERVLET_FEEDBACK);

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

    private String name="user", suggestion="";
    private int app_id;

    // ----------------------- Constructor ----------------------- //
    public FeedbackServerTask(Context parentContext, int app_id, String name, String suggestion)
    {
        super(parentContext, INFO);

        this.app_id = app_id;
        this.name = name;
        this.suggestion=suggestion;
    }

    // ----------------------- Overrides ----------------------- //
    @Override
    public Void doInBackground (Void... params)
    {
        // Init Request JSON
        requestJson = new JSONObject();

        try
        {
            requestJson.put(MacServer.KEY_APP_ID, app_id);
            requestJson.put(MacServer.KEY_FEEDBACK_NAME, name);
            requestJson.put(MacServer.KEY_FEEDBACK_SUGGESTION, suggestion);
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
            Log.i("Kya re...", responseJson.toString());
            Log.i("bsucc", ""+bSuccess);
            if (bSuccess)
            {
              try {
                    int status=responseJson.getInt(MacServer.KEY_FEEDBACK_STATUS);
                    int requestType=responseJson.getInt(MacServer.KEY_REQUEST_TYPE);

                    if(status!= 0)
                    {
                        // Show error toast
                        Dbg.Toast(parentContext, "Thank You For your Feedback...", Toast.LENGTH_SHORT);


                        // Call Success Listener
                        listener.onServerSuccess();
                    } else
                    {
                        // Show error toast
                        Dbg.Toast(parentContext, "Failed to Send Feedback...", Toast.LENGTH_SHORT);

                        // Call Failure Listener
                        listener.onServerFailure();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                // Show error toast
                Dbg.Toast(parentContext, "Failed to Send Feedback...", Toast.LENGTH_SHORT);

                // Call Failure Listener
                listener.onServerFailure();
            }
        }
    }

    // ----------------------- Public APIs ----------------------- //
    // ----------------------- Private APIs ----------------------- //
}
