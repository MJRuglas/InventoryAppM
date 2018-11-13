package com.example.android.inventoryappm;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.android.inventoryappm.data.Inventory;
import com.example.android.inventoryappm.data.InventoryDbHelper;
import com.example.android.inventoryappm.data.InventoryContract.inventory;

public class MainActivity extends AppCompatActivity {

    private InventoryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up ImageButton to open EntryActivity activity
        ImageButton newEntry = findViewById(R.id.new_entry_button);
        newEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EntryActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new InventoryDbHelper(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                inventory._ID,
                inventory.COLUMN_PRODUCT_NAME,
                inventory.COLUMN_PRODUCT_PRICE,
                inventory.COLUMN_PRODUCT_QUANTITY,
                inventory.COLUMN_SUPPLIER_NAME,
                inventory.COLUMN_PHONE_NUMBER};

        // Perform a query on the pets table
        Cursor cursor = db.query(
                inventory.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.inventory_textView);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor>
            displayView.setText("The books table contains " + cursor.getCount() + " books.\n\n");
            displayView.append(inventory._ID + " - " +
                    inventory.COLUMN_PRODUCT_NAME + " - " +
                    inventory.COLUMN_PRODUCT_PRICE + " - " +
                    inventory.COLUMN_PRODUCT_QUANTITY + " - " +
                    inventory.COLUMN_SUPPLIER_NAME + " - " +
                    inventory.COLUMN_PHONE_NUMBER + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(inventory._ID);
            int nameColumnIndex = cursor.getColumnIndex(inventory.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(inventory.COLUMN_PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(inventory.COLUMN_PRODUCT_QUANTITY);
            int supplierColumnIndex = cursor.getColumnIndex(inventory.COLUMN_SUPPLIER_NAME);
            int phoneColumnIndex = cursor.getColumnIndex(inventory.COLUMN_PHONE_NUMBER);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplier = cursor.getString(supplierColumnIndex);
                int currentPhone = cursor.getInt(phoneColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentPrice + " - " +
                        currentQuantity + " - " +
                        currentSupplier + " - " +
                        currentPhone));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    /**
     * Helper method to insert hardcoded pet data into the database. For debugging purposes only.
     */
    private void insertBook() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(inventory.COLUMN_PRODUCT_NAME, "The New Human Revolution Vol. 30");
        values.put(inventory.COLUMN_PRODUCT_PRICE, 8.00);
        values.put(inventory.COLUMN_PRODUCT_QUANTITY, 10);
        values.put(inventory.COLUMN_SUPPLIER_NAME, "Soka Books");
        values.put(inventory.COLUMN_PHONE_NUMBER, 8899);

        // Insert a new row for Toto in the database, returning the ID of that new row.
        // The first argument for db.insert() is the pets table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        long newRowId = db.insert(inventory.TABLE_NAME, null, values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertBook();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}