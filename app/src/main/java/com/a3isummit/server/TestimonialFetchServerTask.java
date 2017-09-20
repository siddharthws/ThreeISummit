package com.a3isummit.server;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.a3isummit.debug.Dbg;
import com.a3isummit.macros.MacServer;
import com.a3isummit.objects.TestimonialObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Siddharth on 02-09-2017.
 */

public class TestimonialFetchServerTask extends BaseServerTask {

    // ----------------------- Constants ----------------------- //
    private static final String TAG = "TESTIMONIAL_FETCH_FROM_SERVER";

    // Server info for this server task
    private static final ServerObject INFO = new ServerObject(  MacServer.REQUEST_TYPE_TESTIMONIAL_FETCH,
            MacServer.REQUEST_METHOD_POST,
            MacServer.BASE_SERVER_URL + MacServer.SERVLET_TESTIMONIAL);

    // ----------------------- Classes ---------------------------//
    // ----------------------- Interfaces ----------------------- //
    private ServerInterfaces.IfaceTestFetch listener = null;
    public void SetBasicInterface(ServerInterfaces.IfaceTestFetch listener)
    {
        this.listener = listener;
    }

    // ----------------------- Globals ----------------------- //
    private boolean                 bShowDialogs    = false;
    private boolean                 bSuccess        = false;

   private int app_id;
    ArrayList<TestimonialObject> testimonialObjects = new ArrayList<>();

    // ----------------------- Constructor ----------------------- //
    public TestimonialFetchServerTask(Context parentContext, int app_id)
    {
        super(parentContext, INFO);

        this.app_id = app_id;
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
        JSONArray ja = new JSONArray();
        try {
            if(responseJson!=null)
            {
                ja=responseJson.getJSONArray(MacServer.KEY_TESTIMONIAL_FETCH_ARRAY);
                for(int i=0; i<ja.length(); i++)
                {
                    JSONObject recordJson=ja.getJSONObject(i);
                    Log.i("array datas: ", recordJson.toString());
                    TestimonialObject ts = new TestimonialObject(recordJson.getString(MacServer.KEY_TESTIMONIAL_FETCH_NAME), recordJson.getString(MacServer.KEY_TESTIMONIAL_FETCH_SUGGESTION), recordJson.getString(MacServer.KEY_TESTIMONIAL_FETCH_DATE));
                    testimonialObjects.add(ts);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
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
                Log.i("arraylist: ", testimonialObjects.toString());
                listener.onServerSuccess(testimonialObjects);
            }
            else
            {
                // Show error toast
                Dbg.Toast(parentContext, "This Section would be updated after the Event....", Toast.LENGTH_SHORT);
                Dbg.Toast(parentContext, "Stay Tuned for it....", Toast.LENGTH_SHORT);

                // Call Failure Listener
                listener.onServerFailure();
            }
        }
    }

    // ----------------------- Public APIs ----------------------- //
    // ----------------------- Private APIs ----------------------- //
}
