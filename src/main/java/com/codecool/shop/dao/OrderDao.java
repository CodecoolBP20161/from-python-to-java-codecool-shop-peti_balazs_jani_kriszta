package com.codecool.shop.dao;
import com.codecool.shop.model.Product;
import java.util.List;

/**
 * Created by krisztinabaranyai on 09/11/2016.
 */
public interface OrderDao {
    void add(Product product);
    Product find(int id);
    void remove(int id);

    List<Product> getAll();
}