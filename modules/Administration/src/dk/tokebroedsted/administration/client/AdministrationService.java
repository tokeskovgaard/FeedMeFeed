package dk.tokebroedsted.administration.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import dk.tokebroedsted.administration.client.model.Feed;

@RemoteServiceRelativePath("AdministrationService")
public interface AdministrationService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage();

    String saveFeed(Feed feed);

    /**
     * Utility/Convenience class.
     * Use AdministrationService.App.getInstance() to access static instance of tokebroedstedServiceAsync
     */
    public static class App {
        private static AdministrationServiceAsync ourInstance = GWT.create(AdministrationService.class);

        public static synchronized AdministrationServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
