package dk.tokebroedsted.user.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.user.client.panels.AddUserPanel;
import dk.tokebroedsted.user.client.panels.ShowUserPanel;
import dk.tokebroedsted.user.client.panels.SubscriberFeedPanel;


public class UserEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        final RootPanel rootPanel = RootPanel.get("gwt");
        final UserServiceAsync userService = UserService.App.getInstance();


        rootPanel.add(new Label("Gwt loadet"));

        /*
        Button showUsersButton = new Button("Vis/skjul brugere");
        showUsersButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                userService.getUsers(new AsyncCallback<List<User>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("Error:" + caught.getMessage());
                    }

                    @Override
                    public void onSuccess(List<User> result) {

                        Element e = DOM.getElementById("user-table");
                        if (e == null) {
                            HashMap<Button, Integer> hash = new HashMap<Button, Integer>();
                            FlowPanel flowPanel = new FlowPanel();
                            flowPanel.getElement().setId("user-table");

                            for (User user : result) {
                                FlowPanel flow = new FlowPanel();
                                Label label = new Label(user.getUsername());
                                label.setStyleName("user-table-label");
                                flow.add(label);
                                Button button = new Button("Slet");
                                button.setStyleName("user-table-button");
                                flow.add(button);
                                flow.setStyleName("user-table-row");
                                flowPanel.add(flow);

                                hash.put(button, user.getId());
                            }
                            flowPanel.setStyleName("user-table");
                            rootPanel.add(flowPanel);

                            Iterator it = hash.entrySet().iterator();
                            while (it.hasNext()) {
                                final Map.Entry pairs = (Map.Entry) it.next();
                                System.out.println(pairs.getKey() + " = " + pairs.getValue());
                                ((Button) pairs.getKey()).addClickHandler(new ClickHandler() {
                                    @Override
                                    public void onClick(ClickEvent event) {
                                        Window.alert("Slet id = " + pairs.getValue());
                                    }
                                });

                                it.remove(); // avoids a ConcurrentModificationException
                            }
                        } else {
                            e.removeFromParent();
                        }
                    }
                });


            }
        });
        rootPanel.add(showUsersButton);
        */
        ShowUserPanel showUserPanel = new ShowUserPanel();
        rootPanel.add(showUserPanel);

        AddUserPanel AddUserPanel = new AddUserPanel(showUserPanel);
        rootPanel.add(AddUserPanel);

        //SubscriberFeedPanel subscriberFeedPanel = new SubscriberFeedPanel();
        //rootPanel.add(subscriberFeedPanel);


        /*userService.getFeeds(new AsyncCallback<List<Feed>>() {
            @Override
            public void onFailure(Throwable caught) {
            }

            @Override
            public void onSuccess(List<Feed> result) {
                //TODO make feed header

            }
        });*/
        /*


        userService.getInt(new AsyncCallback<List<Integer>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error:" + caught.getMessage());
            }

            @Override
            public void onSuccess(List<Integer> result) {
                //Window.alert("YEE");

                for (Integer user : result) {
                    rootPanel.add(new Label(user.toString()));
                }
            }
        });
        */
    }
}
