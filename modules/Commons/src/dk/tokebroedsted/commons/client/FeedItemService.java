package dk.tokebroedsted.commons.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import dk.tokebroedsted.commons.client.models.QuestionItemGWT;

@RemoteServiceRelativePath("FeedItemService")
public interface FeedItemService extends RemoteService {

    Boolean save(QuestionItemGWT questionItemGWT);

    /**
     * Utility/Convenience class.
     * Use FeedItemService.App.getInstance() to access static instance of FeedItemServiceAsync
     */
    public static class App {
        private static final FeedItemServiceAsync ourInstance = (FeedItemServiceAsync) GWT.create(FeedItemService.class);

        public static FeedItemServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
