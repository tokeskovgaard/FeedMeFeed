package dk.tokebroedsted.login;

import dk.tokebroedsted.objects.UserCookie;
import dk.tokebroedsted.pages.ServletImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/Logout")
public class Logout extends ServletImpl {

    @Override
    protected void renderBody(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserCookie userCookie = new UserCookie(req, resp);
        userCookie.crashCookieAndSession();

        Cookie referrer = new Cookie("referrer", "");
        resp.addCookie(referrer);
        resp.sendRedirect("Login");
    }
}
