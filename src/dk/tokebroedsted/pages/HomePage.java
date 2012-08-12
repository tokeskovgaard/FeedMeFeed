package dk.tokebroedsted.pages;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeServlet", displayName = "Home Servlet", urlPatterns = {"/Home"})
public class HomePage extends ServletImpl {

    @Override
    protected void renderBody(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.println("Velkomst  beskrivelse af FeedMeFeed");
    }
}
