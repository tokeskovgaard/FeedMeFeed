package dk.tokebroedsted.feedsetup.client.navigation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import dk.tokebroedsted.commons.client.DefaultCallback;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.feedsetup.client.FeedSetupEntryPoint;
import dk.tokebroedsted.feedsetup.client.display.FeedListItemView;
import dk.tokebroedsted.feedsetup.client.edit.FeedEditView;

import java.util.List;


public class NavigationPanel extends Composite {

    interface NavigationPanelUiBinder extends UiBinder<HTMLPanel, NavigationPanel> {
    }

    private static NavigationPanelUiBinder ourUiBinder = GWT.create(NavigationPanelUiBinder.class);

    public NavigationPanel() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @UiHandler("showFeeds")
    void showFeeds(ClickEvent e) {
        FeedSetupEntryPoint.rootContentPanel.clear();
        FeedSetupEntryPoint.feedService.getOwnedFeeds(new DefaultCallback<List<FeedGWT>>() {
            @Override
            public void onSuccess(List<FeedGWT> result) {
                for (FeedGWT feedGWT : result) {
                    FeedSetupEntryPoint.rootContentPanel.add(new FeedListItemView(feedGWT));
                }
            }
        });
    }

    @UiHandler("createFeed")
    void createFeed(ClickEvent e) {
        FeedSetupEntryPoint.rootContentPanel.clear();
        FeedSetupEntryPoint.rootContentPanel.add(new FeedEditView(new FeedGWT()));
    }
}