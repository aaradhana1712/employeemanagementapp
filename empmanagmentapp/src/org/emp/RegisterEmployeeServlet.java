package org.emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RegisterEmployeeServlet extends GenericServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/HTML");
		PrintWriter out =response.getWriter();
		
		String ename = request.getParameter("ename");
		String salary = request.getParameter("salary");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		Connection con=null;
		PreparedStatement ps = null;
		String insertQuery = "INSERT INTO Employee VALUES('E'||EmpSeq.NEXTVAL,?,?,?,?)";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			ps = con.prepareStatement(insertQuery);
			ps.setString(1,ename);
			ps.setString(2,salary);
			ps.setString(3, password);
			ps.setString(4, email);
			if(ps.executeUpdate()==1)
				out.println("<h1>SUCCESS</h1>");
			else
				out.println("<h1>FAILURE</h1>");
			 
		}catch(Exception e){
			out.println("<h1>ERROR</h1>");
			System.out.println(e);
		}
	}
}
