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


    private static void setSession(Request req){

        if(req.session().attribute("shoppingcart") == null) {
            ShoppingCart cart = new ShoppingCart();
            req.session().attribute("shoppingcart", cart);
        }
    }


    public static ModelAndView renderProducts(Request req, Response res) {

        setSession(req);
        ShoppingCart sessionCart = req.session().attribute("shoppingcart");
        System.out.println(req.session().id());

        Map params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getAll());
        params.put("supplier", supplierDataStore.getAll());
        params.put("counter", sessionCart.getTotalQuantity());
        return new ModelAndView(params, "product/index");
    }


    public static ModelAndView renderByFilter(Request req, Response res) {
        int id = Integer.parseInt(req.params("id"));
        Map params = new HashMap<>();

        ShoppingCart sessionCart = req.session().attribute("shoppingcart");

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
        params.put("counter", sessionCart.getTotalQuantity());

        return new ModelAndView(params, "product/index");
    }


    public static ModelAndView saveToCart(Request req, Response res) {
        int id = Integer.parseInt(req.params("id"));

        ShoppingCart sessionCart = req.session().attribute("shoppingcart");
        sessionCart.addToCart(id);

        // testing session storage
        System.out.println(sessionCart.getAllLineItems());
        System.out.println(sessionCart.getTotalPrice());
        System.out.println(sessionCart.getTotalQuantity());
        System.out.println(req.session().id());


        Map params = new HashMap<>();
        params.put("products", productDataStore.getAll());
        params.put("categories", productCategoryDataStore.getAll());
        params.put("supplier", supplierDataStore.getAll());
        params.put("counter", sessionCart.getTotalQuantity());

        return new ModelAndView(params, "product/index");
    }
}


