package dk.tokebroedsted.user.client.panels;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import dk.tokebroedsted.user.client.UserService;
import dk.tokebroedsted.user.client.UserServiceAsync;
import dk.tokebroedsted.user.client.model.User;

import java.util.List;

//TODO Add a wrapper for the ShowUserPanel and a method to update its reference
public class AddUserPanel extends FlowPanel {


    public AddUserPanel() {
        this(null);
    }

    public AddUserPanel(final ShowUserPanel showUserPanel) {


        final UserServiceAsync userService = UserService.App.getInstance();

        FlowPanel createUserPanel = new FlowPanel();
        final TextBox usernameTextBox = new TextBox();
        final TextBox loginTextBox = new TextBox();
        final TextBox passwordTextBox = new TextBox();
        final TextBox emailTextBox = new TextBox();
        Label usernameLabel = new Label("username: ");
        Label loginLabel = new Label("login: ");
        Label passwordLabel = new Label("password: ");
        Label emailLabel = new Label("email: ");
        Button createUserButton = new Button("Tilføj user");

        createUserButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                User user = new User(loginTextBox.getText(),usernameTextBox.getText(),passwordTextBox.getText(), emailTextBox.getText());
                userService.createUser(user,new AsyncCallback<String>()  {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("Error:" + caught.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        Window.alert("Bruger " + usernameTextBox.getText() + " tilføjet.");
                        if(showUserPanel != null) {
                            showUserPanel.update();
                        }
                        usernameTextBox.setText("");
                        loginTextBox.setText("");
                        passwordTextBox.setText("");
                        emailTextBox.setText("");



                    }
                });


            }
        });

        createUserPanel.add(usernameLabel);
        createUserPanel.add(usernameTextBox);
        createUserPanel.add(loginLabel);
        createUserPanel.add(loginTextBox);

        createUserPanel.add(passwordLabel);
        createUserPanel.add(passwordTextBox);
        createUserPanel.add(emailLabel);
        createUserPanel.add(emailTextBox);

        createUserPanel.add(createUserButton);
        add(createUserPanel);

    }

}
