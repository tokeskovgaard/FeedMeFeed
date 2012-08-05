package dk.tokebroedsted.user.client.panels;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import dk.tokebroedsted.commons.client.models.UserGWT;
import dk.tokebroedsted.user.client.UserService;
import dk.tokebroedsted.user.client.UserServiceAsync;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mads
 * Date: 7/18/12
 * Time: 4:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShowUserPanel extends FlowPanel {

    Button showUsersButton;

    public ShowUserPanel() {

        final UserServiceAsync userService = UserService.App.getInstance();

        showUsersButton = new Button("Vis/skjul brugere");
        showUsersButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                userService.getUsers(new AsyncCallback<List<UserGWT>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("Error:" + caught.getMessage());
                    }

                    @Override
                    public void onSuccess(List<UserGWT> result) {

                        Element e = DOM.getElementById("user-table");
                        if (e == null) {
                            HashMap<Button, Integer> hash = new HashMap<Button, Integer>();
                            FlowPanel flowPanel = new FlowPanel();
                            flowPanel.getElement().setId("user-table");

                            for (UserGWT user : result) {
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
                            add(flowPanel);

                            Iterator it = hash.entrySet().iterator();
                            while (it.hasNext()) {
                                final Map.Entry pairs = (Map.Entry) it.next();
                                System.out.println(pairs.getKey() + " = " + pairs.getValue());
                                ((Button) pairs.getKey()).addClickHandler(new ClickHandler() {
                                    @Override
                                    public void onClick(ClickEvent event) {
                                        Window.alert("Slet id = " + pairs.getValue());
                                        int id = (Integer) pairs.getValue();
                                        userService.deleteUser(id, new AsyncCallback<String>() {
                                            @Override
                                            public void onFailure(Throwable caught) {
                                                Window.alert("Error:" + caught.getMessage());
                                            }

                                            @Override
                                            public void onSuccess(String result) {
                                                update();

                                            }
                                        });

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
        add(showUsersButton);
    }

    void update() {

        final UserServiceAsync userService = UserService.App.getInstance();


        userService.getUsers(new AsyncCallback<List<UserGWT>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error:" + caught.getMessage());
            }

            @Override
            public void onSuccess(List<UserGWT> result) {

                Element e = DOM.getElementById("user-table");
                clear(); // Clear vores ShowUserPanel
                add(showUsersButton);
                // Hvis den fandtes i forvejen, tilføj den igen.
                if (e != null) {
                    HashMap<Button, Integer> hash = new HashMap<Button, Integer>();
                    FlowPanel flowPanel = new FlowPanel();
                    flowPanel.getElement().setId("user-table");

                    for (UserGWT user : result) {
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
                    add(flowPanel);

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

                }
            }
        });


    }

}
