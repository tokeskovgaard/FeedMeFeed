package dk.tokebroedsted.user.client.panels;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import dk.tokebroedsted.commons.client.models.UserGWT;
import dk.tokebroedsted.hibernate.HibernateUtil;

import java.util.ArrayList;

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
        users.addAll(HibernateUtil.getUsers());
        showPanel();


    }

    public SubscriberFeedPanel(UserGWT user) {
        users = new ArrayList<UserGWT>();
        users.add(user);
        showPanel();
    }

    //TODO: Iterate through all the users and get the feeds they each are subscribed to.
    private void showPanel() {
        for(UserGWT user : users) {
            FlowPanel userFeed = new FlowPanel();
            Label userLabel = new Label(user.getLoginname());
            userLabel.setStyleName("feed-user-label");


        }

    }
}
