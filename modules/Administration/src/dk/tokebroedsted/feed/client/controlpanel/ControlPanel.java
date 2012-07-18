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


    private FeedGWT selectedFeed;
    private FeedServiceAsync feedService;

    public ControlPanel(FeedServiceAsync feedService) {
        this.feedService = feedService;
        setStyleName("control-panel");

        add(createAddNewLink());

    }

    private Widget createAddNewLink() {
        Label addNew = new Label("Tilføj nyt item");
        addNew.setStyleName("link-label");
        addNew.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final DialogBox dialog = new DialogBox();
                dialog.center();
                dialog.setStyleName("new-feed-item-dialog");

                dialog.add(new Label(selectedFeed.getTitle()));

                final List<InputItemGWT> inputItems = new ArrayList<InputItemGWT>();
                for (InputGWT inputGWT : selectedFeed.getInputs()) {
                    FlowPanel inputPanel = new FlowPanel();
                    InlineLabel label = new InlineLabel(inputGWT.getName());
                    label.setStyleName("name");
                    inputPanel.add(label);

                    InputGWT.Type type = inputGWT.getType();
                    switch (type) {
                        case string:
                            final TextBox input = new TextBox();
                            input.setStyleName("input");
                            final InputItemGWT inputItemGWT = new InputItemGWT(inputGWT);
                            inputItems.add(inputItemGWT);

                            input.addKeyUpHandler(new KeyUpHandler() {
                                @Override
                                public void onKeyUp(KeyUpEvent event) {
                                    inputItemGWT.setValue(input.getValue());
                                }
                            });
                            inputPanel.add(input);
                            break;
                        default:
                            throw new RuntimeException("Type has not been implemented! - " + type.toString());
                    }

                    dialog.add(inputPanel);
                }

                Label save = new Label("Gem");
                dialog.add(save);
                save.setStyleName("link-label");
                save.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {

                        FeedItemGWT feedItemGWT = new FeedItemGWT(selectedFeed);
                        feedItemGWT.setInputItems(inputItems);

                        feedService.addFeedItem(feedItemGWT, new DefaultCallback<Boolean>() {
                            @Override
                            public void onSuccess(Boolean result) {
                                if (Boolean.TRUE.equals(result)) {
                                    dialog.hide();
                                } else {
                                    Window.alert("Kunne ikke gemme dit feed.");
                                }
                            }
                        });


                        dialog.hide();
                    }
                });

                Label cancel = new Label("Annuller");
                dialog.add(cancel);
                cancel.setStyleName("link-label");
                cancel.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        dialog.hide();
                    }
                });

                dialog.show();
            }
        });

        return addNew;
    }

    public void setSelectedFeed(FeedGWT selectedFeed) {
        this.selectedFeed = selectedFeed;
    }
}
