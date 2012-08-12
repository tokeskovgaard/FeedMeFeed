package dk.tokebroedsted.feed.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import dk.tokebroedsted.commons.client.CustomUncaughtExceptionHandler;
import dk.tokebroedsted.commons.client.DefaultCallback;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.FeedItemGWT;
import dk.tokebroedsted.commons.client.preview.FeedWidget;
import dk.tokebroedsted.feed.client.controlpanel.ControlPanel;
import dk.tokebroedsted.feed.client.tabs.Tab;

import java.util.List;

public class FeedEntryPoint implements EntryPoint {

    public static FeedServiceAsync feedService;

    public static FeedWidget feedWidget;
    public static FeedGWT renderedFeed;

    @Override
    public void onModuleLoad() {
        GWT.setUncaughtExceptionHandler(new CustomUncaughtExceptionHandler());

        feedService = FeedService.App.getInstance();

        final RootPanel rootNavigationPanel = RootPanel.get("gwt_navigation");
        RootPanel rootContentPanel = RootPanel.get("gwt_content");

        rootContentPanel.add(new ControlPanel());
        feedWidget = new FeedWidget();
        rootContentPanel.add(feedWidget);


        feedService.getUsersFeeds(new DefaultCallback<List<FeedGWT>>() {
            @Override
            public void onSuccess(List<FeedGWT> result) {
                for (FeedGWT feedGWT : result) {
                    Tab tab = new Tab(feedGWT);
                    rootNavigationPanel.add(tab);
                }
                if (result.size() > 0) {
                    renderFeed(result.get(0));
                }
            }
        });
    }

    public static void renderFeed(final FeedGWT feedGWT) {
        renderedFeed = feedGWT;
        feedService.getFeedItems(feedGWT, new DefaultCallback<List<FeedItemGWT>>() {
            @Override
            public void onSuccess(List<FeedItemGWT> result) {
                feedWidget.renderFeedItems(feedGWT, result);
            }
        });
    }
}
