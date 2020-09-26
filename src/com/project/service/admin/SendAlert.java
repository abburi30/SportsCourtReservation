package com.project.service.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mysql.jdbc.PreparedStatement;
import com.project.Dao.DataSource.JDBCConnection;
import com.project.service.EmailService.EmailService;

public class SendAlert {

	public static String broadcastmsg(String msg) {
		// creating executor service for creating multithreading
		ExecutorService exservice = Executors.newFixedThreadPool(3);
		// Establish Database connection
		Connection conection = JDBCConnection.getDBConnection();
		String query;

		List<String> list = new ArrayList<String>();

		if (conection != null) {
			try {
				query = "SELECT emailId FROM user_slot_book";
				PreparedStatement preparedStmt = (PreparedStatement) conection.prepareStatement(query);

				ResultSet resultSet = preparedStmt.executeQuery();

				while (resultSet.next()) {
					list.add(resultSet.getString(1));
				}
				// email id's storing in a storing in array
				String[] unSortedList = list.toArray(new String[0]);

				// calling merge sort method to sort the all email id's
				String[] sortedList = mergeSort(unSortedList);
				
				//Iterating list of sorted emails.
				for (String emails : sortedList) {
					//Execute the runnable interface for creating and executong multiple threads
					exservice.submit(new Runnable() {
						@Override
						public void run() {
							//calling email services to send the notification
							EmailService.sendconfirmationlink(1, msg, emails, "broadcast");
						}
					});
				}
				//closing the executor service
				exservice.shutdown();

				return "success";

			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return "failure";

	}

	private static String[] mergeSort(String[] unSortedList) {
		//calling method for dividing and merge in sort order
		String[] sortedList = divide(unSortedList);
		
		return sortedList;
	}

	private static String[] divide(String[] emailList) {
		if (emailList.length >= 2) {
			int length = emailList.length;
			int mid = length / 2;
			String[] left = new String[mid];
			String[] right = new String[length - mid];

			for (int i = 0; i < left.length; i++) {
				left[i] = emailList[i];
			}

			for (int i = 0; i < right.length; i++) {
				right[i] = emailList[i + mid];
			}
			
			//divide the given array into left sub array
			divide(left);
			//dividing the given array into right sub array
			divide(right);
			//sort and merge all the left and right sub arrays.
			merge(emailList, left, right);
		}
		return emailList;
	}

	private static void merge(String[] emailList, String[] left, String[] right) {
		int lower = 0;
		int upper = 0;

		for (int i = 0; i < emailList.length; i++) {
			
			if (upper >= right.length || (lower < left.length && left[lower].compareToIgnoreCase(right[upper]) < 0)) {
				emailList[i] = left[lower];
				lower++;
			} else {
				emailList[i] = right[upper];
				upper++;
			}
		}

	}

}
