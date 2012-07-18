package dk.tokebroedsted.administration.client.feedinput;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.administration.client.FeedSetupView;
import dk.tokebroedsted.commons.client.models.InputGWT;

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
        for (InputGWT feedInput : feedSetupView.getFeed().getInputs()) {
            Label listItem = new Label(feedInput.getName() + " - " + feedInput.getVariabelId());
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
