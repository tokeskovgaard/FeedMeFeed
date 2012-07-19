package dk.tokebroedsted.feed.client.controlpanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.commons.client.DefaultCallback;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.FeedItemGWT;
import dk.tokebroedsted.commons.client.models.InputGWT;
import dk.tokebroedsted.commons.client.models.InputItemGWT;
import dk.tokebroedsted.feed.client.FeedServiceAsync;

import java.util.ArrayList;
import java.util.List;

public class EditFeedItemDialog extends DialogBox {

    private FeedGWT selectedFeed;
    private List<InputItemGWT> inputItems;
    private FeedServiceAsync feedService;

    public EditFeedItemDialog(FeedServiceAsync feedService, FeedGWT selectedFeed) {
        this.feedService = feedService;
        this.selectedFeed = selectedFeed;

        setStyleName("edit-feed-item-dialog");
        setGlassEnabled(true);
        center();


        buildDialog();
    }

    private void buildDialog() {

        FlowPanel content = new FlowPanel();
        add(content);
        content.add(getBody());

        FlowPanel buttonPanel = new FlowPanel();
        content.add(buttonPanel);
        Button saveButton = new Button("Gem");
        saveButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onSave();
            }
        });
        buttonPanel.add(saveButton);

        Button cancelButton = new Button("Annuller");
        cancelButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                hide();
            }
        });
        buttonPanel.add(cancelButton);

    }


    private Widget getBody() {

        FlowPanel dialogContent = new FlowPanel();

        dialogContent.add(new Label(selectedFeed.getTitle()));

        inputItems = new ArrayList<InputItemGWT>();
        for (InputGWT inputGWT : selectedFeed.getInputs()) {
            FlowPanel inputPanel = new FlowPanel();
            InlineLabel label = new InlineLabel(inputGWT.getName());
            label.setStyleName("name");
            inputPanel.add(label);

            InputGWT.Type type = inputGWT.getType();
            switch (type) {
                case string:
                    final TextBox input = new TextBox();
                    input.setStyleName("input");
                    final InputItemGWT inputItemGWT = new InputItemGWT(inputGWT);
                    inputItems.add(inputItemGWT);

                    input.addKeyUpHandler(new KeyUpHandler() {
                        @Override
                        public void onKeyUp(KeyUpEvent event) {
                            inputItemGWT.setValue(input.getValue());
                        }
                    });
                    inputPanel.add(input);
                    break;
                default:
                    throw new RuntimeException("Type has not been implemented! - " + type.toString());
            }

            dialogContent.add(inputPanel);
        }

        return dialogContent;
    }

    protected void onSave() {
        FeedItemGWT feedItemGWT = new FeedItemGWT(selectedFeed);
        feedItemGWT.setInputItems(inputItems);

        feedService.addFeedItem(feedItemGWT, new DefaultCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if (Boolean.TRUE.equals(result)) {
                    hide();
                } else {
                    Window.alert("Kunne ikke gemme dit feed.");
                }
            }
        });
        hide();
    }
}
