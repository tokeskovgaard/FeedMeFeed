package dk.tokebroedsted.feed.client.tabs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.feed.client.FeedEntryPoint;

public class Tab extends Composite {
    interface TabbUiBinder extends UiBinder<Label, Tab> {
    }

    private static TabbUiBinder ourUiBinder = GWT.create(TabbUiBinder.class);

    private FeedGWT feedGWT;

    @UiField Label feedTitle;

    public Tab(FeedGWT feedGWT) {
        this.feedGWT = feedGWT;
        initWidget(ourUiBinder.createAndBindUi(this));
        feedTitle.setText(feedGWT.getTitle());
    }

    @UiHandler("feedTitle")
    void selected(ClickEvent event) {
        FeedEntryPoint.renderFeed(feedGWT);
    }
}