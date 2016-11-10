package com.codecool.shop.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by komlancz on 2016.11.09..
 */
public class ShoppingCart {
    private float totalPrice;
    private int totalQuantity;
    private static Map<Integer, LineItem> lineItems = new HashMap<>();
    private static ShoppingCart instance = null;


    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    private static void addToMap(LineItem lineItem) {
        lineItems.put(lineItem.getProductID(), lineItem);
    }

    public static void addToCart(int id) {
        LineItem newItem = new LineItem(id);
        if (lineItems.containsKey(newItem.getProductID())) {
            lineItems.get(newItem.getProductID()).setQuantity();
            lineItems.get(newItem.getProductID()).setSubtotal();
        } else {
            newItem.setSubtotal(newItem.getDefaultPrice());
            addToMap(newItem);
        }
    }

    public void setQuantity() {

        for (LineItem item : lineItems.values()){
            this.totalQuantity += item.getQuantity();
        }
    }
    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalPrice() {
        for (LineItem item : lineItems.values()){
            this.totalPrice += item.getSubtotal();
        }
    }
    public float getTotalPrice() {
        return totalPrice;
    }

    public Map<Integer, LineItem> getAllLineItems() {
        return lineItems;
    }
}
