package com.codecool.shop.model;

import java.util.HashMap;

public class User {
    private int id;
    private String name;
    private String email;
    private String phone;
    private HashMap<String, String> billing_address = new HashMap<>();
    private HashMap<String, String> shipping_address = new HashMap<>();

    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, String> getBilling_address() {
        return billing_address;
    }

    public void setBilling_address(String country, String city, String zip_code, String address) {
        this.billing_address.putAll(setAddressData(country,city,zip_code,address));
    }

    public HashMap<String, String> getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String country, String city, String zip_code, String address) {
        this.shipping_address.putAll(setAddressData(country,city,zip_code,address));
    }

    private HashMap<String, String> setAddressData(String country, String city, String zip_code, String address){
        HashMap<String, String> data = new HashMap<>();
        data.put("country", country);
        data.put("city", city);
        data.put("zip_code", zip_code);
        data.put("address", address);
        return data;
    }
    public static void main(String[] args) {
        User user = new User("jani", "jkomlancz@gmail.com", "06309115524");
        user.setBilling_address("mo", "bp", "1124", "Gyimes u");

        ShoppingCart shoppingCart = new ShoppingCart();
        Order order = new Order(shoppingCart, user);
        System.out.println(order.getUserData().getName());
        System.out.println(order.getUserData().getBilling_address().get("country"));
        System.out.println(order.getShoppingCart().getTotalPrice());

    }
}
