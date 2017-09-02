package com.a3isummit.server;

/**
 * Created by Siddharth on 09-04-2017.
 * Data Object class to store information about server connection such as the URL (with servlet name) and the method (GET / POST) etc.
 */

public class ServerObject
{
    // Server Request Properties
    public String urlAddress = "";
    public int requestMethod = 0;
    public int type = 0;

    // Constructor
    public ServerObject(int type, int requestMethod, String urlAddress)
    {
        this.requestMethod = requestMethod;
        this.type = type;
        this.urlAddress = urlAddress;
    }
}
