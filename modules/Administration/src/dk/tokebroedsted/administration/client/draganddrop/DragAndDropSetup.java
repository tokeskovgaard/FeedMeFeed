/*
package dk.tokebroedsted.administration.client.draganddrop;

import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.administration.client.draganddrop.AdjustableItems;
import dk.tokebroedsted.administration.client.model.InputItem;
import dk.tokebroedsted.administration.client.view.FeedItemSetup;

public class DragAndDropSetup extends AbsolutePanel {
    private enum Tab {preview, html, draganddrop}

    private FeedItemSetup feedItemSetup;

    public DragAndDropSetup(FeedItemSetup feedItemSetup) {
        this.feedItemSetup = feedItemSetup;
        setStyleName("feed-item-preview");
    }

    void updateView() {
        setHeight(feedItemSetup.feedItem.getHeight() + "px");
        setWidth(feedItemSetup.feedItem.getWidth() + "px");
        getElement().getStyle().setBackgroundColor(feedItemSetup.feedItem.getColor());

    }

    void addInputItemToPreview(InputItem inputItem){

        Widget draggableWidget;
        switch (inputItem.getType()){
            case string: draggableWidget = new Label(inputItem.getName()); break;
//            case image: draggableWidget = new Image(); break;
//            case number: draggableWidget = new Label(inputItem.getName()); break;
            default: throw new RuntimeException("A type should always be set!");
        }

        AdjustableItems adjustableItem = new AdjustableItems(draggableWidget);
        add(adjustableItem);
    }

    @Override
    public void setSize(String width, String height) {
        super.setSize(width, height);
        feedItemSetup.feedItem.setWidth(Integer.parseInt(width));
        feedItemSetup.feedItem.setHeight(Integer.parseInt(height));
        feedItemSetup.updateViews();
    }
}
*/
