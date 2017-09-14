package com.a3isummit.objects;

/**
 * Created by Siddharth on 14-09-2017.
 */

public class GuestObject
{
    public static final GuestObject[] TEMP_DATA = {
            new GuestObject("Test Name 1", "Manager ", "ABC Private Ltd"),
            new GuestObject("Test Name 2", "Vice President, PR, Yeh Bhi, Woh Bhi....", "Indira college of engineering, Pune"),
            new GuestObject("Test Name 3", "President, Captain, Boss, Blah Blah Blah... ", "PR solutions"),
            new GuestObject("Test Name 4", "System Software Engineer", "Blah Blah Company Pvt Ltd."),
    };

    public String name, designation, company;

    public GuestObject(String name, String designation, String company) {
        this.designation = designation;
        this.name = name;
        this.company = company;
    }
}
