package com.project.service.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.project.Dao.DataSource.JDBCConnection;
import com.project.pojo.BookingCancel;

public class CancelBookingId {
	private static String query_status = null;
	private static String query;
	private static int number = 0;

	public static String cancelById(int book_id) {

		BookingCancel bookingcancel = new BookingCancel();
		bookingcancel.setStatus("reserved");
		bookingcancel.setNewStatus("cancelled");

		// Establishing connection
		Connection connection = JDBCConnection.getDBConnection();

		if (connection != null) {
			try {

				query = "select registration_status from user_slot_book where booking_id= '" + book_id + "' ";
				bookingcancel.setQuery(query);
				PreparedStatement statement = (PreparedStatement) connection.prepareStatement(bookingcancel.getQuery());
				
				// Executing query to get the status of reservation to corresponding booking id.
				ResultSet rs = statement.executeQuery();
				
				if (rs.next()) {
					query_status = rs.getString(1);
				}
				
				// checking the existing booking id has confirmed the reservation or not
				if (query_status.equals(bookingcancel.getStatus())) {
					query = "UPDATE user_slot_book SET registration_status= '" + bookingcancel.getNewStatus()
							+ "' WHERE booking_id='" + book_id + "' ";
					PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(query);
					number = stmt.executeUpdate();
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
		return "failure";

	}

}
