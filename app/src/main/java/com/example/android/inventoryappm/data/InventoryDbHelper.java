package com.example.android.inventoryappm.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.android.inventoryappm.data.InventoryContract.inventory;


public class InventoryDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "inventoryAppM.db";

    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link InventoryDbHelper}.
     * @param context of the app
     */

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a String that contains the SQL statement to create the Inventory table
        String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE " + inventory.TABLE_NAME + " ("
                + inventory._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + inventory.COLUMN_PRODUCT_NAME + " TEXT,"
                + inventory.COLUMN_PRODUCT_PRICE + " INTEGER,"
                + inventory.COLUMN_PRODUCT_QUANTITY + " INTEGER,"
                + inventory.COLUMN_SUPPLIER_NAME + " TEXT,"
                + inventory.COLUMN_PHONE_NUMBER + " INTEGER )";

        //Execute the SQL statement
        db.execSQL(SQL_CREATE_INVENTORY_TABLE);

        Log.d("successful message", "created table of db");

    }

    /**
     * This is called when the database needs to be upgraded.
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //The database is still at version 1, so there's nothing to do here.
    }

}
