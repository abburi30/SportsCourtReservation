package com.project.service.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.project.Dao.DataSource.JDBCConnection;
import com.project.pojo.BookDates;
import com.project.pojo.Date_Time;

public class CheckBookedDates {

	private static ArrayList datelist;
	private static String query;

	public static ArrayList checkDates(int court, String date_slot) {

		Connection conection = JDBCConnection.getDBConnection();

		BookDates bdates = new BookDates();
		bdates.setResstatus("reserved");

		if (conection != null) {

			try {

				query = "SELECT time_slot FROM user_slot_book where court_id='" + court + "' AND book_date='"
						+ date_slot + "' AND registration_status='" + bdates.getResstatus() + "'";

				PreparedStatement preparedStmt = (PreparedStatement) conection.prepareStatement(query);

				ResultSet resultSet = preparedStmt.executeQuery();

				datelist = new ArrayList();
				
				//iterating time slots and returning time slots 
				while (resultSet.next()) {

					datelist.add(resultSet.getTime("time_slot"));

				}
				
				return datelist;
				
			} catch (Exception e) {
				System.out.println(e);
			}finally {
				try {
					conection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("Failed");
		}
		return null;

	}
}
