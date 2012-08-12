package dk.tokebroedsted.pages;

import dk.tokebroedsted.hibernate.tables.User;
import dk.tokebroedsted.objects.UserCookie;

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
        User user = UserCookie.getLoggedInUser(req);


        out.println("<!DOCTYPE html>");
        out.println("<html>");
        printHead(out);
        out.println("<body>");
        out.println("<div class=\"page\">");
        printHeader(out, user);

        out.println("<section id=\"main\">");
        renderBody(req, resp);
        out.println("</section>");

        out.println("</body>");
        out.println("</html>");
    }

    private void printHeader(ServletOutputStream out, User user) throws IOException {
        out.println("<header>");
        out.println("<div id=\"title\">");
        out.println("<h1>Tokebroedsted.dk</h1>");
        out.println("</div>");

        String loginTitle = "Log på";
        String url = "Login";
        if (user != null) {
            loginTitle = "Velkommen " + user.getUsername() + "!";
            url = "Logout";
        }
        out.println("<div id=\"logindisplay\">");
        out.println("<a href=\"" + url + "\">" + loginTitle + "</a>");
        out.println("</div>");

        out.println("<nav>");
        out.println("<ul id=\"menu\">");
        out.println("<li><a href=\"Home\">Home</a></li>");
        out.println("<li><a href=\"Feed\">Feed</a></li>");
        out.println("<li><a href=\"FeedSetup\">FeedSetup</a></li>");
        out.println("<li><a href=\"ServerStatus\">ServerStatus</a></li>");
        out.println("</ul>");
        out.println("</nav>");
        out.println("</header>");
    }

    private void printHead(ServletOutputStream out) throws IOException {
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />");
        out.println("<script src=\"javascript/less-1.3.0.min.js\" type=\"text/javascript\"></script>");
        out.println("</head>");
    }

    protected abstract void renderBody(HttpServletRequest req, HttpServletResponse resp) throws IOException;

}
