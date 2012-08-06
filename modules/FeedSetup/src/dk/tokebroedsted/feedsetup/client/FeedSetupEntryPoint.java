package dk.tokebroedsted.feedsetup.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import dk.tokebroedsted.commons.client.CustomUncaughtExceptionHandler;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.feedsetup.client.bindinglayout.FeedSetupLayoutView;

public class FeedSetupEntryPoint implements EntryPoint {

    public static FeedSetupView setupView;

    @Override
    public void onModuleLoad() {
        GWT.setUncaughtExceptionHandler(new CustomUncaughtExceptionHandler());

        final RootPanel rootPanel = RootPanel.get("gwt_FeedSetup");
        FeedSetupLayoutView feedSetupLayoutView = new FeedSetupLayoutView(new FeedGWT());
        rootPanel.add(feedSetupLayoutView);
    }
}
