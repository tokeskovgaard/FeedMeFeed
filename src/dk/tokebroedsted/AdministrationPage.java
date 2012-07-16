package dk.tokebroedsted;

import javax.servlet.*;
import  javax.servlet.http.*;
import java.io.IOException;

public class AdministrationPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.println("Du ramte lige Administrationsiden");
    }
}
