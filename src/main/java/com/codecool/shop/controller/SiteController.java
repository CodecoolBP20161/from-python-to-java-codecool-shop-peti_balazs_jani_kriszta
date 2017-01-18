package com.codecool.shop.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by krisztinabaranyai on 07/12/2016.
 */
public class SiteController {

    public static ModelAndView renderByFilter(Request req, Response res) {
        return ProductController.renderByFilter(req, res);
    }

    public static ModelAndView renderProducts(Request req, Response res) {
        return ProductController.renderProducts(req, res);
    }

    public static String saveToCart(Request req, Response res){
        return CartController.saveToCart(req, res);
    }

    public static String increase(Request req, Response res){
        return CartController.increase(req, res);
    }

    public static String decrease(Request req, Response res){
        return CartController.decrease(req, res);
    }

    public static ModelAndView deleteItem(Request req, Response res) {
        return CartController.deleteItem(req, res);
    }

    public static ModelAndView renderCart(Request req, Response res) {
        return CartController.renderCart(req, res);
    }

    public static ModelAndView renderReview(Request req, Response res) throws IOException, URISyntaxException{
        return ProductPageController.renderReview(req, res);
    }


}
