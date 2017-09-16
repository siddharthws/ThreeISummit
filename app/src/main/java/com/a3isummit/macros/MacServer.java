package com.a3isummit.macros;

/**
 * Created by Siddharth on 14-11-2016.
 */

public class MacServer
{
    // ----------------------- Constants ----------------------- //
    // Base URL of Server
    public static final String BASE_SERVER_URL                  = "http://192.168.43.59:8080/BrandslamServer";

    // Servlets
    public static final String SERVLET_USERS                    = "/userServlet";
    public static final String SERVLET_FEEDBACK                    = "/feedback";
    public static final String SERVLET_TESTIMONIAL                    = "/testimonials";
    public static final String SERVLET_GUEST                    = "/guestFetch";



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

    // User info related keys
    public static final String 	KEY_APP_ID           		    = "app_id";
    public static final String 	KEY_USER_NAME      		        = "user_name";
    public static final String 	KEY_PHONE_NUMBER      		    = "phone_number";
    public static final String  KEY_EMAIL_ID                    =  "email_id";

    //Feedback Related Keys
    public static final String 	KEY_FEEDBACK_NAME      		        = "name";
    public static final String 	KEY_FEEDBACK_SUGGESTION      		= "suggestion";
    public static final String 	KEY_FEEDBACK_STATUS      		        = "feedback_status";


    //testimonial add
    public static final String 	KEY_TESTIMONIAL_NAME      		            = "name";
    public static final String 	KEY_TESTIMONIAL_SUGGESTION     		        = "suggestion";
    public static final String 	KEY_TESTIMONIAL_STATUS      		        = "testimonial_status";


    //testimonials fetch
    public static final String 	KEY_TESTIMONIAL_FETCH_NAME    		        = "name";
    public static final String 	KEY_TESTIMONIAL_FETCH_SUGGESTION   		    = "suggestion";
    public static final String 	KEY_TESTIMONIAL_FETCH_DATE     		        = "testimonial_date";
    public static final String 	KEY_TESTIMONIAL_FETCH_ARRAY     		        = "testimonial_array";


    //Guest LIST Fetch
    public static final String 	KEY_GUEST_NAME    		        = "guest_name";
    public static final String 	KEY_GUEST_DESIGNATION 		    = "guest_designation";
    public static final String 	KEY_GUEST_COMPANY     		        = "guest_company";
    public static final String 	KEY_GUEST_ARRAY    		        = "guest_array";

    // Request Types

    // User Info related requests (0X / 1X)
    public static final int         REQUEST_TYPE_REGISTER              = 1;
    public static final int         REQUEST_TYPE_FEEDBACK              = 2;
    public static final int         REQUEST_TYPE_TESTIMONIAL_ADD       = 3;
    public static final int         REQUEST_TYPE_TESTIMONIAL_FETCH     = 4;
    public static final int         REQUEST_TYPE_GUEST_LIST     = 5;
}
