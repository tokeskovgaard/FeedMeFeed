package dk.tokebroedsted.pages;

import dk.tokebroedsted.URLImp;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserPage extends ServletImpl {

    public static URLImp url() {
        return new URLImp("User");
    }

    @Override
    protected void renderBody(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletOutputStream out = resp.getOutputStream();

        out.println("Du ramte lige Brugersiden");

        out.println("<script type=\"text/javascript\" language=\"javascript\" src=\"User/user.nocache.js\"></script>");
        out.println("<div id=\"gwt\"></div>");
    }
}
