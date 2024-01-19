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

public class Selectemp extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res)
			throws IOException {
		res.setContentType("text/HTML");
		PrintWriter out = res.getWriter();
		String empid = req.getParameter("empid");
		String pass = req.getParameter("password");
		Connection con=null;
		PreparedStatement ps = null;
		String selectQuery = "SELECT empid,ename,salary,password,email FROM EMPLOYEE Where empid=? AND password =?";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			ps = con.prepareStatement(selectQuery);
			ps.setString(1, empid);
			ps.setString(2, pass);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				out.println("<h1>Welcome, "+rs.getString(1)+"<p>Login successfully,Please Check Your Details!</p></h1>");
//				out.println("<h1>Emp ID: "+rs.getString(0)+"</h1>");
//				out.println("<h1>Emp Name: "+rs.getString(1)+"</h1>");
//				out.println("<h1>Salary: "+rs.getString(2)+"</h1>");
//				out.println("<h1>Password: "+rs.getString(3)+"</h1>");
//				out.println("<h1>Email: "+rs.getString(4)+"</h1>");
                
                out.println(rs.getString(1)+"<br>");
                out.println(rs.getString(2)+"<br>");
                out.println(rs.getString(3)+"<br>");
                out.println(rs.getString(4)+"<br>");
                out.println(rs.getString(5)+"<br>");

			}else
				out.println("<h1>Failed to login, Invalid id or password, <em>Please try later</em></h1>");
			 
		}catch(Exception e){
			out.println("<h1>ERROR</h1>");
			System.out.println(e);
		}
//		try {
//			req.getRequestDispatcher("Home.html").include(req,res);
//		} catch (ServletException e) {
//
//			e.printStackTrace();
//		}
		
	}
}