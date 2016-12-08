package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.time.LocalDateTime;

public class OrderDaoJdbc implements OrderDao {
    private DBConnection connection = new DBConnection();
    private static OrderDaoJdbc instance = null;
    private String sql;


    private OrderDaoJdbc() {
    }

    public static OrderDaoJdbc getInstance() {
        if (instance == null) {
            instance = new OrderDaoJdbc();
        }
        return instance;
    }

    public void add(Order order) {
        int userId = order.getUserData().getId();
        String billingAddress = order.getUserData().getBilling_address().toString();
        String shippingAddress = order.getUserData().getShipping_address().toString();
        float totalPrice = order.getShoppingCart().getTotalPrice();
        String dateTime = LocalDateTime.now().toString();
        sql = "INSERT INTO orders (user_id,billing_address,shipping_address, total_price, date_time) " +
                "VALUES('"+userId+"','"+billingAddress+"','"+shippingAddress+"','"+totalPrice+"','"+dateTime+"');";
        connection.executeQuery(sql);
    }

//TODO: to find by id an order, order model have to store shopping cart object and shopping cart have to db_table
    public Order find(int id) {
        return null;
    }

    public void remove(int id) {
        sql = "DELETE FROM suppliers WHERE id = " + id + ";";
        connection.executeQuery(sql);
    }
}
