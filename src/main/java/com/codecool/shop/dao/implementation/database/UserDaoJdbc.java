package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    private DBConnection connection = new DBConnection();
    private String sql;
    public User user;
    private static UserDaoJdbc instance = null;


    private UserDaoJdbc() {
    }

    public static UserDaoJdbc getInstance() {
        if (instance == null) {
            instance = new UserDaoJdbc();
        }
        return instance;
    }

    public void add(User user) {
        String name = user.getName();
        String email = user.getEmail();
        String phone = user.getPhone();
        String billingAddress = user.getBilling_address().toString();
        String shippingAddress = user.getShipping_address().toString();
        sql = "INSERT INTO user (name,email,phone, billing_address, shipping_address) " +
                "VALUES('" + name + "','" + email + "','" + phone + "','" + billingAddress + "','" + shippingAddress + "');";
        connection.executeQuery(sql);
    }

    public User find(int id) {
        sql = "SELECT * FROM user WHERE id=" + id + ";";
        try (Connection conn = connection.connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            if (rs.next()) {
                User findUser = new User(rs.getString("name"), rs.getString("email"), rs.getString("phone"));
                List<String> billing = Arrays.asList(rs.getString("billing_address").split(", "));
                List<String> shipping = Arrays.asList(rs.getString("shipping_address").split(", "));
                findUser.setBilling_address(billing.get(0), billing.get(1), billing.get(2), billing.get(3));
                findUser.setShipping_address(shipping.get(0), shipping.get(1), shipping.get(2), shipping.get(3));
                return findUser;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void remove(int id) {
        sql = "DELETE FROM user WHERE id = " + id + ";";
        connection.executeQuery(sql);
    }
}
