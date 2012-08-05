package dk.tokebroedsted.pages;

import dk.tokebroedsted.URLImp;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

/*        String deleteId = req.getParameter("delete");
        if(deleteId != null){
            ModelFactory.delete(Feed.class, Integer.parseInt(deleteId));
            resp.sendRedirect(url().toString());
        }*/

//        printCreatedFeeds(out);
        out.println("<script type=\"text/javascript\" language=\"javascript\" src=\"FeedSetup/feedsetup.nocache.js\"></script>");
        out.println("<div id=\"gwt_FeedSetup\"></div>");
    }

/*
    private void printCreatedFeeds(ServletOutputStream out) throws IOException {
        List<Feed> allFeeds = ModelFactory.getAllFeeds();
        out.println("<div style=\"width:200px; height:100%; float: left;\">");
        out.println("<h2>Feeds</h2>");
        out.println("<table>");
        for (Feed feed : allFeeds) {
            out.println("<tr>");
            out.println("<td>"+feed.getTitle()+"</td>");
            String slet = "<a href=\"" + FeedSetupPage.deleteFeedUrl(feed.getId()) + "\">Slet</a>";
            out.println("<td>"+slet+"</td>");
            out.println("</tr>");
        }
        out.println("</table>");

        out.println("<div>Opret ny</div>");
        out.println("</div>");
    }
*/
}
