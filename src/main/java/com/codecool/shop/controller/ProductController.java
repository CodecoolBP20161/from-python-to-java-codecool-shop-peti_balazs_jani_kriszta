package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.ShoppingCart;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public abstract class ProductController {

    static ProductDao productDataStore;
    static ProductCategoryDao productCategoryDataStore;
    static SupplierDao supplierDataStore;


    // Instantiate shopping cart if there is no shopping cart saved in session
    private static void setSession(Request req){
        if(req.session().attribute("shoppingcart") == null) {
            ShoppingCart cart = new ShoppingCart();
            req.session().attribute("shoppingcart", cart);
        }
    }

    // collects shopping cart's data to show them on the modal and the shopping cart icon
    private static Map showShoppingCart(Request req){
        ShoppingCart sessionCart = req.session().attribute("shoppingcart");
        Map params = new HashMap<>();

        params.put("lineitems", sessionCart.getAllLineItems());
        params.put("totalprice", sessionCart.getTotalPrice());
        params.put("counter", sessionCart.getTotalQuantity());

        params.put("categories", productCategoryDataStore.getAll());
        params.put("supplier", supplierDataStore.getAll());

        return params;
    }

    // Render all products and filter menu elements
    public static ModelAndView renderProducts(Request req, Response res) {
        setSession(req);
        // Read data from from the shopping cart saved in the session

        Map params = new HashMap<>();
        String currentUri = req.uri();
        req.session().attribute("uri", currentUri);

        // put shopping cart's data to params
        params.putAll(showShoppingCart(req));

        params.put("products", productDataStore.getAll());
        params.put("title", "Codecool Shop");

        return new ModelAndView(params, "product/index");
    }

    // Handle filter routes Category and Supplier
    public static ModelAndView renderByFilter(Request req, Response res) {
        setSession(req);
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

        // put shopping cart's data to params
        params.putAll(showShoppingCart(req));

        return new ModelAndView(params, "product/index");
    }

    // Handle saving to cart and ensure staying on current page with redirect
    public static String saveToCart(Request req, Response res) {
        setSession(req);
        int id = Integer.parseInt(req.params("id"));

        ShoppingCart sessionCart = req.session().attribute("shoppingcart");
        sessionCart.addToCart(id);

        Map params = new HashMap<>();

        // put shopping cart's data to params
        params.putAll(showShoppingCart(req));

        params.put("products", productDataStore.getAll());

        // Save uri into session for redirect
        res.redirect(req.session().attribute("uri"));

        return null;
    }
}


