package dk.tokebroedsted.feed.client;


import com.google.gwt.user.client.rpc.AsyncCallback;
import dk.tokebroedsted.commons.client.DefaultCallback;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.FeedItemGWT;

import java.util.List;

public interface FeedServiceAsync {
    void getUsersFeeds(AsyncCallback<List<FeedGWT>> callback);

    void getFeedItems(FeedGWT feedGWT, AsyncCallback<List<FeedItemGWT>> callback);

    void saveFeedItem(FeedItemGWT feedItemGWT, AsyncCallback<Boolean> callback);
}
