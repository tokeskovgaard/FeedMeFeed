package dk.tokebroedsted.feedsetup.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import dk.tokebroedsted.commons.client.DefaultCallback;
import dk.tokebroedsted.commons.client.models.FeedGWT;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 05-08-12
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */
public class FeedList extends FlowPanel {

    private FeedSetupServiceAsync FeedSetupService;

    public FeedList(FeedSetupServiceAsync FeedSetupService) {
        this.FeedSetupService = FeedSetupService;
        setStyleName("feed-list");
    }

    public void updateFeedList() {
        clear();
        HTML headingElement = new HTML();
        headingElement.setHTML("<h3>Oprettede feeds</h3>");
        add(headingElement);
        FeedSetupService.getOwnedFeeds(new DefaultCallback<List<FeedGWT>>() {
            @Override
            public void onSuccess(List<FeedGWT> feedGWTs) {
                for (final FeedGWT feedGWT : feedGWTs) {
                    add(createListItem(feedGWT));
                }
            }
        });
    }

    private Widget createListItem(final FeedGWT feedGWT) {
        Label label = new Label(feedGWT.getTitle());
        label.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                FeedSetupService.deleteFeed(feedGWT, new DefaultCallback<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        updateFeedList();
                    }
                });
            }
        });
        return label;
    }
}
