package com.hexaware.util;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String connectionString = DatabaseUtil.getPropertyString("db.properties");
                if (connectionString == null) {
                    System.err.println("Connection string is null. Check db.properties.");
                    return null;
                }
                String username =  DatabaseUtil.getPropertyString("username"); 
                String password =  DatabaseUtil.getPropertyString("password"); 
                connection = DriverManager.getConnection(connectionString, username, password);
            } catch (SQLException e) {
                System.err.println("Database connection error: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return connection;
    }
}
