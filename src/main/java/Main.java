import com.codecool.shop.controller.Controller;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.implementation.database.DBConnection;
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

        // Run populateData only once at the first, then comment out to prevent duplicating data in database
//        PopulateData.populateData();

        Controller controller = new Controller();
        controller.setState("DB");
        controller.doAct();

        // Routes
        get("/category/:id", ProductController::renderByFilter, tmp);
        get("/supplier/:id", ProductController::renderByFilter, tmp);
        get("/tocart/:id", (request, response) -> ProductController.saveToCart(request, response));
        get("/hello", (req, res) -> "Hello World");

        get("/", ProductController::renderProducts, tmp);

    }
}
