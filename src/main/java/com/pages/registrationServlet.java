package com.pages;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class registrationServlet
 */
@WebServlet("/register")
public class registrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    String balance = request.getParameter("balance");


	    try {
	        // Load Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // Create Connection
	        Connection con = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/bank_application",
	            "root",
	            "Anushka@18"
	        );

	        // SQL Query
	        String sql = "INSERT INTO users(name, email, password) VALUES(?,?,?,?)";

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setString(1, name);
	        ps.setString(2, email);
	        ps.setString(3, password);
	        ps.setString(4, balance);


	        int i = ps.executeUpdate();

	        if(i > 0) {
	            response.getWriter().println("Registration Successful");
	        }
	        else {
	            response.getWriter().println("Registration Failed");
	        }

	        con.close();

	    } 
	    catch(Exception e) {
	        e.printStackTrace();
	    }
	}
}