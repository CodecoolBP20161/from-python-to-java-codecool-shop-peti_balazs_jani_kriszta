package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.ProductCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ProductCategoryDaoMemTest {

    @Mock
    ProductCategory productCategory = new ProductCategory("TestName", "TestDepartment", "TestDescription");
    ProductCategory productCategory2 = new ProductCategory("TestName2", "TestDepartment", "TestDescription");

    ProductCategoryDaoMem productCategoryDaoMem;
    private ArrayList<ProductCategory> testList = new ArrayList<>();
    private List<ProductCategory> getAllTestList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        productCategoryDaoMem = ProductCategoryDaoMem.getInstance();
        productCategoryDaoMem.clear();

        testList.clear();
        getAllTestList.clear();

        testList.add(productCategory);
        getAllTestList.add(productCategory);
        getAllTestList.add(productCategory2);
    }

    @Test
    public void add() throws Exception {
        productCategoryDaoMem.add(productCategory);
        assertEquals("Test add",this.testList, productCategoryDaoMem.getAll());
    }

    @Test
    public void find() throws Exception {
        productCategoryDaoMem.add(productCategory);
        assertEquals("Test find",productCategory, productCategoryDaoMem.find(productCategory.getId()));
    }

    @Test
    public void remove() throws Exception {
        productCategoryDaoMem.add(productCategory);
        assertTrue(productCategoryDaoMem.getAll().contains(productCategory));
        productCategoryDaoMem.remove(productCategory.getId());
        assertTrue("Test remove",!productCategoryDaoMem.getAll().contains(productCategory));
    }

    @Test
    public void getAll() throws Exception {
        productCategoryDaoMem.add(productCategory);
        productCategoryDaoMem.add(productCategory2);
        assertEquals("Get all test",productCategoryDaoMem.getAll(), getAllTestList);
    }
}