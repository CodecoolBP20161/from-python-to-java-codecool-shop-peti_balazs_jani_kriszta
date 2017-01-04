package com.codecool.review_service;

import com.codecool.review_service.controller.ReviewAPIController;
import com.codecool.review_service.service.APIService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;

public class ReviewService {
    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    private ReviewAPIController controller;

    public static void main(String[] args) {
        logger.debug("Starting " + ReviewService.class.getName() + "...");

        setup(args);

        ReviewService application = new ReviewService();

        application.controller = new ReviewAPIController(APIService.getInstance());

        // --- MAPPINGS ---
        get("/status", application.controller::status);
        get("/api/reviews", application.controller::reviews);
        get("/api/allReview", application.controller::allReview);

        // --- EXCEPTION HANDLING ---
        exception(UnirestException.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("URI building error, maybe wrong format? : %s", exception.getMessage()));
            logger.error("Error while processing request", exception);
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("Unexpected error occurred: %s", exception.getMessage()));
            logger.error("Error while processing request", exception);
        });
    }

    private static void setup(String[] args){
        if(args == null || args.length == 0){
            logger.error("Port must be given as the first argument.");
            System.exit(-1);
        }

        try {
            port(Integer.parseInt(args[0]));
        } catch (NumberFormatException e){
            logger.error("Invalid port given '{}', it should be number.", args[0]);
            System.exit(-1);
        }
    }

}
