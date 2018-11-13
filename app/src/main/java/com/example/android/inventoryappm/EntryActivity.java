package com.example.android.inventoryappm;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.inventoryappm.data.InventoryDbHelper;

import com.example.android.inventoryappm.data.InventoryDbHelper;
import com.example.android.inventoryappm.data.InventoryContract.inventory;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_entry);
    }

}