package dk.tokebroedsted.feed.client.content;

import com.google.gwt.user.client.ui.FlowPanel;
import dk.tokebroedsted.feed.client.FeedServiceAsync;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.FeedItemGWT;

import java.util.List;

public class FeedContentPanel extends FlowPanel {
    private FeedGWT currentFeed;
    private FeedServiceAsync feedService;

    public FeedContentPanel(FeedServiceAsync feedService) {
        this.feedService = feedService;
        setStyleName("feed-content-panel");
    }

    public void renderFeed(FeedGWT feedGWT) {
        if (currentFeed != null && currentFeed.equals(feedGWT)) {
            //Dont render feed again since it is already rendered.
            return;
        }
        currentFeed = feedGWT;

        clear();
        getElement().setInnerHTML("<style type=\"text/css\">" + currentFeed.getCss() + "</style>");


        for (FeedItemGWT feedItemGWT : currentFeed.getFeedItems()) {
            FeedContentItem feedContentItem = new FeedContentItem(feedService, feedGWT, feedItemGWT);
            add(feedContentItem);
        }
    }
}
