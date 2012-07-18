package dk.tokebroedsted.user.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import dk.tokebroedsted.user.client.model.User;

import java.util.List;

public class UserEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        final RootPanel rootPanel = RootPanel.get("gwt");

        rootPanel.add(new Label("Gwt loadet"));
        UserServiceAsync userService = UserService.App.getInstance();
        /*userService.getFeeds(new AsyncCallback<List<Feed>>() {
            @Override
            public void onFailure(Throwable caught) {
            }

            @Override
            public void onSuccess(List<Feed> result) {
                //TODO make feed header

            }
        });*/

        userService.getUsers(new AsyncCallback<List<User>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error:" + caught.getMessage());
            }

            @Override
            public void onSuccess(List<User> result) {
                Window.alert("YEE");
                for (User user : result) {
                    rootPanel.add(new Label(user.getUsername()));
                }
            }
        });
    }
}
