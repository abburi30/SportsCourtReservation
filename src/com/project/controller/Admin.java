package com.project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.project.service.admin.UserAuthentication;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Retrieving username and password from URL
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		//calling userAuthentication method for validate username and password
		String result = UserAuthentication.login(username, password);
		
		if (result == "success") {
			
			HttpSession session = request.getSession();
			session.setAttribute("name", username);
			String jsonresult = new Gson().toJson(result);
			response.getWriter().append("status:" + jsonresult);
			
		} else {
			String jsonresult = new Gson().toJson(result);
			response.getWriter().append("status:" + jsonresult);
		}
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
