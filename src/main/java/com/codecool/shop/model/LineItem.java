package com.codecool.shop.model;


import com.codecool.shop.dao.implementation.ProductDaoMem;


public class LineItem {
    private int productID;
    private String productName;
    private int quantity = 1;
    private float defaultPrice;
    private float subtotal = 0;


    public LineItem(int productID){
        ProductDaoMem productInstance = ProductDaoMem.getInstance();

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
