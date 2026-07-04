package com.mmcoe.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementDemo {

	public static void main(String[] args) {
		String sql = "insert into cust values (1,'Polo','Pune')";
		try {
			Connection conn = JdbcFactory.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Recorded sucessfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
