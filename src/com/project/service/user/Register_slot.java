package com.project.service.user;

import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.PreparedStatement;
import com.project.Dao.DataSource.JDBCConnection;
import com.project.pojo.BookSlot;

public class Register_slot {
	
	private static String msg;
	private static String status;
	private static ArrayList list;

	public static ArrayList registeredData(String courtid, String book_date, String book_time, String firstName,
			String lastName, String emailid) {

		BookSlot bslot = new BookSlot();

		list = new ArrayList();
		bslot.setMsg("null");
		bslot.setStatus("hold");
		
		Connection connection = JDBCConnection.getDBConnection();
		
		if (connection != null) {
			try {
				
				//query for insert the user registration form
				String query = "INSERT INTO `user_slot_book`(`booking_id`, `court_id`, `book_date`, `time_slot`, "
						+ "`firstName`, `lastName`, `emailid`,`registration_status`) VALUES (?,?,?,?,?,?,?,?)";

				PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
				
				// query to fetch the existing booking id
				String id = "SELECT MAX(booking_id) FROM `user_slot_book`";
				PreparedStatement ps = (PreparedStatement) connection.prepareStatement(id);
				ResultSet rs = ps.executeQuery();
				int max = 0;
				if (rs.next()) {
					max = rs.getInt(1);
				}
				//Incrementing the booking id by value 1
				statement.setInt(1, ++max);
				
				rs.close();
				
				statement.setString(2, courtid);
				statement.setString(3, book_date);
				statement.setString(4, book_time);
				statement.setString(5, firstName);
				statement.setString(6, lastName);
				statement.setString(7, emailid);
				statement.setString(8, bslot.getStatus());

				int i = statement.executeUpdate();
				
				//storing list values and send it controller 
				if (i == 1) {
					
					list.add(bslot.getStatus());
					list.add(max);
					list.add(firstName);
					list.add(emailid);

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
		return list;

	}

}
