package com.brandslam.server;

import com.brandslam.objects.GuestObject;
import com.brandslam.objects.TestimonialObject;

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
        void onServerSuccessCat(ArrayList<GuestObject.GuestObjectCategory> guestObjects);
        void onServerFailure();
    }
}
