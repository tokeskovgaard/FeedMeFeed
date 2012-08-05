package dk.tokebroedsted.feedsetup.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import dk.tokebroedsted.commons.client.models.FeedGWT;

import java.util.List;

@RemoteServiceRelativePath("FeedSetupService")
public interface FeedSetupService extends RemoteService {
    // Sample interface method of remote interface

    List<FeedGWT> getOwnedFeeds();

    String saveFeed(FeedGWT feed);

    void deleteFeed(FeedGWT feed);

    /**
     * Utility/Convenience class.
     * Use FeedSetupService.App.getInstance() to access static instance of tokebroedstedServiceAsync
     */
    public static class App {
        private static FeedSetupServiceAsync ourInstance = GWT.create(FeedSetupService.class);

        public static synchronized FeedSetupServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
