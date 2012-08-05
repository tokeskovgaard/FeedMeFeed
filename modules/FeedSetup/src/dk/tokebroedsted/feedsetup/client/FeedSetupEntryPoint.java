package dk.tokebroedsted.feedsetup.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import dk.tokebroedsted.commons.client.CustomUncaughtExceptionHandler;
import dk.tokebroedsted.commons.client.models.FeedGWT;

public class FeedSetupEntryPoint implements EntryPoint {

    public static FeedSetupView setupView;

    @Override
    public void onModuleLoad() {
        GWT.setUncaughtExceptionHandler(new CustomUncaughtExceptionHandler());

        final RootPanel rootPanel = RootPanel.get("gwt_FeedSetup");
        FeedSetupServiceAsync feedSetupService = FeedSetupService.App.getInstance();

        final FeedList feedList = new FeedList(feedSetupService);
        feedList.updateFeedList();
        rootPanel.add(feedList);

        setupView = new FeedSetupView(feedSetupService);
        rootPanel.add(setupView);
        setupView.displayFeed(new FeedGWT());
    }
}
