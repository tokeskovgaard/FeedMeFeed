package dk.tokebroedsted;

import dk.tokebroedsted.objects.DatabaseHandler;
import dk.tokebroedsted.objects.User;
import dk.tokebroedsted.objects.UserCookie;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: mads
 * Date: 7/16/12
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Login extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();

        checkOrSetSessionID(req, resp);

        out.println("<html><head>");
        out.println("<link rel=\"stylesheet/less\" type=\"text/css\" href=\"Administration/Administration.less\">\n" +
                "<script src=\"Administration/less-1.3.0.min.js\" type=\"text/javascript\"></script>");
        out.println("</head><body>");
        //DatabaseHandler dbHandler = new DatabaseHandler();
        //User user = dbHandler.getUser("klaus", "123456");
        //out.println("<p>" + user.getEmail() + "</p>");
        String referrer = "UserPage"; // Fallback value
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("referrer")) {
                    referrer = cookies[i].getValue();
                    break;
                }
            }
        }

        if (!req.getParameterMap().containsKey("tryagain")) {
            out.println("Log venligst ind: <br/>");
        } else {
            out.println("Uuups - prøv igen: <br/>");
        }
        out.println("<form method=\"POST\" action=\"Login\" id=\"login\">" +
                "  Username: <input type=\"text\" name=\"loginname\" size=\"15\" /><br />" +
                "  Password: <input type=\"password\" name=\"password\" size=\"15\" /><br />" +
                " <p><input type=\"submit\" value=\"Login\" /></p>" +
                "</form>");


        out.println("</body></html>");

    }

    private static String generateSessionId() throws UnsupportedEncodingException {
        String uid = new java.rmi.server.UID().toString(); // guaranteed unique
        return URLEncoder.encode(uid, "UTF-8"); // encode any special chars
    }

    private void checkOrSetSessionID(HttpServletRequest req, HttpServletResponse resp) {
        String sessionid = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("sessionid")) {
                    sessionid = cookies[i].getValue();
                    break;
                }
            }
        }

        // If the session ID wasn't sent, generate one.
        // Then be sure to send it to the client with the response.
        if (sessionid == null) {
            //TODO: Ordentlig fejlhåndtering og random string ved fejl.
            try {
                sessionid = generateSessionId();
            } catch (Exception e){

                sessionid = "abcde";
            }
            Cookie c = new Cookie("sessionid", sessionid);
            resp.addCookie(c);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        String loginname = req.getParameter("loginname");
        String password = req.getParameter("password");
        String referrer = "/User";
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("referrer")) {
                    referrer = cookies[i].getValue();
                    break;
                }
            }
        }
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = dbHandler.getUser(loginname,password);
        if(user.getId()>0) {
            // User with password exists
            UserCookie cookie =  new UserCookie(req, resp);
            cookie.setCookie(user);
            cookie.updateSession();
            resp.sendRedirect(referrer);
        } else {
            // User with password does not exist.
            resp.sendRedirect("Login?tryagain=1");

        }


    }
}
