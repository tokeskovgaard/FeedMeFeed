package dk.tokebroedsted.administration.client.view;

import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.administration.client.model.InputItem;

public class PreviewView extends AbsolutePanel {

    private FeedItemSetup feedItemSetup;

    public PreviewView(FeedItemSetup feedItemSetup) {
        this.feedItemSetup = feedItemSetup;
        setStyleName("feed-item-preview");
    }

    void updateView() {
        setHeight(feedItemSetup.feedItem.getHeight() + "px");
        setWidth(feedItemSetup.feedItem.getWidth() + "px");
        getElement().getStyle().setBackgroundColor(feedItemSetup.feedItem.getColor());

    }

    public void updatePreview(HTML html){
        this.clear();
        add(html);
    }

    @Override
    public void setSize(String width, String height) {
        super.setSize(width, height);
        feedItemSetup.feedItem.setWidth(Integer.parseInt(width));
        feedItemSetup.feedItem.setHeight(Integer.parseInt(height));
        feedItemSetup.updateViews();
    }
}
