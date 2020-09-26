package com.project.controller;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.project.Dao.DataSource.JDBCConnection;
import com.project.service.EmailService.EmailService;
import com.project.service.user.Register_slot;

/**
 * Servlet implementation class registerCourt
 */
@WebServlet("/RegisterCourt")
public class RegisterCourt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String courtid;
	private String book_date;
	private String book_time;
	private String first_name;
	private String last_name;
	private String email_id;
	private ArrayList booking_status;
	private String status;
	private int booking_id;
	private String reason = "booking";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterCourt() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Fetching some data from URL
		courtid = request.getParameter("courtid");
		book_date = request.getParameter("book_date");
		book_time = request.getParameter("book_time");
		first_name = request.getParameter("firstName");
		last_name = request.getParameter("lastName");
		email_id = request.getParameter("emailid");

		// calling this method to insert values into database for register court
		booking_status = Register_slot.registeredData(courtid, book_date, book_time, first_name, last_name, email_id);

		String status = (String) booking_status.get(0);
		int booking_id = (Integer) booking_status.get(1);
		String fn = (String) booking_status.get(2);
		String eid = (String) booking_status.get(3);

		response.addHeader("Access-Control-Allow-Origin", "*");
		// sending response in the format of JSON
		String json = new Gson().toJson(status);
		response.getWriter().append(json);

		// if status is hold sending the email link to confirm successful registration.
		if (status == "hold") {
			EmailService.sendconfirmationlink(booking_id, fn, eid, reason);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
