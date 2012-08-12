package dk.tokebroedsted.commons.client.preview;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.commons.client.fragment.fragments.Fragment;
import dk.tokebroedsted.commons.client.fragment.FragmentFactory;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.FeedItemGWT;

import java.util.LinkedList;
import java.util.List;

public class FeedWidget extends Composite {
    interface FeedWidgetUiBinder extends UiBinder<HTMLPanel, FeedWidget> {
    }

    private static FeedWidgetUiBinder ourUiBinder = GWT.create(FeedWidgetUiBinder.class);


    @UiField HTML css;
    @UiField FlowPanel feedItems;

    public FeedWidget() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void renderFeedItems(FeedGWT feedGWT, List<FeedItemGWT> feedItemGWTs) {
        css.setHTML("<style type=\"text/css\">" + feedGWT.getCss() + "</style>");

        LinkedList<Fragment> fragmentList = FragmentFactory.buildFragmentList(feedGWT);

        feedItems.clear();

        for (FeedItemGWT feedItemGWT : feedItemGWTs) {
            FeedItemWidget feedItemWidget = new FeedItemWidget(fragmentList, feedItemGWT);
            feedItems.add(feedItemWidget);
        }
    }
}