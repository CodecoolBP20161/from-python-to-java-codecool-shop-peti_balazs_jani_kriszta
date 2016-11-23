package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;


public class ProductDaoMemTest {

    private ProductDao productDao;

    private List<Product> testProductList = new ArrayList<>();
    private Supplier testSupplier1;
    private ProductCategory testCategory1;
    private Product testProduct2;
    private Product testProduct3;
    private List<Product> testProduct1InList = new ArrayList<>();


    @org.junit.Before
    public void setUp() throws Exception {
        productDao = ProductDaoMem.getInstance();

        // create test data
        Supplier supplier1 = new Supplier("suppliername1", "supplierdesc1");
        Supplier supplier2 = new Supplier("suppliername2", "supplierdesc2");

        ProductCategory productCategory1 = new ProductCategory("categoryname1", "department1", "categorydesc1");
        ProductCategory productCategory2 = new ProductCategory("categoryname2", "department2", "categorydesc2");

        Product product1 = new Product("productname1", 100, "USD", "productdesc1", productCategory1, supplier1);
        Product product2 = new Product("productname2", 200, "USD", "productdesc2", productCategory2, supplier2);
        Product product3 = new Product("productname3", 300, "USD", "productdesc3", productCategory2, supplier2);

        // add test products to productDaoMem's DATA arraylist
        productDao.add(product1);
        productDao.add(product2);

        // add the same test products to test data list
        testProductList.add(product1);
        testProductList.add(product2);

        // assign/add test objects
        testSupplier1 = supplier1;
        testCategory1 = productCategory1;
        testProduct2 = product2;
        testProduct3 = product3;
        testProduct1InList.add(product1);
    }

    @org.junit.Test
    public void add() throws Exception {
        testProductList.add(testProduct3);
        productDao.add(testProduct3);
        assertEquals(testProductList, productDao.getAll());
    }

    @Test
    public void find() throws Exception {
        assertEquals(testProduct2, productDao.find(2));
    }

    @org.junit.Test
    public void remove() throws Exception {
        testProductList.remove(testProduct2);
        productDao.remove(2);
        assertEquals(testProductList, productDao.getAll());
    }

    @Test
    public void getAll() throws Exception {
        assertEquals(testProductList, productDao.getAll());
    }

    @org.junit.Test
    public void getBySupplier() throws Exception {
        assertEquals(testProduct1InList, productDao.getBy(testSupplier1));
    }

    @org.junit.Test
    public void getByCategory() throws Exception {
        assertEquals(testProduct1InList, productDao.getBy(testCategory1));

    }

    @After
    public void tearDown() throws Exception {
        // removing objects from ProductDaoMem.DATA
        for (int i = productDao.getAll().size(); i > 0; i--){
            productDao.remove(i);
        }
    }

}