package com.codecool.shop.model;


import com.codecool.shop.dao.implementation.ProductDaoMem;


public class LineItem {
    private int productID;
    private String productName;
    private int quantity = 0;
    private float defaultPrice;
    private float subtotal = 0;


    public LineItem(int productID){
        ProductDaoMem productInstance = ProductDaoMem.getInstance();

        this.productID = productID;
        defaultPrice = productInstance.find(productID).getDefaultPrice();
        productName = productInstance.find(productID).getName();
    }

    public void setQuantity(){
        quantity++;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setSubtotal(){
        subtotal = defaultPrice * quantity;
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
}
