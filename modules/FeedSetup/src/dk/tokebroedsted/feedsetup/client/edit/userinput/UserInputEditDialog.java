package dk.tokebroedsted.feedsetup.client.edit.userinput;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import dk.tokebroedsted.commons.client.models.InputGWT;

public class UserInputEditDialog extends DialogBox {
    interface Binder extends UiBinder<Widget, UserInputEditDialog> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    private final InputGWT inputGWT;
    private Command savedItemCommand;

    @UiField TextBox name;
    @UiField ListBox type;

    public UserInputEditDialog(InputGWT inputGWT, Command savedItemCommand) {
        setWidget(binder.createAndBindUi(this));
        this.savedItemCommand = savedItemCommand;

        this.inputGWT = inputGWT;

        int count = 0;
        int selectedIndex = 0;
        for (InputGWT.Type typeToAdd : InputGWT.Type.values()) {
            type.addItem(typeToAdd.name());
            if (typeToAdd.equals(inputGWT.getType())) {
                selectedIndex = count;
            }
            count++;
        }
        type.setSelectedIndex(selectedIndex);
        name.setValue(inputGWT.getName());

        center();
    }

    @UiHandler("save")
    void saveItem(ClickEvent e) {
        inputGWT.setName(name.getValue());
        String value = type.getValue(type.getSelectedIndex());
        inputGWT.setType(InputGWT.Type.valueOf(value));

        savedItemCommand.execute();
        this.hide();
    }

    @UiHandler("cancel")
    void cancel(ClickEvent e) {
        this.hide();
    }
}
