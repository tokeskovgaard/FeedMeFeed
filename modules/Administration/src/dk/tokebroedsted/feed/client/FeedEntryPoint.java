package dk.tokebroedsted.feed.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import dk.tokebroedsted.commons.client.CustomUncaughtExceptionHandler;
import dk.tokebroedsted.commons.client.DefaultCallback;
import dk.tokebroedsted.feed.client.content.FeedContentPanel;
import dk.tokebroedsted.feed.client.controlpanel.ControlPanel;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.FeedItemGWT;
import dk.tokebroedsted.feed.client.tabs.TabPanel;

import java.util.List;

public class FeedEntryPoint implements EntryPoint {

    private RootPanel rootPanel;
    private TabPanel tabPanel;
    private FeedContentPanel feedContentPanel;
    private ControlPanel controlPanel;

    @Override
    public void onModuleLoad() {
        GWT.setUncaughtExceptionHandler(new CustomUncaughtExceptionHandler());

        rootPanel = RootPanel.get("gwt_feed");

        final FeedServiceAsync feedService = FeedService.App.getInstance();

        tabPanel = new TabPanel(this);
        rootPanel.add(tabPanel);

        controlPanel = new ControlPanel(feedService, tabPanel);
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

                fillFeedRelevantContent(feedService, tabPanel.getSelectedFeed());
            }
        });
    }

    private void fillFeedRelevantContent(FeedServiceAsync feedService, final FeedGWT selectedFeed) {
        feedService.getFeedItems(selectedFeed, new DefaultCallback<List<FeedItemGWT>>() {
            @Override
            public void onSuccess(List<FeedItemGWT> result) {
                feedContentPanel.renderFeed(tabPanel.getSelectedFeed(), result);
            }
        });
    }
}
