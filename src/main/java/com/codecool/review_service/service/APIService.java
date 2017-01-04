package com.codecool.review_service.service;

import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class APIService {

    private static final Logger logger = LoggerFactory.getLogger(APIService.class);
    private static final String API_URL = "localhost:8888/";
    private static final String APIKey = "6bZD4zuR0Qmsh13bz0e6djc4jk1Op1ZAPXIjsnGyaTSNRONHQm";

    private static APIService INSTANCE;

    private APIService(){}

    public static APIService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new APIService();
        }
        return INSTANCE;
    }

    /**
     * Gets reviews from a specific product from this webshop
     *
     * @return - JSON received from the API as it is.
     * @throws IOException
     * @throws URISyntaxException
     */
    public String reviews() throws IOException, URISyntaxException {
        logger.info("Getting reviews.");
        URIBuilder builder = new URIBuilder(API_URL + "/reviewFromClient");
        builder.addParameter("APIKey", APIKey);
        logger.info("My builder looks like: " + builder);
        return execute(builder.build());
    }

    /**
     * Gets reviews from a specific product from this webshop
     *
     * @param productName - product name or serial  number.
     * @return - JSON received from the API as it is.
     * @throws IOException
     * @throws URISyntaxException
     */
    public String allReview(String productName) throws IOException, URISyntaxException {
        logger.info("Getting reviews.");
        URIBuilder builder = new URIBuilder(API_URL + "/reviewFromClient");
        builder.addParameter("APIKey", APIKey);
        builder.addParameter("productName", productName);
        logger.info("My builder looks like: " + builder);
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
