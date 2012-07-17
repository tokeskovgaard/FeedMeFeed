package dk.tokebroedsted.user.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import dk.tokebroedsted.hibernate.HibernateUtil;
import dk.tokebroedsted.user.client.UserService;
import dk.tokebroedsted.user.client.model.Feed;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends RemoteServiceServlet implements UserService {
    // Implementation of sample interface method

    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }

    public List<Feed> getFeeds(){

//        String user = (String) getThreadLocalRequest().getSession().getAttribute("user");
//        DatabaseHandler databaseHandler = new DatabaseHandler();
//        databaseHandler.getFeedsForUser(user);

        Feed feed = (Feed) HibernateUtil.getSomething(Feed.class, 0);
        ArrayList<Feed> feeds = new ArrayList<>();
        feeds.add(feed);

        return  feeds;
    }
}