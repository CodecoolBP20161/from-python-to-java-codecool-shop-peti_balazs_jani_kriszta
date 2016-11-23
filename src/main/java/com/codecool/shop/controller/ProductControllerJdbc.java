package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.database.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.database.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.database.SupplierDaoJdbc;


public class ProductControllerJdbc extends ProductController{

    public static void setAttributes() {
        ProductControllerMem.productDataStore = new ProductDaoJdbc();
        ProductControllerMem.productCategoryDataStore = new ProductCategoryDaoJdbc();
        ProductControllerMem.supplierDataStore = new SupplierDaoJdbc();
    }
}