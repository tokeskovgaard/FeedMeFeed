package dk.tokebroedsted.user.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class UserEntryPoint implements EntryPoint{

    @Override
    public void onModuleLoad() {
        final RootPanel rootPanel = RootPanel.get("gwt");
        Label label = new Label("Virker");
        label.setStyleName("ExampleLabel");
        rootPanel.add(label);

       /* UserServiceAsync userService = UserService.App.getInstance();

        userService.getMessage("MOJNS",new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onSuccess(String result) {
                rootPanel.add(new Label(result));
            }
        });*/
    }
}
