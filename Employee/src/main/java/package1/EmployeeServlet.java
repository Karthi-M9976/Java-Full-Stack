package package1;

import jakarta.servlet.ServletException;
import java.sql.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * 
     *
     */
	
	
	private  static final String url="jdbc:mysql://localhost:3306/mydb";
	private  static final String user="root";
	private  static final String pass="Admin@123";
	
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		response.setContentType("text/html");
		
		
		

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,pass);

            String sql = "SELECT * FROM employee";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            out.println("<html><body><h2>Employee Details</h2><table border='1'>");
            out.println("<tr><th>Emp Id</th><th>Emp Name</th><th>Emp Email</th><th>Emp Dept</th></tr>");

            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td>" +
                            "<td>" + rs.getString("name") + "</td>" +
                            "<td>" + rs.getString("email") + "</td>" +
                            "<td>" + rs.getString("dept") + "</td></tr>");
            }

            out.println("</table></body></html>");

                
            

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }

		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//doGet(request, response);
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String mess=request.getParameter("mess");
        //String mess1 = request.getParameter("mess1");
		if ("put".equalsIgnoreCase(mess)) {
            doPut(request, response);
            return;
        } else if ("delete".equalsIgnoreCase(mess)) {
            doDelete(request, response);
            return;
        }
		
		
	try {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 Connection conn = DriverManager.getConnection(url,user,pass);
	      
		 String query="insert into employee(id,name,email,dept) values(?,?,?,?)";
		 PreparedStatement ps=conn.prepareStatement(query);
		 ps.setInt(1, Integer.parseInt(request.getParameter("id")));
         ps.setString(2, request.getParameter("name"));
         ps.setString(3, request.getParameter("email"));
         ps.setString(4, request.getParameter("dept"));
         int rowsInserted = ps.executeUpdate();

         if (rowsInserted > 0) {
             out.println("<p>Inserted successfully!</p>");
         } else {
             out.println("<p>Insertion failed!</p>");
         }

         out.println("<br><a href='index.jsp'>Go Back</a>");
         ps.close();
         conn.close();
	
		
	}
	catch(Exception e) {
		System.out.println(e);
		
	}
	
	
	}
	 protected void doPut(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        int id = Integer.parseInt(request.getParameter("id"));
	        String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String dept=request.getParameter("dept");

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn = DriverManager.getConnection(url,user,pass);

	            String sql = "UPDATE employee SET name = ?, email = ? , dept=? WHERE id = ?";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, name);
	            ps.setString(2, email);
	            ps.setString(3, dept);
	            
	            ps.setInt(4, id);

	            int rowsUpdated = ps.executeUpdate();

	            if (rowsUpdated > 0) {
	                out.println("<p>User with ID " + id + " updated successfully.</p>");
	            } else {
	                out.println("<p>No user found with ID " + id + "</p>");
	            }

	            ps.close();
	            conn.close();
	        } catch (Exception e) {
	            out.println("<p>Error: " + e.getMessage() + "</p>");
	        }

	        out.println("<br><a href='index.jsp'>Go Back</a>");
	    }

	    
	    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        int id = Integer.parseInt(request.getParameter("id"));

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn = DriverManager.getConnection(url, user, pass);

	            String sql = "DELETE FROM employee WHERE id = ?";
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, id);

	            int rowsDeleted = pstmt.executeUpdate();

	            if (rowsDeleted > 0) {
	                out.println("<p>User with ID " + id + " deleted successfully.</p>");
	            } else {
	                out.println("<p>No user found with ID " + id + "</p>");
	            }
	            
	            doGet(request,response);

	            pstmt.close();
	            conn.close();
	        } catch (Exception e) {
	            out.println("<p>Error: " + e.getMessage() + "</p>");
	        }

	        out.println("<br><a href='index.jsp'>Go Back</a>");
	    }

}
