package com.a3isummit.server;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.a3isummit.debug.Dbg;
import com.a3isummit.macros.MacServer;
import org.json.JSONException;
import org.json.JSONObject;


        import android.content.Context;
        import android.util.Log;
        import android.widget.Toast;

        import com.a3isummit.debug.Dbg;
        import com.a3isummit.macros.MacServer;

        import com.a3isummit.statics.AppPreferences;
        import org.json.JSONException;
        import org.json.JSONObject;

        import javax.crypto.Mac;

/**
 * Created by Siddharth on 02-09-2017.
 */

public class TestimonialAddServerTask extends BaseServerTask {

    // ----------------------- Constants ----------------------- //
    private static final String TAG = "TESTIMONIAL_ADD_TO_SERVER";

    // Server info for this server task
    private static final ServerObject INFO = new ServerObject(  MacServer.REQUEST_TYPE_TESTIMONIAL_ADD,
            MacServer.REQUEST_METHOD_POST,
            MacServer.BASE_SERVER_URL + MacServer.SERVLET_TESTIMONIAL);

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

    private String name="user", testimonial="";
    private int app_id;

    // ----------------------- Constructor ----------------------- //
    public TestimonialAddServerTask(Context parentContext, int app_id, String name, String testimonial)
    {
        super(parentContext, INFO);

        this.app_id = app_id;
        this.name = name;
        this.testimonial=testimonial;
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
            requestJson.put(MacServer.KEY_TESTIMONIAL_NAME, name);
            requestJson.put(MacServer.KEY_TESTIMONIAL_SUGGESTION, testimonial);
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
                    int status=responseJson.getInt(MacServer.KEY_TESTIMONIAL_STATUS);
                    int requestType=responseJson.getInt(MacServer.KEY_REQUEST_TYPE);

                    if(status!= 0)
                    {
                        // Show error toast
                        Dbg.Toast(parentContext, "Thank You For your Interest in the Event...", Toast.LENGTH_SHORT);


                        // Call Success Listener
                        listener.onServerSuccess();
                    } else
                    {
                        // Show error toast
                        Dbg.Toast(parentContext, "Failed to Send your Views...", Toast.LENGTH_SHORT);

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
                Dbg.Toast(parentContext, "Failed to Send your views...", Toast.LENGTH_SHORT);

                // Call Failure Listener
                listener.onServerFailure();
            }
        }
    }

    // ----------------------- Public APIs ----------------------- //
    // ----------------------- Private APIs ----------------------- //
}
