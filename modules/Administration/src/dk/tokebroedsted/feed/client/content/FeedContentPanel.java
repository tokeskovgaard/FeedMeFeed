package dk.tokebroedsted.feed.client.content;

import com.google.gwt.user.client.ui.FlowPanel;
import dk.tokebroedsted.feed.client.FeedServiceAsync;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.FeedItemGWT;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 18-07-12
 * Time: 15:05
 * To change this template use File | Settings | File Templates.
 */
public class FeedContentPanel extends FlowPanel {
    private FeedGWT currentFeed;
    private FeedServiceAsync feedService;

    public FeedContentPanel(FeedServiceAsync feedService) {
        this.feedService = feedService;
        setStyleName("feed-content-panel");
    }

    public void renderFeed(FeedGWT feedGWT, List<FeedItemGWT> feedItemGWTs) {
        if (this.currentFeed != null && this.currentFeed.equals(feedGWT)) {
            //Dont render feed again since it is already rendered.
            return;
        }

        clear();

        this.currentFeed = feedGWT;

        for (FeedItemGWT feedItemGWT : feedItemGWTs) {
            FeedContentItem feedContentItem = new FeedContentItem(feedService, feedGWT, feedItemGWT);
            add(feedContentItem);
        }
    }
}
