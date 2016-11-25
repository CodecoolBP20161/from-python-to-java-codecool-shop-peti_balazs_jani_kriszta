package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SupplierDaoJdbcTest {

    private SupplierDao supplierDao;

    private List<Supplier> testSupplierList = new ArrayList<>();
    Supplier testSupplier2;
    @Before
    public void setUp() throws Exception {
        /*
         populate testProductList
         */
        //setting up new suppliers
        Supplier supplier1 = new Supplier("supplier1", "supplierdesc1");
        Supplier supplier2 = new Supplier("supplier2", "supplierdesc2");
        Supplier supplier3 = new Supplier("supplier3", "supplierdesc3");
        supplier1.setId(1);
        supplier2.setId(2);
        supplier3.setId(3);

        testSupplierList.add(supplier1);
        testSupplierList.add(supplier2);
        testSupplierList.add(supplier3);

        testSupplier2 = supplier2;

        /*
        clearing database before populate
         */
        String url = "jdbc:postgresql://localhost:5432/codecoolshop";
        String username = "postgres";
        String password = "postgres";

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement stmt = connection.createStatement();

        // truncate tables, reset id sequence
        String sql = "TRUNCATE products, product_categories, suppliers; ALTER SEQUENCE suppliers_id_seq RESTART;";
        // Execute deletion
        stmt.executeUpdate(sql);

        // repopulate tables
        PopulateDataMock.populateDataMock();

        supplierDao = SupplierDaoJdbc.getInstance();
    }

    @After
    public void tearDown() throws Exception {
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

        // create another testSupplier
        Supplier testSupplier4 = new Supplier("suppliername4", "supplierdesc4");
        testSupplier4.setId(4);

        testSupplierList.add(testSupplier4);
        supplierDao.add(testSupplier4);

        //we have to convert to string without it after the testSupplierList is added a whitespace ???
        assertEquals("testing add()", testSupplierList.toString(), supplierDao.getAll().toString());
    }

    @Test
    public void find() throws Exception {
        //we have to convert to string without it after the testSupplier2 is added a whitespace ???
        assertEquals("testing find()", testSupplier2.toString(), supplierDao.find(2).toString());
    }

    @Test
    public void remove() throws Exception {

    }

    @Test
    public void getAll() throws Exception {
        //we have to convert to string without it after the testSupplierList is added a whitespace ???
        assertEquals("testing getAll()", testSupplierList.toString(), supplierDao.getAll().toString());
    }

}