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
    private static final String SHOP_API_KEY = "4c544040-43e2-478f-9239-c35f5601579f";

    private static APIService INSTANCE;

    private APIService(){}

    public static APIService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new APIService();
        }
        return INSTANCE;
    }

    public String newReview(String productName, spark.Request request, Response response) throws URISyntaxException, IOException {
        logger.info("New review: ");
        URIBuilder builder = new URIBuilder(API_URL + "/review");
        builder.addParameter("APIKey", SHOP_API_KEY);
        builder.addParameter("productName", productName.replace(" ", "+"));
//        builder.addParameter("username", request.queryParams("name"));
        builder.addParameter("ratings", request.queryParams("ratings"));
        logger.info("new review URI: "+builder);
        String comment = request.queryParams("review");
        return executeNew(builder.build(), comment);
    }

    private String executeNew(URI uri, String comment) throws IOException {
        logger.info("Execute request. URI: " + uri);
        return Post(uri)
                .bodyString(comment, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
    }

    public String getAllApprovedReviewsOfProduct(String productName, spark.Request request, Response response) throws URISyntaxException, IOException {
        logger.info("Get all reviews of product: " + productName);
        URIBuilder builder = new URIBuilder(API_URL + "/allReviewOfProduct");
        builder.addParameter("APIKey", SHOP_API_KEY);
        builder.addParameter("productName", productName);
        return executeAll(builder.build());
    }

    private String executeAll(URI uri) throws IOException {
        logger.info("Execute request at URI: " + uri);
        return Get(uri)
                .execute()
                .returnContent()
                .asString();
    }


}
