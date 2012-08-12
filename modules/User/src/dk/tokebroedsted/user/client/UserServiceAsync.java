package dk.tokebroedsted.user.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.UserGWT;

import java.util.List;

public interface UserServiceAsync {

    void getFeeds(AsyncCallback<List<FeedGWT>> callback);

    void getUsers(AsyncCallback<List<dk.tokebroedsted.commons.client.models.UserGWT>> callback);

    void getInt(AsyncCallback<List<Integer>> callback);

    void getUser(String username, AsyncCallback<UserGWT> callback);

    void deleteUser(int id, AsyncCallback<String> callback);

    void createUser(int id, String loginname, String username, String password, String email, AsyncCallback<Boolean> callback);

    void createUser(dk.tokebroedsted.commons.client.models.UserGWT user, AsyncCallback<String> callback);

    void getSubscribedFeeds(dk.tokebroedsted.commons.client.models.UserGWT user, AsyncCallback<List<FeedGWT>> callback);

}
