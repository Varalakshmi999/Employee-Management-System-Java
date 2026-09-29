import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");

        // direct ga /delete ani open cheste Day16Servlet ki pampu
        if (idParam == null || idParam.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/Day16Servlet");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "Varalakshmi@123");
            PreparedStatement ps = con.prepareStatement("DELETE FROM employees WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            con.close();

            // delete ayyaka malli table ki vellu
            resp.sendRedirect(req.getContextPath() + "/Day16Servlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}