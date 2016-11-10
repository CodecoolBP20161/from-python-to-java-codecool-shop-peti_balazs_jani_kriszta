package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by komlancz on 2016.11.09..
 */
public class ShoppingCart {
    private float totalPrice;
    private int totalQuantity;
    private static List<LineItem> lineItems = new ArrayList<>();
    private static ShoppingCart instance = null;


    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    private static void addToList(LineItem lineItem){
        lineItems.add(lineItem);
    }

    public static LineItem addToCart(int id){
        LineItem returnItem = null;
        for (LineItem item : lineItems) {
            if (id == item.getProductID()) {
                item.setQuantity();
                item.setSubtotal();
                returnItem = item;
            } else {
                LineItem newItem = new LineItem(id);
                addToList(newItem);
                returnItem = newItem;
            }
        }
        return returnItem;
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
