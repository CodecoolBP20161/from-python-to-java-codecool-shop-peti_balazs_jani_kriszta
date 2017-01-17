package com.codecool.review_service.controller;

import com.codecool.review_service.service.APIService;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;

public class ReviewAPIController {
    public static final String PRODUCT_NAME_PARAM_KEY = "productName";

    private final APIService apiService;

    public ReviewAPIController(){
        this.apiService = APIService.getInstance();
    }

    /**
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public String findReviews(String productName, Request req, Response res) throws IOException, URISyntaxException {
        return apiService.findReviews(productName, req, res);
    }

    public String status(Request request, Response response) {
        return "ok";
    }
}
