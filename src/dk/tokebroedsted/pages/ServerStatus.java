package dk.tokebroedsted.pages;

import dk.tokebroedsted.URLImp;
import dk.tokebroedsted.hibernate.ModelFactory;
import dk.tokebroedsted.hibernate.tables.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServerStatus")
public class ServerStatus extends ServletImpl {

    public static URLImp url() {
        return new URLImp("ServerStatus");
    }

    @Override
    protected void renderBody(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
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
            List<Question> questions = ModelFactory.getAllQuestions();
            out.println("<h1>Questions</h1>");
            out.println("<table>");
            printAsTableRow(out, true, "Id", "Name", "Type", "Feed");
            for (Question question : questions) {
                printAsTableRow(out, false, question.getId(), question.getName(), question.getType().name(), question.getFeed().getTitle());
            }
            out.println("</table>");
        }

        {
            List<Calculation> calculations = ModelFactory.getAllCalculations();
            out.println("<h1>Calculations</h1>");
            out.println("<table>");
            printAsTableRow(out, true, "Id", "Name", "Feed", "Calculation");
            for (Calculation calculation : calculations) {
                printAsTableRow(out, false, calculation.getId(), calculation.getName(), calculation.getFeed().getTitle(), calculation.getCalculation());
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

        {
            List<QuestionItem> questionItems = ModelFactory.getAllFeedQuestionItems();
            out.println("<h1>QuestionItems</h1>");
            out.println("<table>");
            printAsTableRow(out, true, "Id", "NumericValue", "QuestionId", "Owner");
            for (QuestionItem questionItem : questionItems) {
                printAsTableRow(out, false, questionItem.getId(), questionItem.getNumericAnswer(), questionItem.getQuestion().getId(), questionItem.getOwner());
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
