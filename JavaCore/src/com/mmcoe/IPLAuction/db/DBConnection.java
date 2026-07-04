package com.mmcoe.IPLAuction.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.function.Supplier;

public class DBConnection {

    private static final String URL  = "jdbc:mysql://localhost:3306/iplauction";
    private static final String USER = "root";
    private static final String PASS = "Manu2715";

    private static DBConnection instance;

    private final Supplier<Connection> supplier = () -> {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            throw new RuntimeException("DB connection failed: " + e.getMessage(), e);
        }
    };

    private DBConnection() {}

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return supplier.get();
    }
}
