package com.example.android.inventoryappm.data;

import android.provider.BaseColumns;

public final class InventoryContract {

    /**
     * API Contract for Inventory App M
     */


    //Empty constructor to prevent ppl from instantiating the contract class by mistake
    private InventoryContract(){}

    public static final class inventory implements BaseColumns {

        public final static String TABLE_NAME = "Inventory";

        /**
         * Unique ID number is only used for database table entry. Type: INTEGER
         */

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_PRODUCT_NAME = "product_name";

        public final static String COLUMN_PRODUCT_PRICE = "price";

        public final static String COLUMN_PRODUCT_QUANTITY = "quantity";

        public final static String COLUMN_SUPPLIER_NAME = "supplier_name";

        public final static String COLUMN_PHONE_NUMBER = "phone_number";

    }

}
