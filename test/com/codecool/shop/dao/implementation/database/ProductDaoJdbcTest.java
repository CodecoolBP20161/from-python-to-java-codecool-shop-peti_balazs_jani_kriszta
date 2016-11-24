package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.PopulateData;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.Assert.*;


public class ProductDaoJdbcTest {

    private ProductDao productDao;


    @Before
    public void setUp() throws Exception {
        /*
        clearing database before populate
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

        // repopulate tables
        PopulateDataMock.populateDataMock();

        productDao = new ProductDaoJdbc();
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

    }

    @Test
    public void find() throws Exception {

    }

    @Test
    public void remove() throws Exception {

    }

    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void getBy() throws Exception {

    }

    @Test
    public void getBy1() throws Exception {

    }

}