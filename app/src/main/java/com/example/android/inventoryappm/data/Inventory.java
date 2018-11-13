package com.example.android.inventoryappm.data;

import com.example.android.inventoryappm.data.InventoryContract.inventory;
import com.example.android.inventoryappm.data.InventoryDbHelper;

public class Inventory {

    private int id;
    private String product_name;
    private int product_price;
    private int product_quantity;
    private String supplier_name;
    private int phone_number;


    public Inventory(){

    }

    public Inventory(int id, String product_name, int product_price, int product_quantity,
                     String supplier_name, int phone_number){
        this.id = id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.supplier_name = supplier_name;
        this.phone_number = phone_number;

    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name(){
        return product_name;
    }

    public int getProduct_price(){
        return product_price;
    }

    public int getProduct_quantity(){
        return product_quantity;
    }

    public String getSupplier_name(){
        return supplier_name;
    }

    public int getPhone_number() {
        return phone_number;
    }
}
