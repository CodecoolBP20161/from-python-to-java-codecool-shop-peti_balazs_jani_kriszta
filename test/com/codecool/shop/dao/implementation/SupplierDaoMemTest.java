package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.memory.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SupplierDaoMemTest {
    private SupplierDao supplierDao;
    private List<Supplier> testData = new ArrayList<>();
    private Supplier supplier2;

    @Before
    public void setUp() throws Exception {
        supplierDao = SupplierDaoMem.getInstance();

        //populate test datas
        Supplier supplier1 = new Supplier("suppliername1", "supplierdesc1");
        supplier2 = new Supplier("suppliername2", "supplierdesc2");

        //add suppliers to supplierDaoMem arraylist
        supplierDao.add(supplier1);
        supplierDao.add(supplier2);

        // add the same test suppliers to test data list
        testData.add(supplier1);
        testData.add(supplier2);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        //removing elements from supplierDao
        for(int i = supplierDao.getAll().size(); i > 0; i--){
            supplierDao.remove(i);
        }
    }

    @org.junit.Test
    public void add() throws Exception {
        Supplier supplier3 = new Supplier("suppliername3", "supplierdesc3");
        supplierDao.add(supplier3);
        testData.add(supplier3);
        assertEquals(testData, supplierDao.getAll());
    }

    @org.junit.Test
    public void find() throws Exception {
        assertEquals(supplier2, supplierDao.find(2));
    }

    @org.junit.Test
    public void remove() throws Exception {
        testData.remove(supplier2);
        supplierDao.remove(2);
        assertEquals(testData, supplierDao.getAll());
    }

    @org.junit.Test
    public void getAll() throws Exception {
        assertEquals(testData, supplierDao.getAll());
    }

}