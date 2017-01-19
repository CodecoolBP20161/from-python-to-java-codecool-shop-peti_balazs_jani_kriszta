package com.codecool.moderator_service.service;

import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.apache.http.client.fluent.Request.Get;
import static org.apache.http.client.fluent.Request.Post;

public class APIService {

    private static final Logger logger = LoggerFactory.getLogger(APIService.class);
    private static String port = "61000";
    private static final String API_URL = "http://localhost:" + port;
    private static final String SHOP_API_KEY = "7b2b7341-c97f-4680-8220-d056856aa7d0";

    private static APIService INSTANCE;

    private APIService(){}

    public static APIService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new APIService();
        }
        return INSTANCE;
    }

    public String newReview(String productName, spark.Request request, Response response) throws URISyntaxException, IOException {
        logger.info("Sending new review to Service... ");
        String ratings = request.queryParams("ratings");
        logger.info("Ratings: " + ratings);
        String comment = request.queryParams("comment");
        URIBuilder builder = new URIBuilder(API_URL + "/review/" + SHOP_API_KEY + "/" + productName.replace(" ", "") + "/" + ratings);

        logger.info("URI for saving new review: " + builder);
        return executeNew(builder.build(), comment);
    }

    private String executeNew(URI uri, String comment) throws IOException {
        return Post(uri)
                .bodyString(comment, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
    }

    public String getAllApprovedReviewsOfProduct(String productName, spark.Request request, Response response) throws URISyntaxException, IOException {
        logger.info("Get all reviews of product: " + productName);
        URIBuilder builder = new URIBuilder(API_URL + "/allReviewOfProduct/" + SHOP_API_KEY + "/" + productName.replace(" ", ""));
        logger.info("URI for getting all approved review of "+ productName + ": " + builder);
        return executeAll(builder.build());
    }

    private String executeAll(URI uri) throws IOException {
        return Get(uri)
                .execute()
                .returnContent()
                .asString();
    }


}
