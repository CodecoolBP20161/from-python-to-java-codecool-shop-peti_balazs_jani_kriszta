package com.codecool.shop.dao.implementation.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by krisztinabaranyai on 22/11/2016.
 */
public class DBConnection {

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/codecoolshop",
                "postgres",
                "postgres");
    }

    public void executeQuery(String query) {
        try (Connection connection = connect();
             Statement statement =  connection.createStatement()
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
