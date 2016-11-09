import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        ThymeleafTemplateEngine tmp = new ThymeleafTemplateEngine();
        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        populateData();

        get("/", ProductController::renderProducts, tmp);
        get("/:type/:id", ProductController::renderByCategory, tmp);

        get("/hello", (req, res) -> "Hello World");

    }

    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "We make the tools. You make them do.");
        supplierDataStore.add(lenovo);
        Supplier sony = new Supplier("Sony", "Make.believe");
        supplierDataStore.add(sony);
        Supplier canon = new Supplier("Canon", "Delighting You Always");
        supplierDataStore.add(canon);
        Supplier rohan = new Supplier("Rohan", "Out of doubt, out of dark");
        supplierDataStore.add(rohan);
        Supplier samsung = new Supplier("Samsung", "Sensing the difference");
        supplierDataStore.add(samsung);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory camera = new ProductCategory("Camera", "Hardware", "Digital cameras");
        productCategoryDataStore.add(camera);
        ProductCategory smartphone = new ProductCategory("Smartphone", "Hardware", "Smartphones with touchscreen");
        productCategoryDataStore.add(smartphone);
        ProductCategory horse = new ProductCategory("Horse", "Hardware", "Riders of Rohan! What news from the Mark?");
        productCategoryDataStore.add(horse);
        ProductCategory tv = new ProductCategory("TV", "Hardware", "Smart tvs");
        productCategoryDataStore.add(tv);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Przewalski 2.0", 1500, "USD", "A rare and endangered subspecies of wild horse (Equus ferus) native to the steppes of central Asia", horse, rohan));
        productDataStore.add(new Product("Samsung Note7", 450, "USD", "Samsung's fiercest smartphone so far. Designed with that intrepid spirit in mind", smartphone, samsung));
        productDataStore.add(new Product("Sony LED TV", 250, "USD", "Lose yourself in ultra contrast. It will take your breath away.", tv, sony));
        productDataStore.add(new Product("Amazon Kindle", 79.9f, "USD", "High Resolution Display with Next-Gen Built-in Light. With WiFi", tablet, amazon));
        productDataStore.add(new Product("Pampa", 900, "USD", "The Pampa Horse is a horse breed that combines the characteristics of Brazilian Horses.", horse, rohan));
        productDataStore.add(new Product("Sony Z3", 350, "USD", "Don’t settle for good. Demand great. Do more than you thought possible", smartphone, sony));
        productDataStore.add(new Product("Przewalski 1.0", 15, "USD", "A rare and endangered subspecies of wild horse (Equus ferus). NOW ON SALE", horse, rohan));
        productDataStore.add(new Product("Lenovo Yoga", 450, "USD", "Think you know everything a tablet can do? Think again.", tablet, lenovo));
    }


}
