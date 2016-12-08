package com.codecool.shop.controller;

import com.codecool.shop.model.ShoppingCart;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by krisztinabaranyai on 07/12/2016.
 */
public class CartController extends ProductController {

    // Instantiate shopping cart if there is no shopping cart saved in session
     static void setSession(Request req){
        if(req.session().attribute("shoppingcart") == null) {
            ShoppingCart cart = new ShoppingCart();
            req.session().attribute("shoppingcart", cart);
        }
    }

    // collects shopping cart's data to show them on the modal and the shopping cart icon
     static Map showShoppingCart(Request req){
        ShoppingCart sessionCart = req.session().attribute("shoppingcart");
        Map params = new HashMap<>();

        params.put("lineitems", sessionCart.getAllLineItems());
        params.put("totalprice", sessionCart.getTotalPrice());
        params.put("counter", sessionCart.getTotalQuantity());

        params.put("categories", productCategoryDataStore.getAll());
        params.put("supplier", supplierDataStore.getAll());

        return params;
    }
    
    // Handle saving to cart and ensure staying on current page with redirect
    public static String saveToCart(Request req, Response res) {
        setSession(req);
        int id = Integer.parseInt(req.params("id"));
        ShoppingCart sessionCart = req.session().attribute("shoppingcart");
        sessionCart.addToCart(id);

        Map params = new HashMap<>();

        // Add shopping cart's data to params
        params.putAll(showShoppingCart(req));
        params.put("products", productDataStore.getAll());

        // Save uri into session for redirect
        res.redirect(req.session().attribute("uri"));

        return null;
    }

    public static String changeQuantityOfLineItem(Request req, Response res) {
        System.out.println("hello");
        setSession(req);
        int id = Integer.parseInt(req.params("id"));
        Integer quantity= Integer.parseInt(req.queryParams("quantity"));
        ShoppingCart sessionCart = req.session().attribute("shoppingcart");
        sessionCart.addToCart(id, quantity);

        Map params = new HashMap<>();

        // Add shopping cart's data to params
        params.putAll(showShoppingCart(req));
        params.put("products", productDataStore.getAll());

        // Save uri into session for redirect
        res.redirect(req.session().attribute("uri"));

        return null;
    }

    public static String deleteItem(Request req, Response res) {
        return null;
    }
}
