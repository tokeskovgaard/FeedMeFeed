package dk.tokebroedsted.administration.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("tokebroedstedService")
public interface AdministrationService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage();

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
