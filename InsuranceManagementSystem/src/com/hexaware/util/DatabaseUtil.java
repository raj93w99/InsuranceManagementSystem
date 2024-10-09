package com.hexaware.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseUtil {

    public static String getPropertyString(String propertyFileName) {
        Properties properties = new Properties();
        try (InputStream input = DatabaseUtil.class.getClassLoader().getResourceAsStream(propertyFileName)) {
            if (input == null) {
                System.err.println("Sorry, unable to find " + propertyFileName);
                return null; // Indicate failure to find the file
            }
            properties.load(input);
            String hostname = properties.getProperty("localhost");
            String dbname = properties.getProperty("shopsmart");
            String username = properties.getProperty("root");
            String password = properties.getProperty("W7301@jqir");
            String port = properties.getProperty("3306");

            // Constructing the connection string
            return String.format("jdbc:mysql://%s:%s/%s", hostname, port, dbname);
        } catch (IOException ex) {
            System.err.println("Error reading properties file: " + ex.getMessage());
            return null;
        }
    }
}

