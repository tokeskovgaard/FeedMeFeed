package dk.tokebroedsted.pages;

import dk.tokebroedsted.hibernate.ModelFactory;
import dk.tokebroedsted.hibernate.tables.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 19-07-12
 * Time: 13:07
 * To change this template use File | Settings | File Templates.
 */
public class ServerStatus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();


        {
            List<User> users = ModelFactory.getAllUsers();
            out.println("<h1>User</h1>");
            out.println("<table>");
            printAsTableRow(out, true, "Id", "Username", "Loginname", "Email", "#CreatedFeeds", "#SubscribedFeeds");
            for (User user : users) {
                printAsTableRow(out, false, user.getId(), user.getUsername(), user.getLoginname(), user.getEmail(), user.getCreatedFeeds().size(), user.getFeedSubscriptions().size());
            }
            out.println("</table>");
        }

        {
            List<Feed> feeds = ModelFactory.getAllFeeds();
            out.println("<h1>Feeds</h1>");
            out.println("<table>");
            printAsTableRow(out, true, "Id", "Title", "#Inputs", "Owner");
            for (Feed feed : feeds) {
                printAsTableRow(out, false, feed.getId(), feed.getTitle(), feed.getFeedInputs().size(), feed.getOwner().getUsername());
            }
            out.println("</table>");
        }

        {
            List<FeedInput> feedInputs = ModelFactory.getAllFeedInputs();
            out.println("<h1>Inputs</h1>");
            out.println("<table>");
            printAsTableRow(out, true, "Id", "Name", "Type", "Feed");
            for (FeedInput feedInput : feedInputs) {
                printAsTableRow(out, false, feedInput.getId(), feedInput.getName(), feedInput.getType().name(), feedInput.getFeed().getTitle());
            }
            out.println("</table>");
        }

        {
            List<FeedItem> feedItems = ModelFactory.getAllFeedItems();
            out.println("<h1>FeedItems</h1>");
            out.println("<table>");
            printAsTableRow(out, true, "Id", "FeedTitle", "#InputItems", "Owner");
            for (FeedItem feedItem : feedItems) {
                printAsTableRow(out, false, feedItem.getId(), feedItem.getFeed().getTitle(), feedItem.getFeedItemInputs().size(), feedItem.getOwner().getUsername());
            }
            out.println("</table>");
        }

        {
            List<FeedItemInput> feedItemInputs = ModelFactory.getAllFeedInputItems();
            out.println("<h1>InputItems</h1>");
            out.println("<table>");
            printAsTableRow(out, true, "Id", "Value", "FeedItemId", "FeedInputId");
            for (FeedItemInput feedItemInput : feedItemInputs) {
                printAsTableRow(out, false, feedItemInput.getId(), feedItemInput.getValue(), feedItemInput.getFeedItem().getId(), feedItemInput.getFeedInput().getId());
            }
            out.println("</table>");
        }
    }

    private void printAsTableRow(ServletOutputStream out, boolean header, Object... columns) throws IOException {
        out.println("<tr>");
        String boldStart = "";
        String boldEnd = "";
        if (header) {
            boldStart = "<b>";
            boldEnd = "</b>";
        }
        for (Object column : columns) {
            out.print("<td>");
            out.print(boldStart);
            out.print(column.toString());
            out.print(boldEnd);
            out.print("</td>");
        }
        out.println("</tr>");
    }
}
