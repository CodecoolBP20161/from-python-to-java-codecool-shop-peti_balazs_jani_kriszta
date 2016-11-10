package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.ShoppingCart;
import com.sun.jndi.toolkit.url.Uri;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {

    private static ProductDao productDataStore = ProductDaoMem.getInstance();
    private static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    private static SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
    private static ShoppingCart shoppingcart = ShoppingCart.getInstance();

    public static ModelAndView renderProducts(Request req, Response res) {
        int counter = 3;

        Map params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getAll());
        params.put("supplier", supplierDataStore.getAll());
        params.put("counter", counter);
        return new ModelAndView(params, "product/index");
    }


    public static ModelAndView renderByFilter(Request req, Response res) {
        int id = Integer.parseInt(req.params("id"));
        int counter = 3;

        Map params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("supplier", supplierDataStore.getAll());
        params.put("counter", counter);


        if (req.uri().contains("category")) {
            params.put("products", productDataStore.getBy(productCategoryDataStore.find(id)));
            params.put("title", productCategoryDataStore.find(id).getName());
        } else if (req.uri().contains("supplier")) {
            params.put("products", productDataStore.getBy(supplierDataStore.find(id)));
            params.put("title", supplierDataStore.find(id).getName());
            params.put("slogan", supplierDataStore.find(id).getDescription());
        }

        return new ModelAndView(params, "product/index");
    }
}


