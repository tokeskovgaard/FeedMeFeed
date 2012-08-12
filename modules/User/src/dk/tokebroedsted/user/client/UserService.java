package dk.tokebroedsted.user.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.UserGWT;

import java.util.List;

@RemoteServiceRelativePath("UserService")
public interface UserService extends RemoteService {

    List<FeedGWT> getFeeds();

    List<dk.tokebroedsted.commons.client.models.UserGWT> getUsers();

    List<Integer> getInt();

    UserGWT getUser(String username);

    String deleteUser(int id);

    Boolean createUser(int id, String loginname, String username, String password, String email);

    String createUser(dk.tokebroedsted.commons.client.models.UserGWT user);

    List<FeedGWT> getSubscribedFeeds(dk.tokebroedsted.commons.client.models.UserGWT user);


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
