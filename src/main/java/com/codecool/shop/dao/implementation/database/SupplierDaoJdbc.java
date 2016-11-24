package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class SupplierDaoJdbc implements SupplierDao {
    private DBConnection dbConnection = new DBConnection();
    private static SupplierDaoJdbc instance = null;

    private SupplierDaoJdbc() {
    }

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier){
        String query = "INSERT INTO suppliers (name, description)" +
                       "VALUES ('" + supplier.getName() + "', '" + supplier.getDescription() + "');";
        dbConnection.executeQuery(query);
    }


    @Override
    public Supplier find(int id) {

         String query = " SELECT *" +
                       " FROM suppliers " +
                       " WHERE id = '" + id +"';";

        try (Connection connection = dbConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()){
                Supplier supplier = new Supplier(resultSet.getString("name"),
                                                 resultSet.getString("description"));
                supplier.setId(resultSet.getInt("id"));
                return supplier;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void remove(int id) {
        String query = "DELETE FROM suppliers WHERE id = " + id + ";";
        dbConnection.executeQuery(query);

    }


    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers = new ArrayList<>();
        String query = "SELECT * FROM suppliers;";

        try {Connection connection = dbConnection.connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Supplier supplier = new Supplier(rs.getString("name"),rs.getString("description"));
                supplier.setId(rs.getInt("id"));
                suppliers.add(supplier);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return suppliers;
    }
}
