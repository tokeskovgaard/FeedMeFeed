package dk.tokebroedsted.feedsetup.client.userinput;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.feedsetup.client.FeedSetupView;
import dk.tokebroedsted.feedsetup.client.view.AbstractItemEditDialog;
import dk.tokebroedsted.commons.client.models.InputGWT;

public class EditFeedInputEditDialog extends AbstractItemEditDialog {

    private FeedSetupView feedSetupView;
    private TextBox nameInput;
    private ListBox typeListBox;

    public EditFeedInputEditDialog(final FeedSetupView feedSetupView) {
        this.feedSetupView = feedSetupView;
        buildDialog();
    }

    @Override
    protected void renderBody(FlowPanel content) {
        content.add(new Label("Navn:"));
        nameInput = new TextBox();
        content.add(nameInput);

        content.add(new Label("Type:"));
        typeListBox = new ListBox();
        for (InputGWT.Type type : InputGWT.Type.values()) {
            typeListBox.addItem(type.name());
        }
        content.add(typeListBox);
    }

    @Override
    protected ClickHandler createSaveItemClickHandler() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                InputGWT feedInput = new InputGWT();
                feedInput.setName(nameInput.getValue());
                int selectedIndex = typeListBox.getSelectedIndex();
                feedInput.setType(InputGWT.Type.valueOf(typeListBox.getValue(selectedIndex)));

                feedSetupView.addInputItem(feedInput);
                hide();
            }
        };
    }
}
