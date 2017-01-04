package com.codecool.shop.review_service.controller;

import com.codecool.shop.review_service.service.APIService;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;

public class ReviewAPIController {
    public static final String PRODUCT_NAME_PARAM_KEY = "productName";

    private final APIService apiService;

    /**
     * @param apiService
     */
    public ReviewAPIController(APIService apiService){
        this.apiService = apiService;
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public String allReview(Request request, Response response) throws IOException, URISyntaxException {
        String productName = request.queryParams(PRODUCT_NAME_PARAM_KEY);
        return apiService.allReview(productName);
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public String reviews(Request request, Response response) throws IOException, URISyntaxException {
        return apiService.reviews();
    }

    public String status(Request request, Response response) {
        return "ok";
    }
}
