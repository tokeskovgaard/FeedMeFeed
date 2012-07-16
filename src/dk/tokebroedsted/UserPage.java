package dk.tokebroedsted;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.println("<html><body>");
        out.println("Du ramte lige Brugersiden");

        out.println("<script type=\"text/javascript\" language=\"javascript\" src=\"User/user.nocache.js\"></script>");
        out.println("<div id=\"gwt\"></div>");
        out.println("</body></html>");
    }
}
