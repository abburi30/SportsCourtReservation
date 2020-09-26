package com.project.service.admin;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.project.Dao.DataSource.JDBCConnection;

public class UserAuthentication {

	private static String query;
	private static String checkPassword = "null";
	
	public static String login(String username, String password) {
		
			
		Connection connection = JDBCConnection.getDBConnection();
		
		if (connection != null) {

			try {
				
				//using the MD5 Message digest algorithm.
				MessageDigest messagedigest = MessageDigest.getInstance("MD5");
				messagedigest.update(password.getBytes());
				byte[] bytes = messagedigest.digest();
				StringBuilder sbuilder = new StringBuilder();
				
				for (int i = 0; i < bytes.length; i++) {
					sbuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
				}

				String generatedPassword = sbuilder.toString();
				//query for selecting password for user.
				query = "select password from user_login where username='" + username + "' ";

				PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
				ResultSet rs = statement.executeQuery();
				if (rs.next()) {
					checkPassword = rs.getString(1);
				}
				//checking the password matches to user sent password.
				if (generatedPassword.equals(checkPassword)) {
					return "success";
				} else {
					return "failure";
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;

	}
}
