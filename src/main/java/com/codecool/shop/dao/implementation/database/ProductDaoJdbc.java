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
    public List<Product> allProducts;

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
        ProductCategoryDao productCategoryDao = new  ProductCategoryDaoJdbc();
        SupplierDao supplierDao = new SupplierDaoJdbc();
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

    }

    @Override
    public List<Product> getAll() {
        Product product;
        allProducts = new ArrayList<>();
        ProductCategoryDao productCategoryDao = new  ProductCategoryDaoJdbc();
        SupplierDao supplierDao = new SupplierDaoJdbc();
        sql = "SELECT * FROM products;";

        try (Connection conn = connection.connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)){
            while (rs.next()){
                ProductCategory category_name = productCategoryDao.find(rs.getInt("category_id"));
                Supplier supplier_name = supplierDao.find(rs.getInt("supplier_id"));
                product= new Product(rs.getString("name"), rs.getFloat("default_price"),rs.getString("default_currency"),
                        rs.getString("description"),category_name, supplier_name);
                product.setId(rs.getInt("id"));
                allProducts.add(product);
            }
            return allProducts;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        allProducts = new ArrayList<>();
        Product product;
        int suppId = getIdFromTable("suppliers",supplier.getName());
        sql = "SELECT * FROM products WHERE supplier_id='"+suppId+"';";
        ProductCategoryDao productCategoryDao = new  ProductCategoryDaoJdbc();
        SupplierDao supplierDao = new SupplierDaoJdbc();
        try (Connection conn = connection.connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                ProductCategory category_name = productCategoryDao.find(rs.getInt("category_id"));
                Supplier supplier_name = supplierDao.find(rs.getInt("supplier_id"));
                product= new Product(rs.getString("name"), rs.getFloat("default_price"),rs.getString("default_currency"),
                        rs.getString("description"),category_name, supplier_name);
                product.setId(rs.getInt("id"));
                allProducts.add(product);
            }
            return allProducts;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        allProducts = new ArrayList<>();
        Product product;
        int suppId = getIdFromTable("product_categories",productCategory.getName());
        sql = "SELECT * FROM products WHERE category_id='"+suppId+"';";
        ProductCategoryDao productCategoryDao = new  ProductCategoryDaoJdbc();
        SupplierDao supplierDao = new SupplierDaoJdbc();
        try (Connection conn = connection.connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                ProductCategory category_name = productCategoryDao.find(rs.getInt("category_id"));
                Supplier supplier_name = supplierDao.find(rs.getInt("supplier_id"));
                product= new Product(rs.getString("name"), rs.getFloat("default_price"),rs.getString("default_currency"),
                        rs.getString("description"),category_name, supplier_name);
                product.setId(rs.getInt("id"));
                allProducts.add(product);
            }
            return allProducts;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
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
}
