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
    private static ShoppingCart sessionCart;


    private static void setSession(Request req){
        req.session(true);
        req.session().attribute("shoppingcart", cart);
        sessionCart = req.session().attribute("shoppingcart");
    }

    public static ModelAndView renderProducts(Request req, Response res) {
        setSession(req);
        Map params = new HashMap<>();
        String currentUri = req.uri();
        System.out.println(currentUri);
        req.session().attribute("uri", currentUri);


        params.put("lineitems", sessionCart.getAllLineItems());
        params.put("totalprice", sessionCart.getTotalPrice());
        params.put("counter", sessionCart.getTotalQuantity());

        params.put("products", productDataStore.getAll());
        params.put("categories", productCategoryDataStore.getAll());
        params.put("supplier", supplierDataStore.getAll());


        return new ModelAndView(params, "product/index");
    }


    public static ModelAndView renderByFilter(Request req, Response res) {
        String currentUri = req.uri();
        System.out.println(currentUri);
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

        params.put("lineitems", sessionCart.getAllLineItems());
        params.put("totalprice", sessionCart.getTotalPrice());
        params.put("counter", sessionCart.getTotalQuantity());


        params.put("categories", productCategoryDataStore.getAll());
        params.put("supplier", supplierDataStore.getAll());

        return new ModelAndView(params, "product/index");
    }


    public static String saveToCart(Request req, Response res) {
        int id = Integer.parseInt(req.params("id"));

        sessionCart.addToCart(id);

        Map params = new HashMap<>();

        params.put("lineitems", sessionCart.getAllLineItems());
        params.put("counter", sessionCart.getTotalQuantity());
        params.put("totalprice", sessionCart.getTotalPrice());

        params.put("products", productDataStore.getAll());
        params.put("categories", productCategoryDataStore.getAll());
        params.put("supplier", supplierDataStore.getAll());

        res.redirect(req.session().attribute("uri"));

        return null;
    }

}


