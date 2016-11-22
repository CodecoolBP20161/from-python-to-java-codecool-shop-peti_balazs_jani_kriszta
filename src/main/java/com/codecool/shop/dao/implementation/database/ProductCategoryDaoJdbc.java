package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {
    private String sql;
    private DBConnection connection = new DBConnection();
    private Statement stmt;

    @Override
    public void add(ProductCategory category) {
        String name = category.getName();
        String description = category.getDescription();
        String department = category.getDepartment();
        sql = "INSERT INTO product_categories (name, description, department) VALUES ('" + name + "','" + description + "','" + department + "')";
        connection.executeQuery(sql);
    }

    @Override
    public ProductCategory find(int productID) {
        sql = "SELECT * FROM product_categories WHERE id="+productID+";";
        ProductCategory productCategory;
        try (Connection conn = connection.connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            if (rs.next()){
                return productCategory = new ProductCategory(rs.getString("name"), rs.getString("department"), rs.getString("description"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int productID) {
        String query = "DELETE FROM suppliers WHERE id = " + productID + ";";
        connection.executeQuery(query);
    }

    @Override
    public List<ProductCategory> getAll() {
        ProductCategory productCategory;
        List<ProductCategory> allCategories;
        sql = "SELECT * FROM product_categories;";

        try (Connection conn = connection.connect();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql)){
            allCategories = new ArrayList<>();
            while (rs.next()){
                productCategory= new ProductCategory(rs.getString("name"),rs.getString("department"),rs.getString("description"));
                productCategory.setId(rs.getInt("id"));
                allCategories.add(productCategory);
            }
            return allCategories;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
