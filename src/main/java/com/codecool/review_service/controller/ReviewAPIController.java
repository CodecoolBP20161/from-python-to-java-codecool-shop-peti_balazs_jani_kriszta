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
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public String findReviews(Request request, Response response) throws IOException, URISyntaxException {
        String productName = request.queryParams(PRODUCT_NAME_PARAM_KEY);
        return apiService.findReviews(productName);
    }

    public String status(Request request, Response response) {
        return "ok";
    }
}
