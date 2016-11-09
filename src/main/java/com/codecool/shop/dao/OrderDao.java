package com.codecool.shop.dao;
import com.codecool.shop.model.Order;

import java.util.List;

/**
 * Created by krisztinabaranyai on 09/11/2016.
 */
public interface OrderDao {
    void add(Order order);
    Order find(int id);
    void remove(int id);

    List<Order> getAll();
}