package dk.tokebroedsted.user.client.panels;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.UserGWT;
import dk.tokebroedsted.user.client.UserService;
import dk.tokebroedsted.user.client.UserServiceAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mads
 * Date: 7/19/12
 * Time: 6:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class SubscriberFeedPanel extends FlowPanel {


    private ArrayList<UserGWT> users;


    // Constructor for a panel with all users subscribed feeds
    public SubscriberFeedPanel() {
        users = new ArrayList<UserGWT>();
        //users.addAll(HibernateUtil.getUsers());
        final UserServiceAsync userService = UserService.App.getInstance();
        userService.getUsers(new AsyncCallback<List<UserGWT>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }

            @Override
            public void onSuccess(List<UserGWT> result) {
                users.addAll(result);
            }
        });
        showPanel();


    }

    public SubscriberFeedPanel(UserGWT user) {
        users = new ArrayList<UserGWT>();
        users.add(user);
        showPanel();
    }


    private void showPanel() {
        final UserServiceAsync userService = UserService.App.getInstance();
        for (UserGWT user : users) {
            final FlowPanel userFeed = new FlowPanel();
            Label userLabel = new Label(user.getLoginname());
            userLabel.setStyleName("feed-user-label");
            userFeed.add(userLabel);
            userService.getSubscribedFeeds(user, new AsyncCallback<List<FeedGWT>>() {
                @Override
                public void onFailure(Throwable caught) {
                    Window.alert(caught.getMessage());
                }

                @Override
                public void onSuccess(List<FeedGWT> result) {
                    if (result != null) {
                        for (FeedGWT feed : result) {
                            Label feedLabel = new Label(feed.getTitle());
                            feedLabel.setStyleName("feed-feed-label");
                            userFeed.add(feedLabel);

                        }
                    }
                }
            });
            add(userFeed);

        }

    }
}
