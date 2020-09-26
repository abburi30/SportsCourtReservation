package com.project.service.user;

import com.project.Dao.DataSource.JDBCConnection;
import com.project.pojo.BookDates;
import com.project.pojo.BookSlot;
import com.project.pojo.BookingStatus;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.activation.DataSource;

import com.mysql.jdbc.PreparedStatement;

public class CheckStatus {

	private static String query;
	private static String update_query;
	private static int updatedrow;

	public static String checkBooikngStatus(int book_id) {

		// creating object for utilize pojo classes
		BookingStatus bstatus = new BookingStatus();
		bstatus.setNewStaus("reserved");
		bstatus.setStatus("null");

		// Establish data base connection
		Connection connection = JDBCConnection.getDBConnection();

		if (connection != null) {

			try {
				//query for fetching status for the booking id.
				query = "SELECT registration_status FROM `user_slot_book` WHERE booking_id='" + book_id + "'";
				bstatus.setQuery(query);
				//query for update the status.
				update_query = "UPDATE user_slot_book SET registration_status='" + bstatus.getNewStaus()
						+ "' WHERE booking_id='" + book_id + "'";
				bstatus.setUpdateQuery(update_query);
				
				PreparedStatement ps = (PreparedStatement) connection.prepareStatement(bstatus.getQuery());

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					bstatus.setStatus(rs.getString(1));
				}
				
				//check whether status is hold or not
				if (bstatus.getStatus().equals("hold")) {

					PreparedStatement ps1 = (PreparedStatement) connection.prepareStatement(bstatus.getUpdateQuery());
					updatedrow = ps1.executeUpdate();
					if (updatedrow == 1)
						return "registered successfully";
				} else {
					return "Already registered";
				}

			}

			catch (Exception e) {
				System.out.println(e);
			}
		} else {
			System.out.println("Failed");
		}
		return "";
	}
}
