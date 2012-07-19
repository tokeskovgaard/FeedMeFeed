package dk.tokebroedsted.user.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.UserGWT;
import dk.tokebroedsted.user.client.model.Feed;
import dk.tokebroedsted.user.client.model.User;

import java.util.List;

@RemoteServiceRelativePath("UserService")
public interface UserService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    List<Feed> getFeeds();

    List<UserGWT> getUsers();

    List<Integer> getInt();

    User getUser(String username);

    String deleteUser(int id);

    Boolean createUser(int id, String loginname, String username, String password, String email);

    String createUser(UserGWT user);

    List<FeedGWT> getSubscribedFeeds(UserGWT user);



    /**
     * Utility/Convenience class.
     * Use UserService.App.getInstance() to access static instance of UserServiceAsync
     */
    public static class App {
        private static UserServiceAsync ourInstance = GWT.create(UserService.class);

        public static synchronized UserServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
