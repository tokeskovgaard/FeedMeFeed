package dk.tokebroedsted.feedsetup.client.view;

import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.feedsetup.client.FeedSetupView;

public class PreviewView extends AbsolutePanel {

    private FeedSetupView feedItemSetup;

    public PreviewView(FeedSetupView feedItemSetup) {
        this.feedItemSetup = feedItemSetup;
        setStyleName("feed-item-preview");
    }

    public void updateView() {
        setHeight(200 + "px");
        setWidth(300 + "px");
        getElement().getStyle().setBackgroundColor("#FFF");

    }

    public void updatePreview(HTML html) {
        this.clear();
        add(html);
    }

    @Override
    public void setSize(String width, String height) {
        super.setSize(width, height);
        feedItemSetup.updateViews();
    }
}
