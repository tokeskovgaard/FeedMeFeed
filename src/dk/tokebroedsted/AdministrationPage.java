package dk.tokebroedsted;

import dk.tokebroedsted.objects.UserCookie;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AdministrationPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        UserCookie cookie = new UserCookie(req, resp);
//        cookie.updateAndCheckSession("Administration");

        ServletOutputStream out = resp.getOutputStream();
        out.println("<html><head>");
        out.println("<link rel=\"stylesheet/less\" type=\"text/css\" href=\"Administration/Administration.less\">\n" +
                "<script src=\"Administration/less-1.3.0.min.js\" type=\"text/javascript\"></script>");
        out.println("</head><body>");
        out.println("Du ramte lige Administrationsiden");

        out.println("<script type=\"text/javascript\" language=\"javascript\" src=\"Administration/administration.nocache.js\"></script>");
        out.println("<div id=\"gwt_administration\"></div>");
        out.println("</body></html>");
    }
}
