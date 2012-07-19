package dk.tokebroedsted.feed.client.tabs;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import dk.tokebroedsted.commons.client.models.FeedGWT;

public class Tab extends FlowPanel {


    private boolean selected;
    private final FeedGWT feedGWT;

    public Tab(final TabPanel tabPanel, FeedGWT feedGWT) {
        this.feedGWT = feedGWT;

        setStyleName("tab");
        getElement().setInnerHTML(feedGWT.getTitle());

        addDomHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (!selected)
                    tabPanel.updateSelectedTab(Tab.this);
            }
        }, ClickEvent.getType());
    }

    public void setSelected(boolean selected) {
        if (selected) {
            setStyleName("tab selected");
        } else {
            setStyleName("tab");
        }

        this.selected = selected;
    }

    public FeedGWT getFeed() {
        return feedGWT;
    }
}
