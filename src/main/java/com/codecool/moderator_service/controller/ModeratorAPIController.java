package com.codecool.moderator_service.controller;

import com.codecool.moderator_service.service.APIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by komlancz on 2017.01.18..
 */
public class ModeratorAPIController {
    private static final Logger logger = LoggerFactory.getLogger(ModeratorAPIController.class);

    private final APIService apiService;

    public ModeratorAPIController(){
        this.apiService = APIService.getInstance();
    }

    public String getAllApprovedReviewsOfProduct(String productName, Request req, Response res) throws IOException, URISyntaxException {
        logger.info("Start sending request for approved reviews...");
        return apiService.getAllApprovedReviewsOfProduct(productName, req, res);
    }

    public String saveReview(String productName, Request req, Response res)  throws IOException, URISyntaxException {
        return apiService.newReview(productName, req, res);
    }
}

