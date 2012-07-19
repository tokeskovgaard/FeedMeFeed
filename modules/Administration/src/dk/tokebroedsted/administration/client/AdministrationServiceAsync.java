package dk.tokebroedsted.administration.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import dk.tokebroedsted.commons.client.models.FeedGWT;

import java.util.List;
import java.util.Set;

public interface AdministrationServiceAsync {

    void getOwnedFeeds(AsyncCallback<List<FeedGWT>> callback);

    void saveFeed(FeedGWT feed, AsyncCallback<String> callback);
}
