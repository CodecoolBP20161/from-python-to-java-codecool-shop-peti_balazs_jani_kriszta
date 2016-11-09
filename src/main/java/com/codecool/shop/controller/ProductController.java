package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    private static ProductDao productDataStore = ProductDaoMem.getInstance();
    private static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    private static SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

    public static ModelAndView renderProducts(Request req, Response res) {

        Map params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getAll());
        params.put("supplier", supplierDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderByCategory(Request req, Response res){
        int id = Integer.parseInt(req.params("id"));
        String type = req.params("type");

        Map params = new HashMap();
        params.put("categories", productCategoryDataStore.getAll());
        if (type.equals("category")){
            params.put("products", productDataStore.getBy(productCategoryDataStore.find(id)));
            params.put("title", productCategoryDataStore.find(id).getName());

        }else if(type.equals("supplier")){
            params.put("products", productDataStore.getBy(supplierDataStore.find(id)));
            params.put("title", supplierDataStore.find(id).getName());
        }
        params.put("supplier", supplierDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

}
