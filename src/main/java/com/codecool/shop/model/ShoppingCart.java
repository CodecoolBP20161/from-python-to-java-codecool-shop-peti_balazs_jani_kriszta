package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by komlancz on 2016.11.09..
 */
public class ShoppingCart {
    private float totalPrice;
    private int totalQuantity;
    private List<LineItem> lineItems = new ArrayList<>();

    public void addToList(LineItem lineItem){
        this.lineItems.add(lineItem);
    }

    public void addToCart(int id){
        for (LineItem item : lineItems){
            if (id == item.getProductID()){
                item.setQuantity();
                item.setSubtotal();
            } else {
                LineItem newItem = new LineItem(id);
                addToList(newItem);
            }
        }
    }

    public void setQuantity() {
        for (LineItem item : lineItems){
            this.totalQuantity += item.getQuantity();
        }
    }
    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalPrice() {
        for (LineItem item : lineItems){
            this.totalPrice += item.getSubtotal();
        }
    }
    public float getTotalPrice() {
        return totalPrice;
    }

    public List<LineItem> getAllLineItems() {
        return lineItems;
    }
}
