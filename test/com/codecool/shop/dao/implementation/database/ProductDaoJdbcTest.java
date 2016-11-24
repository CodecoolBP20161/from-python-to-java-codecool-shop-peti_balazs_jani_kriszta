package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ProductDaoJdbcTest {

    private ProductDao productDao;

    private List<Product> testProductList = new ArrayList<>();
    Product testProduct2;


    @Before
    public void setUp() throws Exception {

        /*
         populate testProductList
         */
        //setting up new suppliers
        Supplier supplier1 = new Supplier("supplier1", "supplierdesc1");
        Supplier supplier2 = new Supplier("supplier2", "supplierdesc2");
        Supplier supplier3 = new Supplier("supplier3", "supplierdesc3");

        //setting up new product categories
        ProductCategory category1 = new ProductCategory("category1", "department1", "categorydesc1");
        ProductCategory category2 = new ProductCategory("category2", "department2", "categorydesc2");
        ProductCategory category3 = new ProductCategory("category3", "department3", "categorydesc3");

        //setting up products
        Product product1 = new Product("product1", 100, "USD", "productdesc1", category1, supplier1);
        product1.setId(1);
        Product product2 = new Product("product2", 200, "USD", "productdesc2", category2, supplier2);
        product2.setId(2);
        Product product3 = new Product("product3", 300, "USD", "productdesc3", category3, supplier3);
        product3.setId(3);

        testProductList.add(product1);
        testProductList.add(product2);
        testProductList.add(product3);

        testProduct2 = product2;

        /*
        clearing database before populate
         */
        String url = "jdbc:postgresql://localhost:5432/codecoolshop";
        String username = "postgres";
        String password = "postgres";

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement stmt = connection.createStatement();

        // truncate tables, reset id sequence
        String sql = "TRUNCATE products, product_categories, suppliers; ALTER SEQUENCE products_id_seq RESTART;";
        // Execute deletion
        stmt.executeUpdate(sql);

        // repopulate tables
        PopulateDataMock.populateDataMock();

        productDao = ProductDaoJdbc.getInstance();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        /*
        clearing tables after tests so it won't cause a problem later
         */
        String url = "jdbc:postgresql://localhost:5432/codecoolshop";
        String username = "postgres";
        String password = "postgres";

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement stmt = connection.createStatement();

        // Use TRUNCATE
        String sql = "TRUNCATE products, product_categories, suppliers";
        // Execute deletion
        stmt.executeUpdate(sql);
    }

    @Test
    public void add() throws Exception {
        // create another testProduct & add category + supplier to database
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();
        SupplierDao supplierDataStore = SupplierDaoJdbc.getInstance();

        Supplier supplier4 = new Supplier("suppliername4", "supplierdesc4");
        supplierDataStore.add(supplier4);
        ProductCategory productCategory4 = new ProductCategory("categoryname4", "department4", "categorydesc4");
        productCategoryDataStore.add(productCategory4);
        Product testProduct4 = new Product("productname4", 400, "USD", "productdesc4", productCategory4, supplier4);
        testProduct4.setId(4);

        testProductList.add(testProduct4);
        productDao.add(testProduct4);

        assertEquals("testing add()", testProductList, productDao.getAll());
    }

    @Test
    public void find() throws Exception {
        assertEquals("testing find()", testProduct2, productDao.find(2));
    }

    @Test
    public void remove() throws Exception {

    }

    @Test
    public void getAll() throws Exception {
        assertEquals("testing getAll()", testProductList, productDao.getAll());

    }

    @Test
    public void getBy() throws Exception {

    }

    @Test
    public void getBy1() throws Exception {

    }

}