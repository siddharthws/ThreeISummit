package com.a3isummit.adapters;

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

    // Generic items for different types of data
    public static final int TYPE_LEAD                   = 4;        // lead list item

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
}
