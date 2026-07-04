package com.mmcoe.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultDemo {

	public static void main(String[] args) {
		String sql = "select * from cust";
		try {
			Connection conn = JdbcFactory.getConnection();
			Statement stmt = conn.createStatement();
			//execution of DQL operation and getting result into resultSet
			ResultSet rs = stmt.executeQuery(sql);
//			while(rs.next){
//				System.out.println(rs.getString("cid") + ", " + rs.getString(2) + rs.getString(3));
			ResultSetMetaData meta = rs.getMetaData();
			System.out.println(meta.getColumnCount());
			while(rs.next()) {
				for(int i=1;i<meta.getColumnCount();i++) {
					System.out.println(rs.getString(i)+ ", ");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
