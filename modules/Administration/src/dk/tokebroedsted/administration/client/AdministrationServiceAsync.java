package dk.tokebroedsted.administration.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import dk.tokebroedsted.administration.client.model.Feed;

public interface AdministrationServiceAsync {
    void getMessage(AsyncCallback<String> async);

    void saveFeed(Feed feed, AsyncCallback<String> callback);
}
