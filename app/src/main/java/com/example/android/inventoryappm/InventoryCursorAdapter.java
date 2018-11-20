package com.example.android.inventoryappm;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryappm.data.InventoryContract;

public class InventoryCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link InventoryCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.activity_list_item, parent, false);
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {

        //Find Sale Button
        Button saleButton = view.findViewById(R.id.sale_button);

        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = view.findViewById(R.id.name);
        TextView summaryTextView = view.findViewById(R.id.summary);
        TextView quantityTextView = view.findViewById(R.id.product_quantity);

        // Find the columns of book attributes that we're interested in
        final int idColumnIndex = cursor.getColumnIndex(InventoryContract.inventory._ID);
        int nameColumnIndex = cursor.getColumnIndex(InventoryContract.inventory.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(InventoryContract.inventory.COLUMN_PRODUCT_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.inventory.COLUMN_PRODUCT_QUANTITY);

        // Read the book attributes from the Cursor for the current book
        String productName = cursor.getString(nameColumnIndex);
        String productPrice = cursor.getString(priceColumnIndex);
        final int productQuantity = cursor.getInt(quantityColumnIndex);

        // Update the TextViews with the attributes for the current book
        nameTextView.setText(productName);
        summaryTextView.setText(productPrice);

        // If the book quantity is empty string or null, then set it to zero.
        if (productQuantity == 0) {
            quantityTextView.setText(context.getString(R.string.unknown_quantity));
            // Disable button click
            saleButton.setEnabled(false);
        } else {
            quantityTextView.setText(String.valueOf(productQuantity));
            // Enable button click
            saleButton.setEnabled(true);
        }

        // OnClickListener for Sale button
        // When clicked it reduces the number in stock by 1.
        final String id = cursor.getString(cursor.getColumnIndex(InventoryContract.inventory._ID));

        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (productQuantity > 0) {
                    Uri currentBookUri = ContentUris.withAppendedId(InventoryContract.inventory.CONTENT_URI,
                            Long.parseLong(id));
                    ContentValues values = new ContentValues();
                    values.put(InventoryContract.inventory.COLUMN_PRODUCT_QUANTITY, productQuantity - 1);
                    context.getContentResolver().update(currentBookUri, values, null, null);
                    swapCursor(cursor);
                    // Check if out of stock to display toast
                    if (productQuantity == 1) {
                        Toast.makeText(context, R.string.out_of_stock, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}