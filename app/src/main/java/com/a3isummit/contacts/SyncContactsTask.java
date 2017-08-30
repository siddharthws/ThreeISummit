package com.a3isummit.contacts;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;

import com.a3isummit.debug.Dbg;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Siddharth on 15-12-2016.
 */

public class SyncContactsTask extends     AsyncTask<Void, Integer, Boolean>
{
    // ----------------------- Constants ----------------------- //
    private static final String TAG = "SYNC_CONTACTS";

    // Min length for a valid phone number
    private static final int    PHONE_NUMBER_UNKNOWN_TYPE_MIN_LENGTH = 7;

    // ----------------------- Interfaces ----------------------- //
    public interface IfaceSyncContactListener
    {
        void onContactsSyncProgress(int total, int serviced);
        void onContactsSyncSuccess();
        void onContactsSyncFailure();
    }
    private IfaceSyncContactListener listener = null;
    public void SetSyncContactListener(IfaceSyncContactListener listener)
    {
        this.listener = listener;
    }

    // ----------------------- Globals ----------------------- //
    private Context parentContext = null;
    public static HashMap<String, String> contacts = null;

    // ----------------------- Constructor ----------------------- //
    public SyncContactsTask(Context parentContext)
    {
        this.parentContext = parentContext;
    }

    // ----------------------- Overrides ----------------------- //

    @Override
    public void onPreExecute()
    {
    }

    @Override
    public void onProgressUpdate(Integer... progress)
    {
        int total = progress[0];
        int serviced = progress[1];

        // Show progress dialog
        if (listener != null)
        {
            listener.onContactsSyncProgress(total, serviced);
        }
    }

    @Override
    protected Boolean doInBackground(Void... voids)
    {
        return ReadPhoneDirectory();
    }

    @Override
    public void onPostExecute(Boolean bSuccess)
    {
        // Call completed listener
        if (bSuccess)
        {
            if (listener != null)
            {
                listener.onContactsSyncSuccess();
            }
        }
        else
        {
            if (listener != null)
            {
                Dbg.info(TAG, "Syncing");
                listener.onContactsSyncFailure();
            }
        }
    }

    // ----------------------- Public APIs ----------------------- //

    public static String GetFormattedPhoneNo(String rawNumber)
    {
        String formattedNumber = "";

        // ParseToObject Raw number to Phone Number Object
        Phonenumber.PhoneNumber phoneNo = ParseStringToPhoneNo(rawNumber);

        // Check Validity of number
        if (IsPhoneNoValid(phoneNo))
        {
            // Format to internation format
            formattedNumber = ParsePhoneNoToString(phoneNo);
        }

        return formattedNumber;
    }

    // ----------------------- Private APIs ----------------------- //
    public boolean ReadPhoneDirectory()
    {
        ContentResolver cr = parentContext.getContentResolver();
        ArrayList<String> servicedPhoneNumbers = new ArrayList<>();
        contacts = new HashMap<>();
Dbg.info(TAG, "1");
        // Check for permission
        if (ContextCompat.checkSelfPermission(parentContext, Manifest.permission.READ_CONTACTS) != PermissionChecker.PERMISSION_GRANTED)
        {
            return false;
        }
        Dbg.info(TAG, "2");

        // Get cursor for the contact list
        Cursor contactCursor = cr.query(ContactsContract.Contacts.CONTENT_URI,
                                        null,
                                        null,
                                        null,
                                        null);

        // Validate Cursor
        if (contactCursor == null)
        {
            return false;
        }
        Dbg.info(TAG, "3");

        int contactCount = contactCursor.getCount();
        int contactIdx = -1;

        // Inittialize progress
        publishProgress(contactCount, 0);

        while (contactCursor.moveToNext())
        {
            contactIdx++;

            // Get contact ID
            String id = contactCursor.getString(
                    contactCursor.getColumnIndex(ContactsContract.Contacts._ID));

            String displayName = contactCursor.getString(
                    contactCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));

            // Check if phone number is present
            if (Integer.parseInt(contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
            {
                // Get phone number based on ID from cursor
                Cursor phoneNoCursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{id},
                        null);

                // Validate Cursor
                if (phoneNoCursor == null)
                {
                    continue;
                }

                if (phoneNoCursor.getCount() > 0)
                {
                    while (phoneNoCursor.moveToNext())
                    {
                        // Get Phone number string
                        String phoneNoString = phoneNoCursor.getString(phoneNoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String formattedPhoneNumber = GetFormattedPhoneNo(phoneNoString);

                        // Don't Add if phone no is invalid
                        if (formattedPhoneNumber.length() == 0)
                        {
                            continue;
                        }

                        // Ignore repeated numbers
                        if (servicedPhoneNumbers.contains(formattedPhoneNumber))
                        {
                            continue;
                        }

                        // Get phone number label (MOBILE is blank)
                        int phoneNoLabelType = phoneNoCursor.getInt(phoneNoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        String phoneNoLabel = "";

                        if (phoneNoLabelType == ContactsContract.CommonDataKinds.BaseTypes.TYPE_CUSTOM)
                        {
                            phoneNoLabel = phoneNoCursor.getString(phoneNoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.LABEL));
                        }
                        else if (phoneNoLabelType != ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                        {
                            phoneNoLabel = (String) ContactsContract.CommonDataKinds.Phone.getTypeLabel(parentContext.getResources(), phoneNoLabelType, "");
                        }

                        if (phoneNoLabel == null)
                        {
                            phoneNoLabel = "";
                        }

                        // Add this contact to list
                        contacts.put(formattedPhoneNumber, displayName);

                        servicedPhoneNumbers.add(formattedPhoneNumber);

                        // Send Progress update if required
                        publishProgress(contactCount, contactIdx + 1);
                    }
                }

                phoneNoCursor.close();
            }
        }

        contactCursor.close();

        return true;
    }

    /*
     * Phone number Validation APIs
     */
    private static Phonenumber.PhoneNumber ParseStringToPhoneNo(String phoneNoString)
    {
        // ParseToObject phone number to international format
        Phonenumber.PhoneNumber phoneNo = null;
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        try
        {
            if (!phoneNoString.contains("+"))
            {
                String countryCode = "91";
                phoneNoString = "+" + countryCode + "" + phoneNoString;
            }

            phoneNo = phoneUtil.parse(phoneNoString, "");
        }
        catch (NumberParseException e)
        {
            Dbg.error(TAG, "Error while parsing phone number : " + phoneNoString);
            Dbg.stack(e);
            phoneNo = null;
        }

        return phoneNo;
    }

    private static boolean IsPhoneNoValid(Phonenumber.PhoneNumber phoneNo)
    {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        boolean bValid = false;

        // Phone number should not be null
        if (phoneNo != null)
        {
            // Phone Util Validity check
            if (phoneUtil.isPossibleNumber(phoneNo))
            {
                // Phone number type should be valid
                PhoneNumberUtil.PhoneNumberType numberType = phoneUtil.getNumberType(phoneNo);

                if ((numberType == PhoneNumberUtil.PhoneNumberType.MOBILE) ||
                        (numberType == PhoneNumberUtil.PhoneNumberType.FIXED_LINE_OR_MOBILE) ||
                        (numberType == PhoneNumberUtil.PhoneNumberType.PERSONAL_NUMBER))
                {
                    bValid = true;
                }
                else if (numberType == PhoneNumberUtil.PhoneNumberType.UNKNOWN)
                {
                    // For UNKNOWN numbers, perform size check
                    String formattedInternationNumber = ParsePhoneNoToString(phoneNo);
                    if (formattedInternationNumber.length() > PHONE_NUMBER_UNKNOWN_TYPE_MIN_LENGTH)
                    {
                        bValid = true;
                    }
                    else
                    {
                        Dbg.error(TAG, "Formatted phone number is less than 7 characters");
                    }
                }
                else
                {
                    Dbg.error(TAG, "Unrecognizable Number type " + numberType.toString());
                }
            }
            else
            {
                Dbg.error(TAG, "Phone number is invalid as per phone util");
            }
        }
        else
        {
            Dbg.error(TAG, "Phone number is null");
        }

        return bValid;
    }

    private static String ParsePhoneNoToString(Phonenumber.PhoneNumber phoneNo)
    {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        String phoneNoString = "";

        phoneNoString = phoneUtil.format(phoneNo, PhoneNumberUtil.PhoneNumberFormat.E164);

        return phoneNoString;
    }
}
