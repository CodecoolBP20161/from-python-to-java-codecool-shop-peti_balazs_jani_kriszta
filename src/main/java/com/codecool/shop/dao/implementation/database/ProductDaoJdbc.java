package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProductDaoJdbc implements ProductDao {
    private DBConnection connection = new DBConnection();
    private String sql;
    public Product product;
    public List<Product> returnProductsList;
    private static ProductDaoJdbc instance = null;

    private ProductDaoJdbc() {
    }

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        String name = product.getName();
        System.out.println(name);
        int catId = getIdFromTable("product_categories", product.getProductCategory().getName());
        int suppId = getIdFromTable("suppliers",product.getSupplier().getName());
        String description = product.getDescription();
        float defPrice = product.getDefaultPrice();
        String defCurr = product.getDefaultCurrency().toString();
        sql = "INSERT INTO products (name,category_id,supplier_id, description, default_price,default_currency) " +
                    "VALUES('"+name+"','"+catId+"','"+suppId+"','"+description+"','"+defPrice+"','"+defCurr+"');";
        connection.executeQuery(sql);
    }

    @Override
    public Product find(int id) {
        sql = "SELECT * FROM products WHERE id="+id+";";
        ProductCategoryDao productCategoryDao = ProductCategoryDaoJdbc.getInstance();
        SupplierDao supplierDao = SupplierDaoJdbc.getInstance();
        try (Connection conn = connection.connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            if (rs.next()){
                ProductCategory category_name = productCategoryDao.find(rs.getInt("category_id"));
                Supplier supplier_name = supplierDao.find(rs.getInt("supplier_id"));
                return new Product(rs.getString("name"), rs.getFloat("default_price"),rs.getString("default_currency"),
                        rs.getString("description"),category_name, supplier_name);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        String sql = "DELETE FROM suppliers WHERE id = " + id + ";";
        connection.executeQuery(sql);
    }

    @Override
    public List<Product> getAll() {
        sql = "SELECT * FROM products;";
        updateReturnProductList(sql);
        return returnProductsList;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        int suppId = getIdFromTable("suppliers",supplier.getName());
        sql = "SELECT * FROM products WHERE supplier_id='"+suppId+"';";
        updateReturnProductList(sql);
        return returnProductsList;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        int suppId = getIdFromTable("product_categories",productCategory.getName());
        sql = "SELECT * FROM products WHERE category_id='"+suppId+"';";
        updateReturnProductList(sql);
        return returnProductsList;
    }
    private int getIdFromTable(String tableName, String name){
        sql = "SELECT id FROM "+tableName+" WHERE name='"+name+"';";
        try (Connection conn = connection.connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)){
            if (rs.next()){
                return rs.getInt("id");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    private void updateReturnProductList(String sql){
        returnProductsList = new ArrayList<>();
        ProductCategoryDao productCategoryDao = ProductCategoryDaoJdbc.getInstance();
        SupplierDao supplierDao = SupplierDaoJdbc.getInstance();
        try (Connection conn = connection.connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                ProductCategory category_name = productCategoryDao.find(rs.getInt("category_id"));
                Supplier supplier_name = supplierDao.find(rs.getInt("supplier_id"));
                product= new Product(rs.getString("name"), rs.getFloat("default_price"),rs.getString("default_currency"),
                        rs.getString("description"),category_name, supplier_name);
                product.setId(rs.getInt("id"));
                returnProductsList.add(product);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
