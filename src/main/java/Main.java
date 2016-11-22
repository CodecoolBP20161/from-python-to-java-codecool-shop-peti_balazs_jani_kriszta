import com.codecool.shop.controller.ProductControllerMem;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.database.DBConnection;
import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.dao.implementation.memory.SupplierDaoMem;
import com.codecool.shop.model.PopulateDate;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.sql.SQLException;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        dbConnection.connect();

        ThymeleafTemplateEngine tmp = new ThymeleafTemplateEngine();
        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        PopulateDate.populateData();


        // Routes
        get("/category/:id", ProductControllerMem::renderByFilter, tmp);
        get("/supplier/:id", ProductControllerMem::renderByFilter, tmp);
        get("/tocart/:id", (request, response) -> ProductControllerMem.saveToCart(request, response));
        get("/hello", (req, res) -> "Hello World");

        get("/", ProductControllerMem::renderProducts, tmp);

    }
}
