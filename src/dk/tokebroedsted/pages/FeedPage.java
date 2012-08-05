package dk.tokebroedsted.pages;

import dk.tokebroedsted.URLImp;

import javax.servlet.ServletOutputStream;
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
public class FeedPage extends ServletImpl {

    public static URLImp url() {
        return new URLImp("Feed");
    }

    @Override
    protected void renderBody(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />");

        out.println("<script type=\"text/javascript\" language=\"javascript\" src=\"Feed/feed.nocache.js\"></script>");
        out.println("<div id=\"gwt_feed\"></div>");
    }
}
