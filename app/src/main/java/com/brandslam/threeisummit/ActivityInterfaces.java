package com.brandslam.threeisummit;

/**
 * Created by Siddharth on 25-04-2017.
 */

public class ActivityInterfaces
{
    // Activity Result interfaces
    public interface ResultRegistration
    {
        void onRegistrationComplete();
        void onRegistrationFailed();
    }

    // Permission Listeners
    public interface ContactPermission
    {
        void onContactPermissionSuccess();
        void onContactPermissionFailure();
    }
}
