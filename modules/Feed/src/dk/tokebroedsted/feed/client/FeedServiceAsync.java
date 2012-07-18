package dk.tokebroedsted.feed.client;


import com.google.gwt.user.client.rpc.AsyncCallback;
import dk.tokebroedsted.feed.client.commons.DefaultCallback;
import dk.tokebroedsted.feed.client.model.FeedGWT;
import dk.tokebroedsted.feed.client.model.FeedItemGWT;

import java.util.List;

public interface FeedServiceAsync {
    void getUsersFeeds(AsyncCallback<List<FeedGWT>> callback);

    void getFeedItems(FeedGWT feedGWT, AsyncCallback<List<FeedItemGWT>> callback);
}
