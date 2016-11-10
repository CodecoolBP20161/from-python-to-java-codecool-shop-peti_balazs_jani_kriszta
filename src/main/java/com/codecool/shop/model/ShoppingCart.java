package com.codecool.shop.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private float totalPrice;
    private int totalQuantity;
    private Map<Integer, LineItem> lineItems = new HashMap<>();



    private void addToMap(LineItem lineItem) {
        lineItems.put(lineItem.getProductID(), lineItem);
    }

    public void addToCart(int id) {
        LineItem newItem = new LineItem(id);
        if (lineItems.containsKey(newItem.getProductID())) {
            lineItems.get(newItem.getProductID()).setQuantity();
            lineItems.get(newItem.getProductID()).setSubtotal();
            setTotalPrice(newItem.getDefaultPrice());
            setQuantity();

        } else {
            newItem.setSubtotal(newItem.getDefaultPrice());
            addToMap(newItem);
            setTotalPrice(newItem.getDefaultPrice());
            setQuantity();
        }
    }

    private void setQuantity() {
        totalQuantity += 1;
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

    public List<LineItem> getAllLineItems() {
        List<LineItem> returnList = new ArrayList<>();
        returnList.addAll(lineItems.values());
        return returnList;
    }
}
