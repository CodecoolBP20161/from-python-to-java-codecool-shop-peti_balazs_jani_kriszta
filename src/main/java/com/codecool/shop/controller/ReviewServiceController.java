package com.codecool.shop.controller;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class ReviewServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ReviewServiceController.class);
    private static final String SERVICE_URL = "http://localhost:60000";

    public Boolean isRunning() throws URISyntaxException, IOException {
        logger.info("Checking Service status");

        Boolean running = execute("/status").equalsIgnoreCase("ok");

        if(running){
            logger.info("Service is running");
        } else {
            logger.warn("Service is not running");
        }

        return running;
    }

    public static ArrayList<String> getReviews() throws URISyntaxException, IOException {
        logger.info("Listing reviews from this webshop.");
        ArrayList<String> reviews = new ArrayList<String>();
        JSONArray obj = new JSONArray(execute("/api/reviews"));
        for(int i = 0; i < obj.length(); i++){
            reviews.add(obj.getString(i));
        }
        return reviews;
    }

    public static ArrayList<String> getAllReview() throws URISyntaxException, IOException {
        logger.info("Listing reviews from every webshop.");
        ArrayList<String> reviews = new ArrayList<String>();
        JSONArray obj = new JSONArray(execute("/api/allReview"));
        for(int i = 0; i < obj.length(); i++){
            reviews.add(obj.getString(i));
        }
        return reviews;
    }

    /**
     * Executes the actual GET request against the given URI
     *
     * @param url - obj containing path and params.
     * @return
     * @throws IOException
     */
    private static String execute(String url) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL + url).build();
        return Request.Get(uri).execute().returnContent().asString();
    }
}
