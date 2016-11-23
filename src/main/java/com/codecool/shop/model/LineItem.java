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
//        String state = status;
        ProductDao productInstance = null;
        if (state.equals("DB")) {
            productInstance = new ProductDaoJdbc();
        } else if (state.equals("MEM")) {
            productInstance = ProductDaoMem.getInstance();
        }
//        ProductDaoMem productInstance = ProductDaoMem.getInstance();

        this.productID = productID;
        this.defaultPrice = productInstance.find(productID).getDefaultPrice();
        this.productName = productInstance.find(productID).getName();

    }

    public void setQuantity(){
        this.quantity++;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setSubtotal(){
        subtotal = defaultPrice * (float) quantity;
        BigDecimal bd = new BigDecimal(Float.toString(subtotal));
        subtotal = bd.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }
    public void setSubtotal(float price){
        subtotal = price;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductID() {
        return productID;
    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
}
