package dk.tokebroedsted.feed.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import dk.tokebroedsted.commons.client.models.*;
import dk.tokebroedsted.feed.client.FeedService;
import dk.tokebroedsted.hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class FeedServiceImpl extends RemoteServiceServlet implements FeedService {

    @Override
    public List<FeedGWT> getUsersFeeds() {


//        HibernateUtil.getUser()

        ArrayList<FeedGWT> feeds = new ArrayList<FeedGWT>();


        StringBuilder html = new StringBuilder();
        html.append("<div class=\"feedItem\">");
        html.append("Titel: ").append("<%input 1%><br />");
        html.append("Genre: ").append("<%input 2%><br />");
        html.append("<br />");
        html.append("Angiv karakter for sjov: ").append("<%question 1%><br />");
        html.append("Summeret karakter sjov: ").append("<%calculation 1%><br />");
        html.append("<br />");
        html.append("Angiv karakter for andet: ").append("<%question 2%><br />");
        html.append("Summeret karakter andet: ").append("<%calculation 2%><br />");
        html.append("</div>");

        StringBuilder css = new StringBuilder();
        css.append("<style type=\"text/css\">");
        css.append(".feedItem {");
        css.append("background-color: #AAA");
        css.append("}");
        css.append("</style>");

        FeedGWT feedGWT = new FeedGWT();
        feedGWT.setTitle("Film");
        feedGWT.setHTML(html.toString());
        feedGWT.setCss(css.toString());

        feeds.add(feedGWT);

        return feeds;
    }

    @Override
    public List<FeedItemGWT> getFeedItems(FeedGWT feedGWT) {
        ArrayList<FeedItemGWT> feedItems = new ArrayList<FeedItemGWT>();

        InputGWT input1 = new InputGWT();
        input1.setName("<%input 1%>");
        input1.setType(InputGWT.Type.string);

        InputGWT input2 = new InputGWT();
        input1.setName("<%input 2%>");
        input1.setType(InputGWT.Type.string);

        ArrayList<InputItemGWT> inputItemGWTs = new ArrayList<InputItemGWT>();
        InputItemGWT inputItem1 = new InputItemGWT(input1);
        inputItem1.setValue("Batman begins");
        inputItemGWTs.add(inputItem1);

        InputItemGWT inputItem2 = new InputItemGWT(input2);
        inputItem2.setValue("Action");
        inputItemGWTs.add(inputItem2);


        ArrayList<QuestionItemGWT> questionItemGWTs = new ArrayList<QuestionItemGWT>();
        QuestionItemGWT questionItem1 = new QuestionItemGWT();
        questionItem1.setName("<%question 1%>");
        questionItem1.setType(QuestionItemGWT.Type.numeric);
        questionItemGWTs.add(questionItem1);

        QuestionItemGWT questionItem2 = new QuestionItemGWT();
        questionItem2.setName("<%question 2%>");
        questionItem2.setType(QuestionItemGWT.Type.numeric);
        questionItem2.setNumericAnswer(4);
        questionItemGWTs.add(questionItem2);


        ArrayList<CalculationItemGWT> calculationItemGWTs = new ArrayList<CalculationItemGWT>();
        CalculationItemGWT calculationItem1 = new CalculationItemGWT();
        calculationItem1.setName("<%calculation 1%>");
        calculationItem1.setValue(String.valueOf(questionItem1.getNumericAnswer()));
        calculationItemGWTs.add(calculationItem1);

        CalculationItemGWT calculationItem2 = new CalculationItemGWT();
        calculationItem2.setName("<%calculation 2%>");
        calculationItem2.setValue(String.valueOf(questionItem2.getNumericAnswer()));
        calculationItemGWTs.add(calculationItem2);


        FeedItemGWT feedItemGWT = new FeedItemGWT();
        feedItemGWT.setInputItems(inputItemGWTs);
        feedItemGWT.setQuestionItems(questionItemGWTs);
        feedItemGWT.setCalculationItems(calculationItemGWTs);

        feedItems.add(feedItemGWT);

        return feedItems;
    }

    @Override
    public Boolean addFeedItem(FeedItemGWT feedItemGWT) {
        return Boolean.TRUE;
    }
}