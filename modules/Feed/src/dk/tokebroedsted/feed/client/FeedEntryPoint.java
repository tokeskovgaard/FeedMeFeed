package dk.tokebroedsted.feed.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import dk.tokebroedsted.feed.client.commons.DefaultCallback;
import dk.tokebroedsted.feed.client.content.FeedContentPanel;
import dk.tokebroedsted.feed.client.model.FeedGWT;
import dk.tokebroedsted.feed.client.model.FeedItemGWT;
import dk.tokebroedsted.feed.client.tabs.TabPanel;

import java.util.List;

public class FeedEntryPoint implements EntryPoint {

    private RootPanel rootPanel;
    private TabPanel tabPanel;
    private FeedContentPanel feedContentPanel;

    @Override
    public void onModuleLoad() {
        rootPanel = RootPanel.get("gwt_feed");

        final FeedServiceAsync feedService = FeedService.App.getInstance();

        tabPanel = new TabPanel();
        rootPanel.add(tabPanel);

        ControlPanel controlPanel = new ControlPanel(feedService);
        rootPanel.add(controlPanel);

        feedContentPanel = new FeedContentPanel(feedService);
        rootPanel.add(feedContentPanel);

        fillTabPanel(feedService);
    }

    private void fillTabPanel(final FeedServiceAsync feedService) {
        feedService.getUsersFeeds(new DefaultCallback<List<FeedGWT>>() {
            @Override
            public void onSuccess(List<FeedGWT> result) {
                for (FeedGWT feedGWT : result) {
                    tabPanel.addFeedAsTab(feedGWT);
                }

                fillFeedContent(feedService);
            }
        });
    }

    private void fillFeedContent(FeedServiceAsync feedService) {
        feedService.getFeedItems(tabPanel.getSelectedFeed(), new DefaultCallback<List<FeedItemGWT>>() {
            @Override
            public void onSuccess(List<FeedItemGWT> result) {
                feedContentPanel.renderFeed(tabPanel.getSelectedFeed(), result);
            }
        });
    }
}
