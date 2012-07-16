package dk.tokebroedsted;

import dk.tokebroedsted.objects.UserCookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: mads
 * Date: 7/16/12
 * Time: 11:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserCookie userCookie = new UserCookie(req, resp);
        userCookie.crashCookieAndSession();

        Cookie referrer = new Cookie("referrer", "");
        resp.addCookie(referrer);
        resp.sendRedirect("Login");
    }
}
