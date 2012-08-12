package dk.tokebroedsted.feedsetup.client.display;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import dk.tokebroedsted.commons.client.DefaultCallback;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.feedsetup.client.FeedSetupEntryPoint;
import dk.tokebroedsted.feedsetup.client.edit.FeedEditView;

public class FeedListItemView extends Composite {
    interface FeedListUiBinder extends UiBinder<FlowPanel, FeedListItemView> {
    }

    private static FeedListUiBinder ourUiBinder = GWT.create(FeedListUiBinder.class);

    private FeedGWT feedGWT;

    @UiField Label name;
    @UiField Label user;
    @UiField Label subscribers;

    public FeedListItemView(FeedGWT feedGWT) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.feedGWT = feedGWT;

        name.setText(feedGWT.getTitle());
        user.setText(feedGWT.getOwner());
    }

    @UiHandler("name")
    void editFeed(ClickEvent event) {
        FeedSetupEntryPoint.rootContentPanel.clear();
        FeedSetupEntryPoint.rootContentPanel.add(new FeedEditView(feedGWT));
    }

    @UiHandler("delete")
    void delete(ClickEvent e) {
        boolean confirmed = Window.confirm("Er du sikker på du vil slette " + feedGWT.getTitle());
        if (confirmed)
            FeedSetupEntryPoint.feedService.deleteFeed(feedGWT, new DefaultCallback<Void>() {
                @Override
                public void onSuccess(Void result) {
                    FeedListItemView.this.removeFromParent();
                }
            });
    }
}