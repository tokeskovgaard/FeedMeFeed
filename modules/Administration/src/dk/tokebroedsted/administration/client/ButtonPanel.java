package dk.tokebroedsted.administration.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import dk.tokebroedsted.commons.client.models.FeedGWT;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 19-07-12
 * Time: 22:04
 * To change this template use File | Settings | File Templates.
 */
public class ButtonPanel extends FlowPanel {

    public ButtonPanel() {
        setStyleName("button-panel");

        Label newButton = new Label("Opret ny");
        newButton.setStyleName("link-label");
        newButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                AdministrationEntryPoint.setupView.displayFeed(new FeedGWT());
            }
        });
        add(newButton);
    }
}
