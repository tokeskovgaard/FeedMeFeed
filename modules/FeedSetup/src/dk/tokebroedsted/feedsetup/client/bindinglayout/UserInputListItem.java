package dk.tokebroedsted.feedsetup.client.bindinglayout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import dk.tokebroedsted.commons.client.models.InputGWT;

public class UserInputListItem extends Composite {
    private Command deleteCommand;

    interface UserInputListItemUiBinder extends UiBinder<FlowPanel, UserInputListItem> {
    }

    private static UserInputListItemUiBinder ourUiBinder = GWT.create(UserInputListItemUiBinder.class);

    private InputGWT inputGWT;

    @UiField InlineLabel name;

    public UserInputListItem(InputGWT inputGWT) {
        initWidget(ourUiBinder.createAndBindUi(this));

        this.inputGWT = inputGWT;
        name.setText(inputGWT.getName());
    }

    public void setDeleteCommand(Command deleteCommand) {
        this.deleteCommand = deleteCommand;
    }

    @UiHandler("deleteImage")
    void delete(ClickEvent e) {
        deleteCommand.execute();
    }

    @UiHandler("name")
    void editInputItem(ClickEvent e) {
        UserInputEditDialog inputEditDialog = new UserInputEditDialog(inputGWT, new Command() {
            @Override
            public void execute() {
            }
        });
        inputEditDialog.show();
    }
}