package com.styopik.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection conn;
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost:3306/lux";
	
	private static final String USER = "root";
	private static final String PASS = "root";
	
	public static synchronized Connection getConnection() {
		
//		Connection conn = null;
		
		if (conn == null) {
			try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
//				return conn;
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return conn;
		
//		return null;
	}

}
