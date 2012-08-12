package dk.tokebroedsted.feedsetup.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import dk.tokebroedsted.commons.client.CustomUncaughtExceptionHandler;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.feedsetup.client.edit.FeedEditView;
import dk.tokebroedsted.feedsetup.client.navigation.NavigationPanel;

public class FeedSetupEntryPoint implements EntryPoint {

    public static FeedSetupServiceAsync feedService;
    public static RootPanel rootContentPanel;
    public static RootPanel rootNavigationPanel;

    @Override
    public void onModuleLoad() {
        GWT.setUncaughtExceptionHandler(new CustomUncaughtExceptionHandler());

        feedService = FeedSetupService.App.getInstance();
        rootContentPanel = RootPanel.get("gwt_content");
        rootNavigationPanel = RootPanel.get("gwt_navigation");

        FeedEditView feedEditView = new FeedEditView(new FeedGWT());
        rootContentPanel.add(feedEditView);

        NavigationPanel navigationPanel = new NavigationPanel();
        rootNavigationPanel.add(navigationPanel);
    }
}
