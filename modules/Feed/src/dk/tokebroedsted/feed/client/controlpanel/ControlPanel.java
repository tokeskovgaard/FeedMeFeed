package dk.tokebroedsted.feed.client.controlpanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.feed.client.FeedEntryPoint;

public class ControlPanel extends FlowPanel {


    public ControlPanel() {
        setStyleName("control-panel");
        add(createAddNewLink());
    }

    private Widget createAddNewLink() {
        Label addNew = new Label("Tilføj nyt item");
        addNew.setStyleName("link-label");
        addNew.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                EditFeedItemDialog dialog = new EditFeedItemDialog(FeedEntryPoint.feedService, FeedEntryPoint.renderedFeed);
                dialog.show();
            }
        });

        return addNew;
    }
}
