package dk.tokebroedsted.user.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import dk.tokebroedsted.hibernate.HibernateUtil;
import dk.tokebroedsted.user.client.model.User;
import dk.tokebroedsted.user.client.UserService;
import dk.tokebroedsted.user.client.model.Feed;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends RemoteServiceServlet implements UserService {
    // Implementation of sample interface method

    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }

    public List<Feed> getFeeds() {

//        String user = (String) getThreadLocalRequest().getSession().getAttribute("user");
//        DatabaseHandler databaseHandler = new DatabaseHandler();
//        databaseHandler.getFeedsForUser(user);

        Feed feed = (Feed) HibernateUtil.getSomething(Feed.class, 0);
        ArrayList<Feed> feeds = new ArrayList<Feed>();
        feeds.add(feed);

        return feeds;
    }

    public List<User> getUsers() {

/*        dk.tokebroedsted.hibernate.tables.User user = (dk.tokebroedsted.hibernate.tables.User) HibernateUtil.getSomething(dk.tokebroedsted.hibernate.tables.User.class, 0);

        User userGWT = new User();
        userGWT.setUsername(user.getUsername());
        userGWT.setEmail(user.getEmail());
        userGWT.setLoginname(user.getLoginname());
        userGWT.setPassword(user.getPassword());
        userGWT.setId(user.getId());

        ArrayList<User> users = new ArrayList<User>();
        users.add(userGWT);*/

        return new ArrayList<>();
    }
}