package dk.tokebroedsted.administration.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import dk.tokebroedsted.commons.client.models.FeedGWT;

public interface AdministrationServiceAsync {
    void getMessage(AsyncCallback<String> async);

    void saveFeed(FeedGWT feed, AsyncCallback<String> callback);
}
