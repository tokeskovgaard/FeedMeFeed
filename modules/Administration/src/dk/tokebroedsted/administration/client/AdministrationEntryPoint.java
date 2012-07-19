package dk.tokebroedsted.administration.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import dk.tokebroedsted.commons.client.CustomUncaughtExceptionHandler;
import dk.tokebroedsted.commons.client.DefaultCallback;
import dk.tokebroedsted.commons.client.models.FeedGWT;

import java.util.List;

public class AdministrationEntryPoint implements EntryPoint {

    public static FeedSetupView setupView;

    @Override
    public void onModuleLoad() {
        GWT.setUncaughtExceptionHandler(new CustomUncaughtExceptionHandler());

        final RootPanel rootPanel = RootPanel.get("gwt_administration");

        AdministrationServiceAsync administrationService = AdministrationService.App.getInstance();

        rootPanel.add(new ButtonPanel());

        final OwnedFeedsList ownedFeedsList = new OwnedFeedsList();
        rootPanel.add(ownedFeedsList);
        administrationService.getOwnedFeeds(new DefaultCallback<List<FeedGWT>>() {
            @Override
            public void onSuccess(List<FeedGWT> result) {
                ownedFeedsList.buildList(result);
            }
        });

        setupView = new FeedSetupView(administrationService);
        rootPanel.add(setupView);
        setupView.displayFeed(new FeedGWT());
    }
}
