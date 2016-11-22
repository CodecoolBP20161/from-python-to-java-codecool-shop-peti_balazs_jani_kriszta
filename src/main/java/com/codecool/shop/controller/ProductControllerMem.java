package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.dao.implementation.memory.SupplierDaoMem;


public class ProductControllerMem extends ProductController {

    public static void setAttributes() {
        ProductController.productDataStore = ProductDaoMem.getInstance();
        ProductController.productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductController.supplierDataStore = SupplierDaoMem.getInstance();
    }
}
