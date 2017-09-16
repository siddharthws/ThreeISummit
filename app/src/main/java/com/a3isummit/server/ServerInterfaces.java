package com.a3isummit.server;

import com.a3isummit.objects.GuestObject;
import com.a3isummit.objects.TestimonialObject;

import java.util.ArrayList;

/**
 * Created by Siddharth on 01-08-2017.
 * Interfaces used in server tasks
 */

public class ServerInterfaces
{
    public interface IfaceBasic
    {
        void onServerSuccess();
        void onServerFailure();
    }

    public interface IfaceTestFetch
    {
        void onServerSuccess(ArrayList<TestimonialObject> testimonialObjects);
        void onServerFailure();
    }

    public interface IfaceGuestFetch
    {
        void onServerSuccess(ArrayList<GuestObject> guestObjects);
        void onServerFailure();
    }
}
