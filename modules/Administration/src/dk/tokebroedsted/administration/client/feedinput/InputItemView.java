package dk.tokebroedsted.administration.client.feedinput;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.administration.client.FeedSetupView;
import dk.tokebroedsted.administration.client.model.FeedInput;

public class InputItemView extends FlowPanel {

    private FeedSetupView feedSetupView;

    public InputItemView(FeedSetupView feedSetupView) {
        this.feedSetupView = feedSetupView;
        setStyleName("item-list-view");
    }

    public void updateView() {
        clear();

        Label titel = new Label("Brugerinputs");
        titel.addStyleName("titel");
        add(titel);
        for (FeedInput feedInput : feedSetupView.getFeed().feedInputList) {
            Label listItem = new Label(feedInput.getDisplayName());
            listItem.addStyleName("list-item");
            add(listItem);
        }
        Button userInput = new Button("Tilføj nyt bruger input");
        userInput.setStyleName("add-new-button");
        userInput.addClickHandler(createClickHandler());
        add(userInput);
    }

    private ClickHandler createClickHandler() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                EditFeedInputEditDialog dialog = new EditFeedInputEditDialog(feedSetupView);
                dialog.show();
            }
        };
    }
}
