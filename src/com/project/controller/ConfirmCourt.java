package com.project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.project.service.user.CheckStatus;

/**
 * Servlet implementation class ConfirmCourt
 */
@WebServlet("/ConfirmCourt")
public class ConfirmCourt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int book_id;
	private static String message;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmCourt() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// fetching booking id from URL.
		book_id = Integer.parseInt(request.getParameter("booking_id"));
		// checking booking id status
		message = CheckStatus.checkBooikngStatus(book_id);

		//String json = new Gson().toJson(message);
		//response.getWriter().append(json);
		
		
		PrintWriter writer = response.getWriter();
		
		String htmlRespone = "<html>";
		htmlRespone += "<center><h1> Successfully Registred! </h1></center>";
		htmlRespone += "</html>";
		 
		writer.println(htmlRespone);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
