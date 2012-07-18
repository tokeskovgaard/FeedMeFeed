package dk.tokebroedsted.administration.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 17-07-12
 * Time: 19:24
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractItemEditDialog extends DialogBox {

    protected void buildDialog() {
        setStyleName("edit-item-dialog");
        setGlassEnabled(true);
        center();

        FlowPanel content = new FlowPanel();
        add(content);

        renderBody(content);

        FlowPanel buttonPanel = new FlowPanel();
        content.add(buttonPanel);
        Button saveButton = new Button("Gem");
        saveButton.addClickHandler(createSaveItemClickHandler());
        buttonPanel.add(saveButton);

        Button cancelButton = new Button("Annuller");
        cancelButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                hide();
            }
        });
        buttonPanel.add(cancelButton);
    }

    protected abstract void renderBody(FlowPanel content);

    protected abstract ClickHandler createSaveItemClickHandler();
}
