package dk.tokebroedsted.feed.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import dk.tokebroedsted.commons.client.models.*;
import dk.tokebroedsted.commons.server.converters.FeedConverter;
import dk.tokebroedsted.commons.server.converters.FeedItemConverter;
import dk.tokebroedsted.feed.client.FeedService;
import dk.tokebroedsted.hibernate.ModelFactory;
import dk.tokebroedsted.hibernate.tables.*;

import java.util.ArrayList;
import java.util.List;

public class FeedServiceImpl extends RemoteServiceServlet implements FeedService {

    @Override
    public List<FeedGWT> getUsersFeeds() {
        User user = ModelFactory.getUser("toke");

        ArrayList<FeedGWT> feedGWTs = new ArrayList<FeedGWT>();
        FeedConverter feedConverter = new FeedConverter();
        for (Feed feed : user.getCreatedFeeds()) {
            FeedGWT feedGWT = feedConverter.toGwtObject(feed);
            feedGWTs.add(feedGWT);
        }

        return feedGWTs;
    }

    @Override
    public Boolean saveFeedItem(FeedItemGWT feedItemGWT) {
        Feed feed = ModelFactory.getFeed(feedItemGWT.getFeedId());
        User user = ModelFactory.getUser("toke");

        FeedItem feedItem = new FeedItem();
        feedItem.setFeed(feed);
        feedItem.setOwner(user);
        ModelFactory.save(feedItem);

        for (InputItemGWT inputItemGWT : feedItemGWT.getInputItems()) {
            FeedInput input = ModelFactory.getInput(inputItemGWT.getInputGWT().getId());

            FeedItemInput feedItemInput = new FeedItemInput();
            feedItemInput.setValue(inputItemGWT.getValue());
            feedItemInput.setFeedItem(feedItem);
            feedItemInput.setFeedInput(input);

            ModelFactory.save(feedItemInput);
        }

        return Boolean.TRUE;
    }

    @Override
    public List<FeedItemGWT> getFeedItems(FeedGWT feedGWT) {
        ArrayList<FeedItemGWT> feedItemGWTs = new ArrayList<FeedItemGWT>();

        FeedItemConverter feedItemConverter = FeedItemConverter.toGWT();
        List<FeedItem> feedItems = ModelFactory.getFeedItems(feedGWT.getFeedId());
        for (FeedItem feedItem : feedItems) {
            FeedItemGWT feedItemGWT = feedItemConverter.toGwtObject(feedItem);
            feedItemGWTs.add(feedItemGWT);
        }

        return feedItemGWTs;
    }

    @Override
    public void saveQuestionReply(QuestionItemGWT questionItem) {
    }
}