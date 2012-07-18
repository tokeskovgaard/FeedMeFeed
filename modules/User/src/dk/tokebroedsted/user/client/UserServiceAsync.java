package dk.tokebroedsted.user.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import dk.tokebroedsted.user.client.model.User;
import dk.tokebroedsted.user.client.model.Feed;

import java.util.List;

public interface UserServiceAsync {
    void getMessage(String msg, AsyncCallback<String> callback);

    void getFeeds(AsyncCallback<List<Feed>> callback);

    void getUsers(AsyncCallback<List<User>> callback);

    void getInt(AsyncCallback<List<Integer>> callback);

    void getUser(String username, AsyncCallback<User> callback);

    void deleteUser(int id, AsyncCallback<String> callback);

    void createUser(int id, String loginname, String username, String password, String email, AsyncCallback<Boolean> callback);

    void createUser(User user, AsyncCallback<String> callback);
}
