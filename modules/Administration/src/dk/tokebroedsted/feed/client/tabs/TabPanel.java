package dk.tokebroedsted.feed.client.tabs;

import com.google.gwt.user.client.ui.FlowPanel;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.feed.client.FeedEntryPoint;


public class TabPanel extends FlowPanel {

    private Tab selectedTab = null;
    private FeedEntryPoint feedEntryPoint;

    public TabPanel(FeedEntryPoint feedEntryPoint) {
        this.feedEntryPoint = feedEntryPoint;
        setStyleName("tab-panel");
    }

    public void addFeedAsTab(FeedGWT feedGWT) {
        Tab tab = new Tab(this, feedGWT);

        if (selectedTab == null) {
            updateSelectedTab(tab);
        }

        add(tab);
    }

    public void updateSelectedTab(Tab selectedTab) {
        if (this.selectedTab != null) {
            this.selectedTab.setSelected(false);
        }
        selectedTab.setSelected(true);

        this.selectedTab = selectedTab;
    }

    public FeedGWT getSelectedFeed() {
        return selectedTab.getFeed();
    }
}
