package com.codecool.shop.controller;

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



    public static ModelAndView renderReview(Request req, Response res) throws IOException, URISyntaxException {
        cartController.setSession(req);

        int id = Integer.parseInt(req.params("id"));
        String productName = productDataStore.find(id).getName();


        ReviewAPIController finderController = new ReviewAPIController();
        String reviewJson = finderController.findReviews(productName, req, res);

        Map params = new HashMap<>();
        params.put("reviews", parseReviews(reviewJson));
        params.put("product", productDataStore.find(id));
        params.putAll(cartController.showShoppingCart(req));
        return new ModelAndView(params, "product/product");
    }

    public static ArrayList parseReviews(String json) throws IOException{
        HashMap<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);
        ArrayList reviews = new ArrayList();
        for (Object element : result.values()){
            reviews.add(element);
        }
        return reviews;
    }
}
