package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.ShoppingCart;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;

public class ProductController {

    private static ProductDao productDataStore = ProductDaoMem.getInstance();
    private static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    private static SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
    private static ShoppingCart cart = ShoppingCart.getInstance();


    public static ModelAndView renderProducts(Request req, Response res) {
        Map params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getAll());
        params.put("supplier", supplierDataStore.getAll());
        params.put("counter", cart.getTotalQuantity());
        return new ModelAndView(params, "product/index");
    }


    public static ModelAndView renderByFilter(Request req, Response res) {
        int id = Integer.parseInt(req.params("id"));
        Map params = new HashMap<>();


        if (req.uri().contains("category")) {
            params.put("products", productDataStore.getBy(productCategoryDataStore.find(id)));
            params.put("title", productCategoryDataStore.find(id).getName());
            params.put("slogan", productCategoryDataStore.find(id).getDescription());
        } else if (req.uri().contains("supplier")) {
            params.put("products", productDataStore.getBy(supplierDataStore.find(id)));
            params.put("title", supplierDataStore.find(id).getName());
            params.put("slogan", supplierDataStore.find(id).getDescription());
        }

        params.put("categories", productCategoryDataStore.getAll());
        params.put("supplier", supplierDataStore.getAll());
        params.put("counter", cart.getTotalQuantity());

        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView saveToCart(Request req, Response res) {
        int id = Integer.parseInt(req.params("id"));
        ShoppingCart.addToCart(id);

        Map params = new HashMap<>();
        params.put("products", productDataStore.getAll());
        params.put("categories", productCategoryDataStore.getAll());
        params.put("supplier", supplierDataStore.getAll());
        params.put("counter", cart.getTotalQuantity());

        return new ModelAndView(params, "product/index");
    }

}


