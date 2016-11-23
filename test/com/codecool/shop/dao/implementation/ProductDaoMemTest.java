package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;


public class ProductDaoMemTest {

    private ProductDaoMem productDaoMem;

    private List<Product> testData = new ArrayList<>();

    private Supplier testSupplier;
    private ProductCategory testCategory;
    private Product testProduct;

    private List<Supplier> testSupplierList = new ArrayList<>();
    private List<Product> testProductList = new ArrayList<>();


    @org.junit.Before
    public void setUp() throws Exception {
        productDaoMem = ProductDaoMem.getInstance();

        // create test data
        Supplier supplier1 = new Supplier("suppliername1", "supplierdesc1");
        supplier1.setId(1);
        Supplier supplier2 = new Supplier("suppliername2", "supplierdesc2");
        supplier2.setId(2);

        ProductCategory productCategory1 = new ProductCategory("categoryname1", "department1", "categorydesc1");
        productCategory1.setId(1);
        ProductCategory productCategory2 = new ProductCategory("categoryname2", "department2", "categorydesc2");
        productCategory2.setId(2);

        Product product1 = new Product("productname1", 100, "USD", "productdesc1", productCategory1, supplier1);
        product1.setId(1);
        Product product2 = new Product("productname2", 100, "USD", "productdesc2", productCategory2, supplier2);
        product2.setId(2);

        // add test products to productDaoMem's DATA arraylist
        productDaoMem.add(product1);
        productDaoMem.add(product2);

        // add the same test products to test data list
        testData.add(product1);
        testData.add(product2);

        // assign/add test objects
        testSupplier = supplier1;
        testCategory = productCategory1;
        testProduct = product1;
        testSupplierList.add(supplier1);
        testProductList.add(product1);

    }


    @org.junit.Test
    public void getInstance() throws Exception {

    }

    @org.junit.Test
    public void add() throws Exception {

    }

    @Test
    public void find() throws Exception {
        assertEquals(testProduct, productDaoMem.find(1));
    }

    @org.junit.Test
    public void remove() throws Exception {

    }

    @Test
    public void getAll() throws Exception {
        assertEquals(testData, productDaoMem.getAll());
    }

    @org.junit.Test
    public void getBySupplier() throws Exception {
        assertEquals(testProductList, productDaoMem.getBy(testSupplier));
    }

    @org.junit.Test
    public void getByCategory() throws Exception {
        assertEquals(testProductList, productDaoMem.getBy(testCategory));

    }

    @After
    public void tearDown() throws Exception {
        productDaoMem.remove(1);
        productDaoMem.remove(2);
    }

}