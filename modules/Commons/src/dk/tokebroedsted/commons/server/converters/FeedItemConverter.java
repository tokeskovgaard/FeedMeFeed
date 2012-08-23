package dk.tokebroedsted.commons.server.converters;

import dk.tokebroedsted.commons.client.models.CalculationValueGWT;
import dk.tokebroedsted.commons.client.models.FeedItemGWT;
import dk.tokebroedsted.commons.client.models.InputItemGWT;
import dk.tokebroedsted.commons.client.models.QuestionItemGWT;
import dk.tokebroedsted.hibernate.ModelFactory;
import dk.tokebroedsted.hibernate.tables.*;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 19-07-12
 * Time: 00:01
 * To change this template use File | Settings | File Templates.
 */
public class FeedItemConverter implements Converter<FeedItemGWT, FeedItem> {

    private Feed feed;
    private User user;

    private FeedItemConverter() {
    }

    private FeedItemConverter(Feed feed, User user) {
        this.feed = feed;
        this.user = user;
    }

    @Override
    public FeedItem toServerObject(FeedItemGWT feedItemGWT) {
        FeedItem feedItem = new FeedItem();
        HashSet<FeedItemInput> feedItemInputs = new HashSet<FeedItemInput>();

        InputItemConverter inputItemConverter = InputItemConverter.toServer(feedItem);

        for (InputItemGWT inputItemGWT : feedItemGWT.getInputItems()) {
            FeedItemInput feedItemInput = inputItemConverter.toServerObject(inputItemGWT);
            feedItemInputs.add(feedItemInput);

            ModelFactory.save(feedItemInput);
        }

        feedItem.setFeed(feed);
        feedItem.setOwner(user);
        feedItem.setItemInputs(feedItemInputs);
        return feedItem;
    }

    @Override
    public FeedItemGWT toGwtObject(FeedItem feedItem) {
        ArrayList<InputItemGWT> inputItemGWTs = new ArrayList<InputItemGWT>();
        InputItemConverter inputItemConverter = InputItemConverter.toGwt();
        for (FeedItemInput feedItemInput : feedItem.getFeedItemInputs()) {
            inputItemGWTs.add(inputItemConverter.toGwtObject(feedItemInput));
        }

        ArrayList<QuestionItemGWT> questionItemGWTs = new ArrayList<QuestionItemGWT>();
        QuestionItemConverter questionItemConverter = new QuestionItemConverter();
        for (QuestionItem questionItem : feedItem.getQuestionItems()) {
            questionItemGWTs.add(questionItemConverter.toGwtObject(questionItem));
        }

        Feed feed = feedItem.getFeed();
        ArrayList<CalculationValueGWT> calculationValueGWTs = new ArrayList<CalculationValueGWT>();
        for (Calculation calculation : feed.getCalculations()) {
        }


        int id = feedItem.getId();
        int feedId = feed.getId();
        FeedItemGWT feedItemGWT = new FeedItemGWT(id, feedId, inputItemGWTs, questionItemGWTs, new ArrayList<CalculationValueGWT>());

        return feedItemGWT;
    }

    public static FeedItemConverter toGWT() {
        return new FeedItemConverter();
    }

    public static FeedItemConverter toServer(Feed feed, User user) {
        return new FeedItemConverter(feed, user);
    }
}
