package dk.tokebroedsted.feedsetup.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import dk.tokebroedsted.commons.client.models.FeedGWT;

import java.util.List;

public interface FeedSetupServiceAsync {

    void getOwnedFeeds(AsyncCallback<List<FeedGWT>> callback);

    void saveFeed(FeedGWT feed, AsyncCallback<String> callback);

    void deleteFeed(FeedGWT feedGWT, AsyncCallback<Void> callback);
}
