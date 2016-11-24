package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;


public class ProductDaoMemTest {

    private ProductDao productDao;

    // test list containing all (2) products
    private List<Product> testProductList = new ArrayList<>();

    // test objects
    private Supplier testSupplier1;
    private ProductCategory testCategory1;
    private Product testProduct2;
    private List<Product> testProduct1InList = new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        productDao = ProductDaoMem.getInstance();

        // create test data
        Supplier supplier1 = new Supplier("suppliername1", "supplierdesc1");
        Supplier supplier2 = new Supplier("suppliername2", "supplierdesc2");
        ProductCategory productCategory1 = new ProductCategory("categoryname1", "department1", "categorydesc1");
        ProductCategory productCategory2 = new ProductCategory("categoryname2", "department2", "categorydesc2");
        Product product1 = new Product("productname1", 100, "USD", "productdesc1", productCategory1, supplier1);
        Product product2 = new Product("productname2", 200, "USD", "productdesc2", productCategory2, supplier2);

        // add test products to productDaoMem's DATA arraylist using it's add() method
        productDao.add(product1);
        productDao.add(product2);

        // add the same test products to test data list
        testProductList.add(product1);
        testProductList.add(product2);

        // assign/add test objects
        testSupplier1 = supplier1;
        testCategory1 = productCategory1;
        testProduct2 = product2;
        testProduct1InList.add(product1);
    }

    @Test
    public void add() throws Exception {
        // create another testProduct
        Supplier supplier3 = new Supplier("suppliername3", "supplierdesc3");
        ProductCategory productCategory3 = new ProductCategory("categoryname3", "department3", "categorydesc3");
        Product testProduct3 = new Product("productname3", 300, "USD", "productdesc3", productCategory3, supplier3);

        testProductList.add(testProduct3);
        productDao.add(testProduct3);

        assertEquals("testing add()", testProductList, productDao.getAll());
    }

    @Test
    public void find() throws Exception {
        assertEquals("testing find()", testProduct2, productDao.find(2));
    }

    @Test
    public void remove() throws Exception {
        testProductList.remove(testProduct2);
        productDao.remove(2);

        assertEquals("testing remove()", testProductList, productDao.getAll());
    }

    @Test
    public void getAll() throws Exception {
        assertEquals("testing getAll()", testProductList, productDao.getAll());
    }

    @Test
    public void getBySupplier() throws Exception {
        assertEquals("testing getBy(supplier)", testProduct1InList, productDao.getBy(testSupplier1));
    }

    @Test
    public void getByCategory() throws Exception {
        assertEquals("testing getBy(category)", testProduct1InList, productDao.getBy(testCategory1));

    }

    @After
    public void tearDown() throws Exception {
        /* removing objects from ProductDaoMem.DATA
           it is possible only via remove() using the product's ID */
        for (int i = productDao.getAll().size(); i > 0; i--){
            productDao.remove(i);
        }
    }

}