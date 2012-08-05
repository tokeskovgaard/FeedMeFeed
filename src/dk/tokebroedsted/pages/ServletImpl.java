package dk.tokebroedsted.pages;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class ServletImpl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();

        out.println("<html>");

        out.println("<head>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />");
        out.println("<script src=\"javascript/less-1.3.0.min.js\" type=\"text/javascript\"></script>");
        out.println("</head>");

        out.println("<body>");
        out.println("<div class=\"top-panel\">");
        {
            out.println("<img src=\"pics/customlists.png\"/>");
            out.println("<div class=\"tabs\">");
            out.println("<a href=\"" + FeedPage.url() + "\">Feeds</a>");
            out.println("<a href=\"" + FeedSetupPage.url() + "\">FeedSetup</a>");
            out.println("<a href=\"" + UserPage.url() + "\">UserManagement</a>");
            out.println("<a href=\"" + ServerStatus.url() + "\">Status</a>");
            out.println("</div>");
        }
        out.println("</div>");

        out.println("<div class=\"content\">");
        renderBody(req, resp);
        out.println("</div>");

        out.println("</body></html>");
    }

    protected abstract void renderBody(HttpServletRequest req, HttpServletResponse resp) throws IOException;

}
