package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.Before;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by csyk on 2016.11.23..
 */
public class SupplierDaoMemTest {
    private SupplierDao supplierDao;
    private List<Supplier> testData = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        supplierDao = SupplierDaoMem.getInstance();

        //populate test datas
        Supplier supplier1 = new Supplier("suppliername1", "supplierdesc1");
        supplier1.setId(1);
        Supplier supplier2 = new Supplier("suppliername2", "supplierdesc2");
        supplier2.setId(2);

        ProductCategory productCategory1 = new ProductCategory("categoryname1", "department1", "categorydesc1");
        productCategory1.setId(1);
        ProductCategory productCategory2 = new ProductCategory("categoryname2", "department2", "categorydesc2");
        productCategory2.setId(2);

        //add suppliers to supplierDaoMem arraylist
        supplierDao.add(supplier1);
        supplierDao.add(supplier2);

        // add the same test suppliers to test data list
        testData.add(supplier1);
        testData.add(supplier2);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        //remove
    }

    @org.junit.Test
    public void add() throws Exception {

    }

    @org.junit.Test
    public void find() throws Exception {

    }

    @org.junit.Test
    public void remove() throws Exception {

    }

    @org.junit.Test
    public void getAll() throws Exception {
        assertEquals(testData, supplierDao.getAll());
    }

}