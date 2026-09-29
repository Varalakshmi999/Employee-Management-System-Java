import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Day16Servlet")
public class Day16Servlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "Varalakshmi@123");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM employees");

            out.println("<h2>Employee List - Day16</h2>");
            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Action</th></tr>");
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                out.println("<tr><td>" + id + "</td><td>" + name + "</td>");
                out.println("<td><a href='delete?id="+id+"'>Delete</a></td></tr>");
            }
            out.println("</table>");
            con.close();
        } catch(Exception e){
            out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}