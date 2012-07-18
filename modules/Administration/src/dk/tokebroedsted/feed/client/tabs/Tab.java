package dk.tokebroedsted.feed.client.tabs;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import dk.tokebroedsted.commons.client.models.FeedGWT;

public class Tab extends FlowPanel {


    private boolean selected;
    private final TabPanel tabPanel;
    private final FeedGWT feedGWT;

    public Tab(TabPanel tabPanel, FeedGWT feedGWT) {
        this.tabPanel = tabPanel;
        this.feedGWT = feedGWT;

        setStyleName("tab");
        getElement().setInnerHTML(feedGWT.getTitle());
    }

    public void setSelected(boolean selected) {
        this.selected = selected;

        if (selected) {
            setStyleName("tab selected");
        } else {
            setStyleName("tab");
            addHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    tabPanel.updateSelectedTab(Tab.this);
                }
            }, ClickEvent.getType());
        }
    }

    public FeedGWT getFeed() {
        return feedGWT;
    }
}
