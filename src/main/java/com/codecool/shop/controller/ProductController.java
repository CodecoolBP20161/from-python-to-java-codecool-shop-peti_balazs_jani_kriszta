package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public abstract class ProductController {

    static ProductDao productDataStore;
    static ProductCategoryDao productCategoryDataStore;
    static SupplierDao supplierDataStore;
    static CartController cartController = new CartController();


    // Render all products and filter menu elements
        public static ModelAndView renderProducts(Request req, Response res) {
        // Read data from the shopping cart saved in the session
        cartController.setSession(req);
            cartController.deleteZeroQuantityLineItems(req);

        Map params = new HashMap<>();
        String currentUri = req.uri();
        req.session().attribute("uri", currentUri);

        // Add shopping cart's data to params
        params.putAll(cartController.showShoppingCart(req));
        params.put("products", productDataStore.getAll());
        params.put("title", "Horsecool Shop");

        return new ModelAndView(params, "product/index");
    }

    // Handle filter routes Category and Supplier
    public static ModelAndView renderByFilter(Request req, Response res) {
        cartController.setSession(req);
        String currentUri = req.uri();
        req.session().attribute("uri", currentUri);
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

        // Add shopping cart's data to params
        params.putAll(cartController.showShoppingCart(req));

        return new ModelAndView(params, "product/index");
    }



}


