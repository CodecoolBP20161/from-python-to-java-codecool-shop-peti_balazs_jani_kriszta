package com.codecool.shop.model;

public class OrdersSwitch {

    public OrdersSwitch(int orderId, int productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    private int orderId;
    private int productId;
    private int quantity = 0;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setQuantity(){
        this.quantity++;
    }
}
