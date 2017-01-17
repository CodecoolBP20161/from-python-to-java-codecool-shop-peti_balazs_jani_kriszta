package com.codecool.shop.controller;

import com.codecool.review_service.controller.ReviewAPIController;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by csyk on 2017.01.17..
 */
public class ProductPageController {

    public static ModelAndView renderReview(Request req, Response res) throws IOException{
        String productName = req.params("productName");
        ReviewAPIController finderController = new ReviewAPIController();
        String reviewJson = finderController.findReviews(productName);
        Map params = new HashMap<>();
        params.put("reviews", parseReviews(reviewJson));
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
