package dk.tokebroedsted.administration.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ProvidesResize;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 16-07-12
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
public class AdjustableItem extends FlowPanel {


    public AdjustableItem() {
        setStyleName("adjustable-item");
        add(new Label("Test"));

        addHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

            }
        }, ClickEvent.getType());
    }
}
