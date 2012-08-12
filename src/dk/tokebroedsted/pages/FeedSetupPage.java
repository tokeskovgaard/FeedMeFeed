package dk.tokebroedsted.pages;

import dk.tokebroedsted.URLImp;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/FeedSetup")
public class FeedSetupPage extends ServletImpl {

    public static URLImp url() {
        return new URLImp("FeedSetup");
    }

//    private static URLImp deleteFeedUrl(Integer id) {
//        URLImp url = new URLImp("FeedSetup");
//        url.addParameter("delete",id);
//        return url;
//    }

    @Override
    protected void renderBody(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletOutputStream out = resp.getOutputStream();
        //TODO handle login and only show feeds for User

        out.println("<script type=\"text/javascript\" language=\"javascript\" src=\"FeedSetup/feedsetup.nocache.js\"></script>");
        out.println("<div id=\"gwt_navigation\"></div>");
        out.println("<div id=\"gwt_content\"></div>");
    }
}
