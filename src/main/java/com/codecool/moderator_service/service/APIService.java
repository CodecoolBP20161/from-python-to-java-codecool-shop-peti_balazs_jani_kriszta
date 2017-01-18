package com.codecool.moderator_service.service;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class APIService {

    private static final Logger logger = LoggerFactory.getLogger(APIService.class);
    private static String port = "6543";
    private static final String API_URL = "http://localhost:" + port;
    private static final String SHOP_API_KEY = "";

    private static APIService INSTANCE;

    private APIService(){}

    public static APIService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new APIService();
        }
        return INSTANCE;
    }
    public String newReview(String productName, Request request, Response response) throws URISyntaxException, IOException {
        logger.info("New review: ");
        URIBuilder builder = new URIBuilder(API_URL + "/review/"+SHOP_API_KEY+"/"+productName);
        logger.info("new review url: "+builder);
        return execute(builder.build());
    }
    public String getAllApprovedReviewsOfProduct(String productName, Request request, Response response) throws URISyntaxException, IOException {
        logger.info("Get all review of product: "+productName);
        URIBuilder builder = new URIBuilder(API_URL + "/allReviewOfProduct/"+SHOP_API_KEY+"/"+productName);
        logger.info("get all review url: "+builder);
        return execute(builder.build());
    }
    /**
     * Executes the actual GET request against the given URI
     *
     * @param uri - obj containing path and params.
     * @return
     * @throws IOException
     */
    private String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }
}
