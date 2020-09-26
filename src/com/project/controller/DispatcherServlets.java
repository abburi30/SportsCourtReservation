package com.project.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.project.service.user.CheckBookedDates;

/**
 * Servlet implementation class DispatcherServlets
 */
@WebServlet("/DispatcherServlets")
public class DispatcherServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String court_id;
	private static String date_slot;
	private static ArrayList booking_list;
	private static int court_number;

	/**
	 * Default constructor.
	 */
	public DispatcherServlets() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// fetching court id and date from URL
		String court_id = request.getParameter("court");
		String date_slot = request.getParameter("date");

		court_number = Integer.parseInt(court_id);

		ArrayList booking_list = CheckBookedDates.checkDates(court_number, date_slot);

		String json = new Gson().toJson(booking_list);
		response.getWriter().append(json);

		// System.out.println(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // TODO Auto-generated
	 * method stub doGet(request, response); }
	 */

}
