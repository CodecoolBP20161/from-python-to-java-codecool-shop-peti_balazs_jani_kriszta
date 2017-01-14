package com.codecool.shop.model;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.database.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;

import java.math.BigDecimal;


public class LineItem {
    private int productID;
    private String productName;
    private int quantity = 1;
    private float defaultPrice;
    private float subtotal = 0;

    // Constructor for LineItem class
    public LineItem(int productID, String state){
        ProductDao productInstance = null;

        if (state.equals("DB")) {
            productInstance = ProductDaoJdbc.getInstance();
        } else if (state.equals("MEM")) {
            productInstance = ProductDaoMem.getInstance();
        }

        this.productID = productID;
        this.defaultPrice = productInstance.find(productID).getDefaultPrice();
        this.productName = productInstance.find(productID).getName();
    }

    public void setQuantity(){
        this.quantity++;
    }

    public void reduceQuantity() {this.quantity--;}

    public int getQuantity(){
        return quantity;
    }

    public int getQuantity(int productID) {
        if (this.productID == productID) {
            return this.quantity;
        }
        return 0;
    }

    void setSubtotal(){
        subtotal = defaultPrice * (float) this.quantity;
        BigDecimal bd = new BigDecimal(Float.toString(subtotal));
        subtotal = bd.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }
//    void setSubtotal(float price){
//        subtotal = price;
//    }

    public float getSubtotal() {
        return subtotal;
    }

    void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    void setProductID(int productID){
        this.productID = productID;
    }

    public int getProductID() {
        return productID;
    }

    void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

}
