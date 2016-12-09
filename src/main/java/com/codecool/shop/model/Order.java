package com.codecool.shop.model;

public class Order {
    private int id;
    private ShoppingCart shoppingCart;
    private User userData;

//    it have to get a ShoppingCart object and a User object
    public Order(ShoppingCart shoppingCart, User userData) {
        this.shoppingCart = shoppingCart;
        this.userData = userData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public User getUserData() {
        return userData;
    }

}
