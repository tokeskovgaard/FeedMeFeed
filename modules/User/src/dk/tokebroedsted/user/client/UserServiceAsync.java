package dk.tokebroedsted.user.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import dk.tokebroedsted.user.client.model.User;
import dk.tokebroedsted.user.client.model.Feed;

import java.util.List;

public interface UserServiceAsync {
    void getMessage(String msg, AsyncCallback<String> callback);

    void getFeeds(AsyncCallback<List<Feed>> callback);
    void getUsers(AsyncCallback<List<User>> callback);
}
