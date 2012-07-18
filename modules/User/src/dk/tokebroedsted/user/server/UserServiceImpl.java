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

    private ArrayList<User> users;

    public UserServiceImpl() {

        users = new ArrayList<User>();

        User user = new User();
        user.setUsername("Mads Ravn");
        user.setEmail("madsravn@gmail.com");
        user.setLoginname("madsravn");
        user.setPassword("0c4927fcbd005ee6f1d4d13b8e706d5759020c8feec035e433a38017175f029a");
        user.setId(1);
        users.add(user);

        user = new User();
        user.setUsername("Toke Brødsted");
        user.setEmail("tokebroedsted@gmail.com");
        user.setLoginname("tokeb");
        user.setPassword("d304e7f1a2161179f0aacefbe70b6f868196180ebb3e10ce62c1c93e9eef5155");
        user.setId(2);
        users.add(user);
    }

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

        //dk.tokebroedsted.hibernate.tables.User user = (dk.tokebroedsted.hibernate.tables.User) HibernateUtil.getSomething(dk.tokebroedsted.hibernate.tables.User.class, 0);

        return users;
    }

    public User getUser(String username) {

        return new User();

    }

    public List<Integer> getInt() {
        ArrayList<Integer> ints = new ArrayList<Integer>();

        for(int i = 0; i < 10; i++) {
            ints.add(i);
        }

        return ints;
    }

    public Boolean createUser(int id, String loginname, String username, String password, String email)   {
        User user = new User(id, loginname, username, password, email);
        users.add(user);

        return true;
    }
}