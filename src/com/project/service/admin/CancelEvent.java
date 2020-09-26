package com.project.service.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.project.Dao.DataSource.JDBCConnection;
import com.project.service.EmailService.sendEmailCancellation;

public class CancelEvent {

	private static String status = "reserved";
	private static String setStatus = "cancelled";
	private static String query_status = null;
	private static String query;
	
	public static String cancelDateEvent(String cancel_date) {

		ArrayList<String> email_list = new ArrayList<String>();

		// Retrieving the database connection and establishing connection
		Connection connection = JDBCConnection.getDBConnection();

		if (connection != null) {
			try {
				query = "UPDATE user_slot_book SET registration_status= '" + setStatus + "' WHERE book_date='"
						+ cancel_date + "' ";
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(query);
				stmt.executeUpdate();

				query = "select booking_id,firstName,emailid from user_slot_book WHERE book_date='" + cancel_date + "'";
				PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
				ResultSet rs = statement.executeQuery();

				//Retrieving data from query and adding it into list
				while (rs.next()) {
					email_list.add(rs.getString(1));
					email_list.add(rs.getString(2));
					email_list.add(rs.getString(3));
				}
				
				System.out.println(email_list);
				sendEmailCancellation.sendEmailConfirmation(email_list);
				
				return "success";
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return "failure";

	}

}
