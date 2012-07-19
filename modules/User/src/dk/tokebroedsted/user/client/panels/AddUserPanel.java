package dk.tokebroedsted.user.client.panels;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.commons.client.models.UserGWT;
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
        final PasswordTextBox passwordTextBox = new PasswordTextBox();
        final TextBox emailTextBox = new TextBox();
        Label usernameLabel = new Label("username: ");
        Label loginLabel = new Label("login: ");
        Label passwordLabel = new Label("password: ");
        Label emailLabel = new Label("email: ");
        Button createUserButton = new Button("Tilføj user");

        createUserButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                if(validateUserInput(usernameTextBox, loginTextBox, passwordTextBox, emailTextBox)) {
                    UserGWT user = new UserGWT(loginTextBox.getText(),usernameTextBox.getText(), emailTextBox.getText(),passwordTextBox.getText());
                    userService.createUser(user,new AsyncCallback<String>()  {
                        @Override
                        public void onFailure(Throwable caught) {
                            Window.alert("Error:" + caught.getMessage());
                        }

                        @Override
                        public void onSuccess(String result) {
                            //Window.alert("Bruger " + usernameTextBox.getText() + " tilføjet.");
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
            }});

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

    private boolean validateUserInput(TextBox usernameT, TextBox loginnameT, TextBox passwordT, TextBox emailT) {
        boolean validated = true;
        String username = usernameT.getText();
        String loginname = loginnameT.getText();
        String password = passwordT.getText();
        String email = emailT.getText();

        if(!username.matches("(\\D|\\d| )*")) {
            usernameT.setText("Indtast ordentligt navn");
            validated = false;

        }
        if(!loginname.matches(("(\\D|\\d)*"))) {
            loginnameT.setText("Indtast ordentligt brugernavn");
            validated = false;
        }
        //TODO: Create rules for password
        if(!password.matches("(.){4,100}")) {
            passwordT.setText("Indtast ordentligt password");
            validated = false;
        }
        //TODO: Find regexp for password
        if(email.matches("")) {
            emailT.setText("Indtast ordentlig email");
            validated = false;
        }

        return validated;
    }

}
