package org.emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginEmployeeServlet extends GenericServlet {
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/HTML");
		PrintWriter out =response.getWriter();
		
		String empid = request.getParameter("empid"); 
		String password = request.getParameter("password");
		  
		Connection con=null;
		PreparedStatement ps = null;
		String selectQuery = "SELECT Ename FROM Employee WHERE empid=? AND password=?";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			ps = con.prepareStatement(selectQuery);
			ps.setString(1,empid); 
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				out.println("<h1>Welcome, "+rs.getString(1)+"<p>Login successfully, enjoy browsing the site</></h1>");
			else
				out.println("<h1>Failed to login, Invalid id or password, <em>Please try later</em></h1>");
			 
		}catch(Exception e){
			out.println("<h1>ERROR: "+e.getMessage()+"</h1>");
			System.out.println(e);
		}
	}

}
