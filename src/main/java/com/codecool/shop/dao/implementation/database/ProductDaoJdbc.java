package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class ProductDaoJdbc implements ProductDao {
    private DBConnection connection = new DBConnection();
    private String sql;
    private Statement stmt;

    @Override
    public void add(Product product) {
        String name = product.getName();
        int catId = product.getProductCategory().getId();
        int suppId = product.getSupplier().getId();
        String description = product.getDescription();
        float defPrice = product.getDefaultPrice();
        String defCurr = product.getDefaultCurrency().getDisplayName();
        try {
            sql = "INSERT INTO products (name,category_id,supplier_id, description, default_price,default_currency) " +
                    "VALUES ('"+name+"','"+catId+"','"+suppId+"','"+description+"','"+defPrice+"','"+defCurr+"');";
            stmt = connection.getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
