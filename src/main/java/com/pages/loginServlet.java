package com.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	
System.out.println("=== LoginServlet doPost executed ===");
		
		response.setContentType("text/html");

	    PrintWriter pw = response.getWriter();

	    String str1 = request.getParameter("name");
	    String str2 = request.getParameter("password");

	try {
		
		//upload driver
		System.out.println("Trying JDBC connection...");
		Class.forName("com.mysql.cj.jdbc.Driver");
		//connection
		Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_application","root","Anushka@18");
		System.out.println("Database connected successfully");
		
		
		
	//main logic 
		
		String sql= "SELECT * FROM USERS WHERE name=? AND password=?";
		PreparedStatement pts = con.prepareStatement(sql);
		pts.setString(1,str1);
		pts.setString(2,str2);
		
		ResultSet rs = pts.executeQuery();
		System.out.println("Query executed");
		
		if(rs.next())
		{
		    System.out.println("Login successful");
		    RequestDispatcher rd= request.getRequestDispatcher("dashboard.html");
			rd.include(request, response);
		}
		else
		{
			 RequestDispatcher rd= request.getRequestDispatcher("login.html");
				rd.include(request, response);
		    System.out.println("Invalid username or password");
		}
		
	}
	catch(Exception e) {
		pw.print(e);
	}

}
}
