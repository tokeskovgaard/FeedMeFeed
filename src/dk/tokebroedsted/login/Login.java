package dk.tokebroedsted.login;

import dk.tokebroedsted.hibernate.ModelFactory;
import dk.tokebroedsted.hibernate.tables.User;
import dk.tokebroedsted.objects.UserCookie;
import dk.tokebroedsted.pages.ServletImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@WebServlet(urlPatterns = "/Login")
public class Login extends ServletImpl {

    static Logger logger = LoggerFactory.getLogger(Login.class);


    @Override
    protected void renderBody(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletOutputStream out = resp.getOutputStream();

        checkOrSetSessionID(req, resp);

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
            } catch (Exception e) {

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
        String referrer = "/FeedMeFeed/Home";
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("referrer")) {
                    referrer = cookies[i].getValue();
                    break;
                }
            }
        }
        User user = ModelFactory.getUser(loginname, password);
        //DatabaseHandler dbHandler = new DatabaseHandler();
        //User user = dbHandler.getUser(loginname, password);
        if (user != null) {
            // User with password exists
            UserCookie cookie = new UserCookie(req, resp);
            cookie.setCookie(user);
            cookie.updateSession();
            resp.sendRedirect(referrer);
        } else {
            // User with password does not exist.
            resp.sendRedirect("Login?tryagain=1");
        }
    }
}
