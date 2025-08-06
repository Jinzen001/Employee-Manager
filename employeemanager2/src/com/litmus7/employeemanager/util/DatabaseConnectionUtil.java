package com.litmus7.employeemanager.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionUtil {
    private static String url;
    private static String user;
    private static String password;

    static {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/dbconfig.txt")) {
            props.load(fis);
            url = props.getProperty("url");
            user = props.getProperty("user");
            password = props.getProperty("password");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
