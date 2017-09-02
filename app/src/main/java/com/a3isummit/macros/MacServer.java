package com.a3isummit.macros;

/**
 * Created by Siddharth on 14-11-2016.
 */

public class MacServer
{
    // ----------------------- Constants ----------------------- //
    // Base URL of Server
    public static final String BASE_SERVER_URL                  = "http://192.168.43.166:8080/Your-Server";

    // Servlets
    public static final String SERVLET_USERS                    = "/UsersServlet";

    // General Request properties
    public static final int CONNECTION_TIMEOUT_MS               = 3000;
    public static final int READ_TIMEOUT_MS                     = 15000;

    // Request Methods for server requests
    public static final int REQUEST_METHOD_GET                  = 1;
    public static final int REQUEST_METHOD_POST                 = 2;

    // JSON Keys for request and response
    // Mandatory keys
    public static final String  KEY_GET_PARAMS                  = "get_paramas"; 						// Header Key in Get Request
    public static final String 	KEY_REQUEST_TYPE     			= "request_type";						// Request Type

    // User / Team info related keys
    public static final String 	KEY_APP_ID           		    = "app_id";
    public static final String 	KEY_USER_NAME      		        = "user_name";
    public static final String 	KEY_PHONE_NUMBER      		    = "phone_number";

    // Request Types

    // User Info related requests (0X / 1X)
    public static final int         REQUEST_TYPE_REGISTER       = 1;
}
