package com.a3isummit.adapters;

import com.a3isummit.objects.GuestObject;
import com.a3isummit.objects.TestimonialObject;

/**
 * Data Objects used to store data which populates the list item view
 */

public class ListItemObject
{
    // ----------------------- Constants ----------------------- //
    // Types of list items
    public static final int TYPE_INVALID                = 0;

    // Drawer related list items
    public static final int TYPE_DRAWER_ITEM            = 1;        // Actual Drawer view with image and text
    public static final int TYPE_DRAWER_ITEM_SEPARATOR  = 2;        // Drawer items separator
    public static final int TYPE_DRAWER_GROUP_SEPARATOR = 3;        // Drawer Group separator

    public static final int TYPE_TESTIMONIAL            = 4;
    public static final int TYPE_GUEST                  = 5;

    // ----------------------- Classes ---------------------------//
    // Base Class for list Item Data
    public static abstract class Base
    {
        // ----------------------- Globals ----------------------- //
        public int    type              = TYPE_INVALID;
        public int    id                = -1;

        // ----------------------- Constructor ----------------------- //
        public Base(int type, int id)
        {
            this.type               = type;
            this.id                 = id;
        }
    }

    // Generic list item with 2 images, 1 text and custom background image.
    // Many of the list views in the app can be created using this customizations of this data object
    public static class Generic extends Base
    {
        // ----------------------- Globals ----------------------- //
        public String title             = "";
        public int startImageId         = 0;
        public int endImageId           = 0;
        public int background           = 0;

        // ----------------------- Constructor ----------------------- //
        public Generic(int type, int id, String title, int startImageId, int endImageId, int background)
        {
            super(type, id);

            this.title              = title;
            this.startImageId       = startImageId;
            this.endImageId         = endImageId;
            this.background         = background;
        }
    }

    public static class Testimonial extends Base
    {
        // ----------------------- Globals ----------------------- //
        public TestimonialObject testimonial = null;

        // ----------------------- Constructor ----------------------- //
        public Testimonial(TestimonialObject testimonial)
        {
            super(TYPE_TESTIMONIAL, -1);

            this.testimonial              = testimonial;
        }
    }

    public static class Guest extends Base
    {
        // ----------------------- Globals ----------------------- //
        public GuestObject guest = null;

        // ----------------------- Constructor ----------------------- //
        public Guest(GuestObject guest)
        {
            super(TYPE_GUEST, -1);

            this.guest              = guest;
        }
    }

    public static class GuestCategory extends Base
    {
        // ----------------------- Globals ----------------------- //
        public GuestObject.GuestObjectCategory guestCat = null;

        // ----------------------- Constructor ----------------------- //
        public GuestCategory(GuestObject.GuestObjectCategory guestCat)
        {
            super(TYPE_GUEST, -1);

            this.guestCat              = guestCat;
        }
    }
}
