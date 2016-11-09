package com.codecool.shop.model;

import java.util.List;

public class Order {
    private int id;
    private List lineItems;

    public Order(List shoppingCartItems){
        this.lineItems = shoppingCartItems;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List getLineItems() {
        return lineItems;
    }
    @Override
    public String toString(){
        return "id: "+this.id+" Line Items: "+this.lineItems;
    }
}
