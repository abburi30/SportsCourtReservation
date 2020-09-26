package com.project.Dao.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {

	public static Connection getDBConnection() {
		Connection con = null;
		try {
			
			// Defining the Driver calss name
			Class.forName("com.mysql.jdbc.Driver");
			// create the connection with database url using username and password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");

			return con;
			
		} catch (Exception e) {
			System.out.println(e);
		}

		return con;

	}

}
