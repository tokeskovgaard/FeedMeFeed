package dk.tokebroedsted.user.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import dk.tokebroedsted.user.client.model.Feed;

import java.util.List;

public class UserEntryPoint implements EntryPoint{

    @Override
    public void onModuleLoad() {
        final RootPanel rootPanel = RootPanel.get("gwt");

        UserServiceAsync userService = UserService.App.getInstance();
        userService.getFeeds(new AsyncCallback<List<Feed>>(){
            @Override
            public void onFailure(Throwable caught) {
            }

            @Override
            public void onSuccess(List<Feed> result) {
                //TODO make feed header
            }
        });
    }
}
