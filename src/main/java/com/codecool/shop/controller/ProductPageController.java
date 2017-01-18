package com.codecool.shop.controller;

import com.codecool.moderator_service.controller.ModeratorAPIController;
import com.codecool.review_service.controller.ReviewAPIController;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.database.ProductDaoJdbc;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by csyk on 2017.01.17..
 */
public class ProductPageController {
    static ProductDao productDataStore = ProductDaoJdbc.getInstance();
    static CartController cartController = new CartController();


    public static String saveReview(Request req, Response res) {
        String body = req.body();
        System.out.println(req.queryParams("comment"));
        System.out.println(body);
//        req.qoeryParams("name")
//        ModeratorAPIController moderatorController = new ModeratorAPIController();
//        moderatorController.saveReview(req);

        return null;
    }

    public static ModelAndView renderReview(Request req, Response res) throws IOException, URISyntaxException {
        cartController.setSession(req);
        Map params = new HashMap<>();

        int id = Integer.parseInt(req.params("id"));
        String productName = productDataStore.find(id).getName();

        params.put("moderated", getAllApprovedReviewsOfProduct(productName, req, res));
        params.put("reviews", getParsedReviews(productName, req, res));
        params.put("product", productDataStore.find(id));
        params.putAll(cartController.showShoppingCart(req));
        return new ModelAndView(params, "product/product");
    }

    private static ArrayList getParsedReviews(String productName, Request req, Response res) throws URISyntaxException{
        try {
            ReviewAPIController finderController = new ReviewAPIController();
            String reviewJson = finderController.findReviews(productName, req, res);
            return parseReviews(reviewJson);
        } catch (IOException e) {
            System.out.println("Caught IOException from Review Finder Service: " + e.getMessage());
            return null;
        }
    }

    private static ArrayList getAllApprovedReviewsOfProduct(String productName, Request req, Response res) throws URISyntaxException{
        try {
            ModeratorAPIController moderatorController = new ModeratorAPIController();
            String reviewJson = moderatorController.getAllApprovedReviewsOfProduct(productName, req, res);
            return parseReviews(reviewJson);
        } catch (IOException e) {
            System.out.println("Caught IOException from Horseshoe Review Service: " + e.getMessage());
            return null;
        }
    }

    private static ArrayList parseReviews(String json) throws IOException {
        HashMap<String, Object> result = new ObjectMapper().readValue(json, HashMap.class);
        ArrayList reviews = new ArrayList();
        for (Object element : result.values()) {
            reviews.add(element);
        }
        return reviews;
    }


}
