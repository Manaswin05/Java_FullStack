package com.mmcoe.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CallableDemo {
	public static void main(String[] args) {
		try {
			Connection conn = JdbcFactory.getConnection();
			CallableStatement stmt = conn.prepareCall("{ ? = call get_name(?) }");
			stmt.registerOutParameter(1, Types.VARCHAR);
			stmt.setInt(2, 12);
			stmt.execute();
			System.out.println("Name: " + stmt.getString(1));
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}