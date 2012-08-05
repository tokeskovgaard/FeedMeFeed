package dk.tokebroedsted.feedsetup.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import dk.tokebroedsted.commons.client.models.FeedGWT;

import java.util.List;

public class OwnedFeedsList extends FlowPanel {

    public OwnedFeedsList() {
        setStyleName("owned-feeds-list");
    }

    public void buildList(List<FeedGWT> feedGWTs) {
        clear();

        for (final FeedGWT feedGWT : feedGWTs) {
            Label listLabel = new Label(feedGWT.getTitle());
            listLabel.setStyleName("owned-feeds-row");
            listLabel.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    FeedSetupEntryPoint.setupView.displayFeed(feedGWT);
                }
            });
            add(listLabel);
        }
    }
}
