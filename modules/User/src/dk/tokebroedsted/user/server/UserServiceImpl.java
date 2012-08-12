package dk.tokebroedsted.user.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.UserGWT;
import dk.tokebroedsted.hibernate.ModelFactory;
import dk.tokebroedsted.hibernate.tables.Feed;
import dk.tokebroedsted.objects.SessionHandler;
import dk.tokebroedsted.user.client.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserServiceImpl extends RemoteServiceServlet implements UserService {
    // Implementation of sample interface method

    private ArrayList<dk.tokebroedsted.commons.client.models.UserGWT> users;

    public UserServiceImpl() {
        users = new ArrayList<dk.tokebroedsted.commons.client.models.UserGWT>();

        dk.tokebroedsted.commons.client.models.UserGWT user = new dk.tokebroedsted.commons.client.models.UserGWT();
        user.setUsername("Mads Ravn");
        user.setEmail("madsravn@gmail.com");
        user.setLoginname("madsravn");
        user.setPassword("0c4927fcbd005ee6f1d4d13b8e706d5759020c8feec035e433a38017175f029a");
        user.setId(1);
        users.add(user);

        user = new dk.tokebroedsted.commons.client.models.UserGWT();
        user.setUsername("Toke R. Brødsted");
        user.setEmail("tokebroedsted@gmail.com");
        user.setLoginname("tokeb");
        user.setPassword("d304e7f1a2161179f0aacefbe70b6f868196180ebb3e10ce62c1c93e9eef5155");
        user.setId(2);
        users.add(user);
    }

    public List<FeedGWT> getFeeds() {

//        String user = (String) getThreadLocalRequest().getSession().getAttribute("user");
//        DatabaseHandler databaseHandler = new DatabaseHandler();
//        databaseHandler.getFeedsForUser(user);

//        Feed feed = ModelFactory.getFeed(0);
        ArrayList<FeedGWT> feeds = new ArrayList<FeedGWT>();
//        feeds.add(feed);

        return feeds;
    }

    public List<dk.tokebroedsted.commons.client.models.UserGWT> getUsers() {

        /*dk.tokebroedsted.hibernate.tables.UserGWT user = (dk.tokebroedsted.hibernate.tables.UserGWT) HibernateUtil.getSomething(dk.tokebroedsted.hibernate.tables.UserGWT.class, 1);
        log(user.toString());
        UserGWT userGWT = new UserGWT();
        userGWT.setId(user.getId());
        userGWT.setEmail(user.getEmail());
        userGWT.setLoginname(user.getLoginname());
        userGWT.setPassword(user.getPassword());
        userGWT.setUsername(user.getUsername());
        users.add(userGWT);
        */

        users = new ArrayList<dk.tokebroedsted.commons.client.models.UserGWT>();
        for (dk.tokebroedsted.hibernate.tables.User user : ModelFactory.getAllUsers()) {
            dk.tokebroedsted.commons.client.models.UserGWT userGWT = new dk.tokebroedsted.commons.client.models.UserGWT(user.getId(), user.getLoginname(), user.getUsername(), user.getEmail(), user.getPassword());
            users.add(userGWT);
        }
        return users;
    }

    public UserGWT getUser(String username) {

        return new UserGWT();

    }

    public List<Integer> getInt() {
        ArrayList<Integer> ints = new ArrayList<Integer>();

        for (int i = 0; i < 10; i++) {
            ints.add(i);
        }

        return ints;
    }

    public Boolean createUser(int id, String loginname, String username, String password, String email) {
        dk.tokebroedsted.commons.client.models.UserGWT user = new dk.tokebroedsted.commons.client.models.UserGWT(id, loginname, username, password, email);
        users.add(user);

        return true;
    }


    public String deleteUser(int id) {

        ModelFactory.deleteUser(id);

        return "success";
    }

    public String createUser(dk.tokebroedsted.commons.client.models.UserGWT userGWT) {

        dk.tokebroedsted.hibernate.tables.User user = new dk.tokebroedsted.hibernate.tables.User(userGWT.getLoginname(),
                userGWT.getUsername(),
                userGWT.getPassword(),
                userGWT.getEmail());

        ModelFactory.save(user);

        return "success";
    }

    public String getCurrentUser() {
        /*HttpServletRequest request = this.getThreadLocalRequest();
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        //TODO: Make a decent check
        String user = (String)session.getAttribute("user");
        if(user == null){
            for(Cookie c : cookies) {
                if(c.getName().equals("user")) {
                    user = c.getValue();
                    session.setAttribute("user", c.getValue());
                    break;
                }
            }
        }
        return user;*/
        return SessionHandler.getCurrentUser(this.getThreadLocalRequest());
    }


    //TODO: Implement this method to get the feeds for a given user
    public ArrayList<FeedGWT> getSubscribedFeeds(dk.tokebroedsted.commons.client.models.UserGWT user) {
        Set<Feed> feedSubscriptions = ModelFactory.getUser(user.getUsername()).getFeedSubscriptions();
        ArrayList<FeedGWT> subscriptions = new ArrayList<>();
        for (Feed feedSubscription : feedSubscriptions) {
            subscriptions.add(new FeedGWT());
        }
        return subscriptions;
    }


}