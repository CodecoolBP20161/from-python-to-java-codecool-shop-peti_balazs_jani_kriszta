package com.codecool.moderator_service.controller;

import com.codecool.review_service.service.APIService;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by komlancz on 2017.01.18..
 */
public class ModeratorAPIController {

    public static final String PRODUCT_NAME_PARAM_KEY = "productName";

    private final APIService apiService;

    public ModeratorAPIController(){
        this.apiService = APIService.getInstance();
    }

    /**
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public String getAllApprovedReviewsOfProduct(String productName, Request req, Response res) throws IOException, URISyntaxException {
        return apiService.getAllApprovedReviewsOfProduct(productName, req, res);
    }

    public String status(Request request, Response response) {
        return "ok";
    }
}
