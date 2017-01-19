package com.codecool.shop.controller;

import com.codecool.moderator_service.controller.ModeratorAPIController;
import com.codecool.review_service.controller.ReviewAPIController;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.database.ProductDaoJdbc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ProductPageController.class);
    static ProductDao productDataStore = ProductDaoJdbc.getInstance();
    static CartController cartController = new CartController();


    public static String saveReview(Request req, Response res) throws IOException, URISyntaxException, HttpResponseException {
        String productName = req.params("productName");
        String userName = req.queryParams("name");
        String comment = req.queryParams("comment");
        String ratings = req.queryParams("ratings");
        String id = req.queryParams("id");
        logger.info("Start saving new review: Username: " + userName + ", Product name: " + productName + ", Comment: " + comment + ", Ratings: " + ratings);

        try {
            ModeratorAPIController moderatorController = new ModeratorAPIController();
            moderatorController.saveReview(productName, req, res);
        } catch (URISyntaxException e) {
            logger.error("Caught URISyntaxException from HorseShoeReview Service: " + e.getMessage());
        } catch (HttpResponseException u) {
            logger.error("Caught HttpResponseException from HorseShoeReview Service: " + u.getMessage());
        }
        res.redirect("/reviewFinder/" + id);
        return null;
    }

    public static ModelAndView renderReview(Request req, Response res) throws IOException, URISyntaxException {
        logger.info("Start getting reviews...");

        cartController.setSession(req);
        Map params = new HashMap<>();

        int id = Integer.parseInt(req.params("id"));
        logger.info("ID is: " + id);
        String productName = productDataStore.find(id).getName();

        params.put("moderated", requestAllApprovedReviewsOfProduct(productName, req, res));
        params.put("reviews", requestParsedReviews(productName, req, res));
        params.put("product", productDataStore.find(id));
        params.putAll(cartController.showShoppingCart(req));
        return new ModelAndView(params, "product/product");
    }

    private static ArrayList requestParsedReviews(String productName, Request req, Response res) throws IOException, URISyntaxException{
        try {
            ReviewAPIController finderController = new ReviewAPIController();
            String reviewJson = finderController.findReviews(productName, req, res);
            return parseReviews(reviewJson);
        }catch (URISyntaxException u) {
            logger.error("Caught URISyntaxException from Review Finder Service: " + u.getMessage());
        }catch (IOException e) {
            logger.error("Caught IOException from Review Finder Service: " + e.getMessage());
        }
        return null;
    }

    private static ArrayList requestAllApprovedReviewsOfProduct(String productName, Request req, Response res) throws IOException, URISyntaxException{
        try {
            ModeratorAPIController moderatorController = new ModeratorAPIController();
            String reviewJson = moderatorController.getAllApprovedReviewsOfProduct(productName, req, res);
            return parseReviews(reviewJson);
        }catch (URISyntaxException u){
            logger.error("Caught URISyntaxException from Horseshoe Review Service: " + u.getMessage());
        }catch (IOException e) {
            logger.error("Caught IOException from Horseshoe Review Service: " + e.getMessage());
        }
        return null;
    }

    private static ArrayList parseReviews(String json) throws IOException {
        logger.info("Parsing json object from Finder service...");
        HashMap<String, Object> result = new ObjectMapper().readValue(json, HashMap.class);
        ArrayList reviews = new ArrayList();
        for (Object element : result.values()) {
            reviews.add(element);
        }
        return reviews;
    }


}
