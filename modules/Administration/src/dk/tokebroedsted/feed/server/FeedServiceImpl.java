package dk.tokebroedsted.feed.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import dk.tokebroedsted.commons.client.models.*;
import dk.tokebroedsted.commons.converters.FeedConverter;
import dk.tokebroedsted.commons.converters.FeedItemConverter;
import dk.tokebroedsted.feed.client.FeedService;
import dk.tokebroedsted.hibernate.ModelFactory;
import dk.tokebroedsted.hibernate.tables.Feed;
import dk.tokebroedsted.hibernate.tables.FeedItem;
import dk.tokebroedsted.hibernate.tables.User;

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
    public List<FeedItemGWT> getFeedItems(FeedGWT feedGWT) {
        ArrayList<FeedItemGWT> feedItemGWTs = new ArrayList<FeedItemGWT>();

        FeedItemConverter feedItemConverter = new FeedItemConverter();
        List<FeedItem> feedItems = ModelFactory.getFeedItems(feedGWT.getFeedId());
        for (FeedItem feedItem : feedItems) {
            FeedItemGWT feedItemGWT = feedItemConverter.toGwtObject(feedItem);
            feedItemGWTs.add(feedItemGWT);
        }

        return feedItemGWTs;
    }

    @Override
    public Boolean addFeedItem(FeedItemGWT feedItemGWT) {
        return Boolean.TRUE;
    }
}