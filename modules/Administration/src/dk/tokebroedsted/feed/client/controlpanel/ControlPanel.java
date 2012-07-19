package dk.tokebroedsted.feed.client.controlpanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.commons.client.DefaultCallback;
import dk.tokebroedsted.commons.client.models.FeedItemGWT;
import dk.tokebroedsted.commons.client.models.InputItemGWT;
import dk.tokebroedsted.feed.client.FeedServiceAsync;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.InputGWT;
import dk.tokebroedsted.feed.client.tabs.*;
import dk.tokebroedsted.feed.client.tabs.TabPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 18-07-12
 * Time: 19:02
 * To change this template use File | Settings | File Templates.
 */
public class ControlPanel extends FlowPanel {


    private FeedServiceAsync feedService;
    private TabPanel tabPanel;

    public ControlPanel(FeedServiceAsync feedService, TabPanel tabPanel) {
        this.feedService = feedService;
        this.tabPanel = tabPanel;
        setStyleName("control-panel");

        add(createAddNewLink());

    }

    private Widget createAddNewLink() {
        Label addNew = new Label("Tilføj nyt item");
        addNew.setStyleName("link-label");
        addNew.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                EditFeedItemDialog dialog = new EditFeedItemDialog(feedService, tabPanel.getSelectedFeed());
                dialog.show();
            }
        });

        return addNew;
    }
}
