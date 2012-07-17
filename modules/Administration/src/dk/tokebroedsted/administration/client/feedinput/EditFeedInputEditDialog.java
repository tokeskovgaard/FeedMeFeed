package dk.tokebroedsted.administration.client.feedinput;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.administration.client.FeedSetupView;
import dk.tokebroedsted.administration.client.model.FeedInput;
import dk.tokebroedsted.administration.client.view.AbstractItemEditDialog;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 17-07-12
 * Time: 19:20
 * To change this template use File | Settings | File Templates.
 */
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
        for (FeedInput.Type type : FeedInput.Type.values()) {
            typeListBox.addItem(type.name());
        }
        content.add(typeListBox);
    }

    @Override
    protected ClickHandler createSaveItemClickHandler() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                FeedInput feedInput = new FeedInput();
                feedInput.setName(nameInput.getValue());

                int selectedIndex = typeListBox.getSelectedIndex();
                feedInput.setType(typeListBox.getValue(selectedIndex));

                feedSetupView.addInputItem(feedInput);
                hide();
            }
        };
    }
}
