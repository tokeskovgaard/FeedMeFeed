package dk.tokebroedsted.feed.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import dk.tokebroedsted.feed.client.model.FeedGWT;
import dk.tokebroedsted.feed.client.model.FeedItemGWT;

import java.util.List;

@RemoteServiceRelativePath("FeedService")
public interface FeedService extends RemoteService {

    List<FeedGWT> getUsersFeeds();

    List<FeedItemGWT> getFeedItems(FeedGWT feedGWT);

    public static class App {
        private static FeedServiceAsync ourInstance = GWT.create(FeedService.class);

        public static synchronized FeedServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
