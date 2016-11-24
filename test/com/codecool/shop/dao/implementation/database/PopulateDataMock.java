package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

public class PopulateDataMock {
    public static void populateDataMock() {

        ProductDao productDataStore = new ProductDaoJdbc();
        ProductCategoryDao productCategoryDataStore = new ProductCategoryDaoJdbc();
        SupplierDao supplierDataStore = new SupplierDaoJdbc();


        //setting up new suppliers
        Supplier supplier1 = new Supplier("supplier1", "supplierdesc1");
        supplierDataStore.add(supplier1);
        Supplier supplier2 = new Supplier("supplier2", "supplierdesc2");
        supplierDataStore.add(supplier2);
        Supplier supplier3 = new Supplier("supplier3", "supplierdesc3");
        supplierDataStore.add(supplier3);

        //setting up new product categories
        ProductCategory category1 = new ProductCategory("category1", "department1", "categorydesc1");
        productCategoryDataStore.add(category1);
        ProductCategory category2 = new ProductCategory("category2", "department2", "categorydesc2");
        productCategoryDataStore.add(category2);
        ProductCategory category3 = new ProductCategory("category3", "department3", "categorydesc3");
        productCategoryDataStore.add(category3);

        //setting up products
        productDataStore.add(new Product("product1", 100, "USD", "productdesc1", category1, supplier1));
        productDataStore.add(new Product("product2", 200, "USD", "productdesc2", category2, supplier2));
        productDataStore.add(new Product("product3", 300, "USD", "productdesc3", category3, supplier3));
    }
}
