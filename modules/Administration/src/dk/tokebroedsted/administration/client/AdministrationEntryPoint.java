package dk.tokebroedsted.administration.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import dk.tokebroedsted.commons.client.DefaultCallback;

public class AdministrationEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        final RootPanel rootPanel = RootPanel.get("gwt_administration");
        AdministrationServiceAsync administrationService = AdministrationService.App.getInstance();

        FeedSetupView feedSetupView = new FeedSetupView(administrationService);
        rootPanel.add(feedSetupView);
    }
}
