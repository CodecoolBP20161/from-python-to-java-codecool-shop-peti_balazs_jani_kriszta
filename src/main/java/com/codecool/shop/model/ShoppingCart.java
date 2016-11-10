package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private static float totalPrice;
    private static int totalQuantity;
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

    public void addToCart(int id) {
        LineItem newItem = new LineItem(id);
        if (lineItems.containsKey(newItem.getProductID())) {
            lineItems.get(newItem.getProductID()).setQuantity();
            lineItems.get(newItem.getProductID()).setSubtotal();
            setTotalPrice();
            setQuantity();
            setTotalPrice();

        } else {
            newItem.setSubtotal(newItem.getDefaultPrice());
            addToMap(newItem);
            setTotalPrice();
            setQuantity();
            setTotalPrice();
        }
    }

    private static void setQuantity() {
        totalQuantity += 1;
    }
    public int getTotalQuantity() {
        return totalQuantity;
    }

    public static void setTotalPrice() {
        for (LineItem item : lineItems.values()){
            totalPrice += item.getSubtotal();
        }
    }
    public float getTotalPrice() {
        return totalPrice;
    }

    public List<LineItem> getAllLineItems() {
        List<LineItem> returnList = new ArrayList<>();
        returnList.addAll(lineItems.values());
        return returnList;
    }
}
