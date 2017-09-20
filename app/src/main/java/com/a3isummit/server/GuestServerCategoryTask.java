package com.a3isummit.server;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.a3isummit.debug.Dbg;
import com.a3isummit.macros.MacServer;
import com.a3isummit.objects.GuestObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Siddharth on 02-09-2017.
 */

public class GuestServerCategoryTask extends BaseServerTask {

    // ----------------------- Constants ----------------------- //
    private static final String TAG = "GUEST_LIST_FETCH";

    // Server info for this server task
    private static final ServerObject INFO = new ServerObject(  MacServer.REQUEST_TYPE_GUEST_LIST_CATEGORY
            ,
            MacServer.REQUEST_METHOD_POST,
            MacServer.BASE_SERVER_URL + MacServer.SERVLET_GUEST);

    // ----------------------- Classes ---------------------------//
    // ----------------------- Interfaces ----------------------- //
    private ServerInterfaces.IfaceGuestFetch listener = null;
    public void SetBasicInterface(ServerInterfaces.IfaceGuestFetch listener)
    {
        this.listener = listener;
    }

    // ----------------------- Globals ----------------------- //
    private boolean                 bShowDialogs    = false;
    private boolean                 bSuccess        = false;

    private int app_id;
    ArrayList<GuestObject.GuestObjectCategory> guestObjects = new ArrayList<>();

    // ----------------------- Constructor ----------------------- //
    public GuestServerCategoryTask(Context parentContext, int app_id)
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

        //Catch the response and parse it
        JSONArray ja = new JSONArray();
        try {
            ja=responseJson.getJSONArray(MacServer.KEY_GUEST_ARRAY);
            for(int i=0; i<ja.length(); i++)
            {
                JSONObject recordJson=ja.getJSONObject(i);
                GuestObject.GuestObjectCategory gs = new GuestObject.GuestObjectCategory(recordJson.getString(MacServer.KEY_GUEST_CATEGORY));
                guestObjects.add(gs);
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
                Log.i("arraylist: ", guestObjects.toString());
                listener.onServerSuccessCat(guestObjects);
            }
            else
            {
                // Show error toast
                Dbg.Toast(parentContext, "Failed to Retrieve Guest List...", Toast.LENGTH_SHORT);

                // Call Failure Listener
                listener.onServerFailure();
            }
        }
    }
    // ----------------------- Public APIs ----------------------- //
    // ----------------------- Private APIs ----------------------- //
}
