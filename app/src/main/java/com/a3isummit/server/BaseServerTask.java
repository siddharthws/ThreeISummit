package com.a3isummit.server;

import android.content.Context;
import android.os.AsyncTask;

import com.a3isummit.debug.Dbg;
import com.a3isummit.macros.MacServer;
import com.a3isummit.statics.InternetHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Siddharth on 09-04-2017.
 */

public abstract class BaseServerTask extends AsyncTask<Void, Void, Void>
{
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "BASE_SERVER_TASK";

    // Error codes for connection
    public static final int ERROR_CODE_INVALID              = 0;
    public static final int ERROR_CODE_INTERNET             = 1;
    public static final int ERROR_CODE_REQUEST_JSON         = 2;
    public static final int ERROR_CODE_CONNECTION           = 3;
    public static final int ERROR_CODE_WRITE                = 4;
    public static final int ERROR_CODE_READ                 = 5;

    private static final int MAX_FAILED_RETRIES             = 2;

    // ----------------------- Classes ---------------------------//
    // ----------------------- Interfaces ----------------------- //
    // ----------------------- Globals ----------------------- //
    private static int serverConnectionRetries = 0;

    protected Context parentContext = null;

    // Connection related globals
    protected       HttpURLConnection urlConn = null;
    private         StringBuilder sb = null;
    private         String readLine = "";
    private         URL     url = null;
    private         BufferedReader reader = null;
    private         BufferedWriter writer = null;
    protected       int errorCode = ERROR_CODE_INVALID;

    // Connection related data
    private ServerObject serverInfo = null;

    // Request / Response Parameters
    protected  JSONObject requestJson = null;
    protected  JSONObject responseJson = null;

    // ----------------------- Constructor ----------------------- //
    public BaseServerTask(Context parentContext, ServerObject serverInfo)
    {
        this.parentContext = parentContext;
        this.serverInfo = serverInfo;
        requestJson = new JSONObject();

        try
        {
            url = new URL(serverInfo.urlAddress);
        }
        catch (MalformedURLException e)
        {
            Dbg.error(TAG, "Invalid URL : " + serverInfo.urlAddress);
            Dbg.stack(e);
        }
    }

    // ----------------------- Overrides ----------------------- //
    @Override
    public Void doInBackground (Void... params)
    {
        int errorCode = ERROR_CODE_INVALID;

        // Check for internet
        if (!InternetHelper.IsInternetAvailable(parentContext))
        {
            errorCode = ERROR_CODE_INTERNET;
            Dbg.error(TAG, "Internet Error !");
        }
        // Prepare Request JSON
        else if (!InitRequestJson())
        {
            errorCode = ERROR_CODE_REQUEST_JSON;
            Dbg.error(TAG, "Cannot Initialize Request Json");
        }
        // Connect to Server
        else if (!ConnectToServer())
        {
            errorCode = ERROR_CODE_CONNECTION;
            serverConnectionRetries++;
            Dbg.error(TAG, "Cannot connect to server");
        }
        // Write data
        else if (!WriteData())
        {
            errorCode = ERROR_CODE_WRITE;
            serverConnectionRetries++;
            Dbg.error(TAG, "Cannot write data to server");
        }
        // Read Data
        else if (!ReadData())
        {
            errorCode = ERROR_CODE_READ;
            serverConnectionRetries++;
            Dbg.error(TAG, "Cannot read data from server");
        }
        else
        {
            // Connection succesful. Reset Server retries
            serverConnectionRetries = 0;
        }

        Disconnect();

        return null;
    }

    // ----------------------- Public APIs ----------------------- //
    public static Boolean IsServerConnected()
    {
        if (serverConnectionRetries >= MAX_FAILED_RETRIES)
        {
            return false;
        }

        return true;
    }

    // ----------------------- Private APIs ----------------------- //
    protected boolean IsResponseValid()
    {
        // Valid response JSON
        if (responseJson == null)
        {
            return false;
        }

        // Validate Type &  App ID
        if (!responseJson.has(MacServer.KEY_REQUEST_TYPE))
        {
            return false;
        }
        else
        {
            try
            {
                int requestType = responseJson.getInt(MacServer.KEY_REQUEST_TYPE);

                if (requestType != serverInfo.type)
                {
                    // Invalid resposne type
                    return false;
                }
            }
            catch (JSONException e)
            {
                return false;
            }
        }

        return true;
    }

    private boolean ReadData()
    {
        boolean bResult = false;

        try
        {
            // Check response code
            int httpResponseCode = urlConn.getResponseCode();

            if (httpResponseCode == HttpURLConnection.HTTP_OK)
            {
                // Init Reader
                reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

                // read String response from server & close reader
                sb.setLength(0);
                while ((readLine = reader.readLine()) != null)
                {
                    sb.append(readLine);
                }

                // Clean and free reader
                reader.close();
                reader = null;

                // ParseToObject into JSON object
                if (sb.toString().length() > 0)
                {
                    responseJson = new JSONObject(sb.toString());
                    bResult = true;
                }
                else
                {
                    Dbg.error(TAG, "String length 0 from server");
                }
            }
            else
            {
                Dbg.error(TAG, "Unknown response code from server : " + httpResponseCode);
            }
        }
        catch (IOException e)
        {
            Dbg.error(TAG, "Exception while reading response form server");
            Dbg.stack(e);
        }
        catch (JSONException e)
        {
            Dbg.error(TAG, "Exception while putting init properties in result JSON");
            Dbg.stack(e);
        }

        return bResult;
    }

    private boolean WriteData()
    {
        boolean bResult = false;

        if (serverInfo.requestMethod == MacServer.REQUEST_METHOD_POST)
        {
            bResult = WritePostData();
        }
        else
        {
            // Write GET Params as a single request property
            urlConn.setRequestProperty(MacServer.KEY_GET_PARAMS, requestJson.toString());
            bResult = true;
        }

        return bResult;
    }

    private boolean WritePostData()
    {
        boolean bResult = false;

        // Populate Response JSON
        try
        {
            // Init Writer
            writer = new BufferedWriter(new OutputStreamWriter(urlConn.getOutputStream(), "UTF-8"));

            // Send data as String on the output stream of connection
            writer.write(requestJson.toString());
            writer.flush();

            // Clean and free writer
            writer.close();
            writer = null;

            bResult = true;
        }
        catch (IOException e)
        {
            Dbg.error(TAG, "IO Exception while writing to server " + e.getMessage());
            Dbg.stack(e);
        }

        return bResult;
    }

    private boolean ConnectToServer()
    {
        boolean bResult = false;

        if (url == null)
        {
            return bResult;
        }

        try
        {
            // Connect to URL
            urlConn = (HttpURLConnection) url.openConnection();

            // SetAlarm Connection properties
            urlConn.setConnectTimeout(MacServer.CONNECTION_TIMEOUT_MS);
            urlConn.setReadTimeout(MacServer.READ_TIMEOUT_MS);

            // SetAlarm Request Method
            if (serverInfo.requestMethod == MacServer.REQUEST_METHOD_GET)
            {
                urlConn.setRequestMethod("GET");
            }
            else
            {
                urlConn.setRequestMethod("POST");
            }

            // Init reader and writer
            sb = new StringBuilder();
            bResult = true;
        }
        catch (ProtocolException e)
        {
            Dbg.error(TAG, "Error while setting Request Method " + e.getMessage());
            Dbg.stack(e);
        }
        catch (MalformedURLException e)
        {
            Dbg.error(TAG, "Illegal URL " + e.getMessage());
            Dbg.stack(e);
        }
        catch (IOException e)
        {
            Dbg.error(TAG, "IO Exception while Connecting to server ");
            Dbg.stack(e);
        }

        return bResult;
    }

    private boolean InitRequestJson()
    {
        boolean bResult = false;

        try
        {
            // Add mandatory properties to request
            requestJson.put(MacServer.KEY_REQUEST_TYPE, serverInfo.type);
            bResult = true;
        }
        catch (JSONException e)
        {
            Dbg.error(TAG, "Error while parsing response to JSON " + e.getMessage());
            Dbg.stack(e);
        }

        return bResult;
    }

    private void Disconnect()
    {
        // Disconnect from server
        if (urlConn != null)
        {
            // Close streams
            try
            {
                if (reader != null)
                {
                    reader.close();
                    reader = null;
                }
                if (writer != null)
                {
                    writer.close();
                    writer = null;
                }
            }
            catch (IOException e)
            {
                Dbg.error(TAG, "Error while cleaning streams");
                Dbg.stack(e);
            }

            urlConn.disconnect();
            urlConn = null;
        }
    }
}
