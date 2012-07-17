package dk.tokebroedsted.administration.client.view;

import com.google.gwt.user.client.ui.*;

public class PreviewView extends AbsolutePanel {

    private FeedItemSetup feedItemSetup;

    public PreviewView(FeedItemSetup feedItemSetup) {
        this.feedItemSetup = feedItemSetup;
        setStyleName("feed-item-preview");
    }

    void updateView() {
        setHeight(feedItemSetup.feed.getHeight() + "px");
        setWidth(feedItemSetup.feed.getWidth() + "px");
        getElement().getStyle().setBackgroundColor(feedItemSetup.feed.getColor());

    }

    public void updatePreview(HTML html){
        this.clear();
        add(html);
    }

    @Override
    public void setSize(String width, String height) {
        super.setSize(width, height);
        feedItemSetup.feed.setWidth(Integer.parseInt(width));
        feedItemSetup.feed.setHeight(Integer.parseInt(height));
        feedItemSetup.updateViews();
    }
}
