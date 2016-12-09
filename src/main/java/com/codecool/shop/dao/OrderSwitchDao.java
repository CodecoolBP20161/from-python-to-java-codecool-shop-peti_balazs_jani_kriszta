package com.codecool.shop.dao;

public interface OrderSwitchDao {
    void add(int orderId, int productId, int productQuantity);
}
