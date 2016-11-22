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
        int counter = 1;
        try {
            category.setId(counter);
            sql = "INSERT INTO product_categories (name, description, department) VALUES ('" + name + "','" + description + "','" + department + "')";
            stmt = connection.getConnection().createStatement();
            stmt.executeUpdate(sql);
            counter++;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductCategory find(int productID) {
        try {
            sql = "SELECT * FROM product_categories WHERE id="+productID+";";
            stmt = connection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ProductCategory productCategory;
            if (rs.next()){
                return productCategory= new ProductCategory(rs.getString("name"), rs.getString("department"), rs.getString("description"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int productID) {
        try {
            sql = "DELETE FROM product_categories WHERE id="+productID+";";
            stmt = connection.getConnection().createStatement();
            stmt.executeQuery(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        ProductCategory productCategory;
        List<ProductCategory> allCategories;
        try {
            sql = "SELECT * FROM product_categories;";
            stmt = connection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
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

//    private java.sql.Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(
//                DATABASE,
//                DB_USER,
//                DB_PASSWORD);
//    }
//
//    private void executeQuery(String query) {
//        try (java.sql.Connection connection = getConnection();
//             Statement statement = connection.createStatement();
//        ) {
//            statement.execute(query);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
