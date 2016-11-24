package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.database.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.database.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.database.SupplierDaoJdbc;


public class ProductControllerJdbc extends ProductController{

    public static void setAttributes() {
        ProductController.productDataStore = new ProductDaoJdbc();
        ProductController.productCategoryDataStore = new ProductCategoryDaoJdbc();
        ProductController.supplierDataStore = new SupplierDaoJdbc();
    }
}