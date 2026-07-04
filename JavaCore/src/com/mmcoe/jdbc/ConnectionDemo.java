package com.mmcoe.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class ConnectionDemo {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mmcoe";
		try {
			DriverManager.registerDriver(new Driver());
			Connection conn = DriverManager.getConnection(url,"root","Manu2715");
			System.out.println("connection Successful");
			DatabaseMetaData meta = conn.getMetaData();
			System.out.println("DB name : "+ meta.getDatabaseProductName());
			System.out.println("DB version : "+ meta.getDatabaseProductVersion());
			System.out.println("Driver's name : "+ meta.getDriverName());
			System.out.println("Driver's Version : " + meta.getDriverVersion());
		}catch(SQLException e) {
			System.out.println("Connection failed due to ....");
			e.printStackTrace();
		}
	}
}