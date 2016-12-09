package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.OrderSwitchDao;
import com.codecool.shop.model.OrdersSwitch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderSwitchDaoJdbc implements OrderSwitchDao {

    private DBConnection connection = new DBConnection();
    private String sql;
    private static OrderSwitchDaoJdbc instance = null;

    public static OrderSwitchDaoJdbc getInstance(){
        if (instance == null){
            instance = new OrderSwitchDaoJdbc();
        }
        return instance;
    }
    public void add(int orderId, int productId, int productQuantity) {
/**
 * Ha az orderid meg a productid létezik akkor növeli setQuantity();
 */
        sql = "SELECT * FROM orders_switch WHERE order_id="+orderId+" AND product_id="+productId+";";
        try (Connection conn = connection.connect();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql)) {
            if (!rs.next()){
                OrdersSwitch ordersSwitch = new OrdersSwitch(orderId, productId);
                ordersSwitch.setQuantity(productQuantity);
                int quantity = ordersSwitch.getQuantity();
                sql = "INSERT INTO orders_switch (order_id,product_id,quantity) " +
                        "VALUES("+orderId+","+productId+","+quantity+");";
                connection.executeQuery(sql);
            }
            else {
                OrdersSwitch ordersSwitch = new OrdersSwitch(orderId, productId);
                ordersSwitch.setQuantity(productQuantity);
                int getQuantity =ordersSwitch.getQuantity();

                        sql = "UPDATE orders_switch SET quantity=quantity"+getQuantity+" WHERE order_id="+orderId+";";
                connection.executeQuery(sql);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        OrderSwitchDao orderSwitchDao = OrderSwitchDaoJdbc.getInstance();
        orderSwitchDao.add(2, 5, 8);

    }
}
