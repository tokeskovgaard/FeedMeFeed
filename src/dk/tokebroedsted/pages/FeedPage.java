package dk.tokebroedsted.pages;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 18-07-12
 * Time: 16:31
 * To change this template use File | Settings | File Templates.
 */
public class FeedPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.println("<html><head>");
        out.println("<script src=\"Feed/less-1.3.0.min.js\" type=\"text/javascript\"></script>");
        out.println("</head><body>");
        out.println("<script type=\"text/javascript\" language=\"javascript\" src=\"Feed/feed.nocache.js\"></script>");
        out.println("<div id=\"gwt_feed\"></div>");
        out.println("</body></html>");
    }
}
