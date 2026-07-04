package com.mmcoe.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDemo {

	public static void main(String[] args) {
		String sql1 = "insert into cust values (15,'Nik','Noida')";
		String sql2 = "update cust set city='Madurai' where cust_id=11";
		String sql3 = "delete from cust where cust_id=14";
		Connection conn = null;
		try {
			conn = JdbcFactory.getConnection();
			conn.setAutoCommit(false); //turning off auto commit
			Statement stmt = conn.createStatement();
			//Adding all 3 queries as a batch
			stmt.addBatch(sql1);
			stmt.addBatch(sql2);
			stmt.addBatch(sql3);
			//Executing Batch of 3 DML queries 
			stmt.executeBatch();
			//Everything goes fine then compiling the transaction
			conn.commit();
			System.out.println("Transaction completed");
		} catch (SQLException e) {
			System.out.println("Transaction failed");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
