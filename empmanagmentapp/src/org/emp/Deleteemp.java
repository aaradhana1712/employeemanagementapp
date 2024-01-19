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

public class Deleteemp extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res)
			throws IOException {
		res.setContentType("text/HTML");
		PrintWriter out = res.getWriter();
		String empid = req.getParameter("empid");
		Connection con=null;
		PreparedStatement ps = null;
		String selectQuery = "DELETE FROM EMPLOYEE Where empid=?";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			ps = con.prepareStatement(selectQuery);
			ps.setString(1, empid);
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