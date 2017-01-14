package com.codecool.shop.model;

import com.codecool.shop.controller.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
            newItem.setSubtotal();
            addToMap(newItem);
            setTotalPrice(newItem.getDefaultPrice());
            setTotalQuantity();
        }
    }

    public void reduceQuantity(int id) {
        String state = getControllerState();
        LineItem newItem = new LineItem(id, state);
        if (lineItems.containsKey(newItem.getProductID())) {
            lineItems.get(newItem.getProductID()).reduceQuantity();
            lineItems.get(newItem.getProductID()).setSubtotal();
            setTotalPrice(newItem.getDefaultPrice());
            setTotalQuantity(totalQuantity - 1);
        }
    }

    public List<LineItem> getAllLineItems() {
        List<LineItem> returnList = new ArrayList<>();
        returnList.addAll(lineItems.values());
        return returnList;
    }


    public LineItem getLineItemById(int productId) {
        return lineItems.get(productId);
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
        totalPrice = 0;
        for (LineItem item : getAllLineItems()){
            totalPrice += item.getSubtotal();
        }
        return totalPrice;
    }

}
