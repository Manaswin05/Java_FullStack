package com.mmcoe.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrepareDemo {

    public static void main(String[] args) {
        String sql = "insert into cust values (?, ?, ?)";
        try {
        	Connection conn = JdbcFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(args[0]));
            stmt.setString(2, args[1]);
            stmt.setString(3, args[2]);
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Customer ID must be an integer.");
        }
    }
}