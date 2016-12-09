package com.codecool.shop.model;

import com.codecool.shop.controller.Controller;

import java.math.BigDecimal;
import java.util.*;


public class ShoppingCart {
    private float totalPrice;
    private int totalQuantity;
    private Map<Integer, LineItem> lineItems = new HashMap<>();
    private static String state;

    private static String getControllerState() {
        state = Controller.getState();
        return state;
    }

    // Add LineItems lineItems hashmap (content of shoppingcart)
    private void addToMap(LineItem lineItem) {
        lineItems.put(lineItem.getProductID(), lineItem);
    }

    // Instantiate lineItems by productId and the Controller's state and check whether they have been already added to the shoppingcart
    // Set quantity of items and totalprice of shoppingcart
    public void addToCart(int id) {
        String state = getControllerState();
        LineItem newItem = new LineItem(id, state);
        if (lineItems.containsKey(newItem.getProductID())) {
            lineItems.get(newItem.getProductID()).setQuantity();
            lineItems.get(newItem.getProductID()).setSubtotal();
            setTotalPrice(newItem.getDefaultPrice());
            setTotalQuantity();

        } else {
            newItem.setSubtotal(newItem.getDefaultPrice());
            addToMap(newItem);
            setTotalPrice(newItem.getDefaultPrice());
            setTotalQuantity();
        }
    }

    public void addToCart(int id, int quantity) {
        String state = getControllerState();
        LineItem newItem = new LineItem(id, state);
        if (lineItems.containsKey(newItem.getProductID())) {
            lineItems.get(newItem.getProductID()).setQuantity(quantity);
            lineItems.get(newItem.getProductID()).setSubtotal();
            setTotalPrice(newItem.getDefaultPrice());
            setTotalQuantity();

        } else {
            newItem.setSubtotal(newItem.getDefaultPrice());
            addToMap(newItem);
            setTotalPrice(newItem.getDefaultPrice());
            setTotalQuantity();
        }
    }

    public List<LineItem> getAllLineItems() {
        List<LineItem> returnList = new ArrayList<>();
        returnList.addAll(lineItems.values());
        return returnList;
    }

    public void removeFromCart(int lineItemID){
        LineItem lineItem = lineItems.get(lineItemID);
        setTotalQuantity(totalQuantity - lineItem.getQuantity());
        lineItems.remove(lineItemID);
    }

    private void setTotalQuantity() {
        totalQuantity += 1;
    }

    private void setTotalQuantity(int quantity){
        totalQuantity = quantity;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    private void setTotalPrice(float price) {
        totalPrice += price;
        BigDecimal bd = new BigDecimal(Float.toString(totalPrice));
        totalPrice = bd.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public float getTotalPrice() {
        return totalPrice;
    }

}
