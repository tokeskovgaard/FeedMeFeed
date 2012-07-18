package dk.tokebroedsted.feed.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import dk.tokebroedsted.commons.client.DefaultCallback;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.FeedItemGWT;

import java.util.List;

@RemoteServiceRelativePath("FeedService")
public interface FeedService extends RemoteService {

    List<FeedGWT> getUsersFeeds();

    List<FeedItemGWT> getFeedItems(FeedGWT feedGWT);

    Boolean addFeedItem(FeedItemGWT feedItemGWT);

    public static class App {
        private static FeedServiceAsync ourInstance = GWT.create(FeedService.class);

        public static synchronized FeedServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
