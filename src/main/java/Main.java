import com.codecool.shop.controller.CartController;
import com.codecool.shop.controller.Controller;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.implementation.database.DBConnection;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.sql.SQLException;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        // Connection to PostgreSQL Database
        DBConnection dbConnection = new DBConnection();
        dbConnection.connect();

        // Instantiate template engine
        ThymeleafTemplateEngine tmp = new ThymeleafTemplateEngine();

        // Default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // Declare the value for the Controller's state variable
        // Choose between "DB" or "MEM"
        String state = "DB";

        // IF RUN IN DB STATE, RUN POPULATEDATA ONLY ONCE, AT THE FIRST TIME WHEN YOU ARE RUNNING MAIN
        // so there won't be duplicates of records
//         PopulateData.populateData(state);

        // Set state
        Controller.setState(state);
        Controller.doAct();

        // Routes
        get("/category/:id", ProductController::renderByFilter, tmp);
        get("/supplier/:id", ProductController::renderByFilter, tmp);
        get("/tocart/:id", (request, response) -> CartController.saveToCart(request, response));
        get("/hello", (req, res) -> "Hello World");

        get("/", ProductController::renderProducts, tmp);

    }
}
