package com.a3isummit.objects;

/**
 * Created by Siddharth on 14-09-2017.
 */

public class GuestObject
{

    public String name, designation, company, category;

    public GuestObject(String category,String name, String designation, String company) {
        this.category=category;
        this.designation = designation;
        this.name = name;
        this.company = company;
    }

    public static class GuestObjectCategory
    {
        public String category;

        public GuestObjectCategory(String category) {
            this.category=category;
    }
    }
}

