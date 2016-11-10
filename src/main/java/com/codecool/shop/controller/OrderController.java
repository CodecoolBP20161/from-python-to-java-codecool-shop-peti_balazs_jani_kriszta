//package com.codecool.shop.controller;
//
//
//import com.codecool.shop.dao.ProductDao;
//import com.codecool.shop.dao.implementation.ProductDaoMem;
//import com.codecool.shop.model.ShoppingCart;
//import spark.ModelAndView;
//import spark.Request;
//import spark.Response;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class OrderController {
//    private static ProductDao productDataStore = ProductDaoMem.getInstance();
//    private static ShoppingCart shoppingcart = ShoppingCart.getInstance();
//
//    public static ModelAndView renderShoppingcart(Request req, Response res) {
//        int id = Integer.parseInt(req.params("id"));
//        Map params = new HashMap();
//
//        return new ModelAndView(params, "product/shoppingcart");
//    }
//}
