package dk.tokebroedsted.administration.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.administration.client.model.InputItem;

public class InputItemView extends FlowPanel{

    private FeedItemSetup feedItemSetup;

    public InputItemView(FeedItemSetup feedItemSetup) {
        this.feedItemSetup = feedItemSetup;
        setStyleName("input-view");
    }

    void updateView() {
        clear();

        add(new Label("Brugerinputs"));
        for (InputItem inputItem : feedItemSetup.inputItems) {
            add(new Label(inputItem.getName()));
        }
        Button userInput = new Button("Tilføj nyt bruger input");
        userInput.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final DialogBox dialog = new DialogBox();
                dialog.setStyleName("new-input-item-dialog");
                dialog.setGlassEnabled(true);
                dialog.setModal(true);
                dialog.center();

                FlowPanel content = new FlowPanel();
                dialog.add(content);

                content.add(new Label("Navn:"));
                final TextBox nameInput = new TextBox();
                content.add(nameInput);

                content.add(new Label("Type:"));
                final ListBox typeListBox = new ListBox();
                for (InputItem.Type type : InputItem.Type.values()) {
                    typeListBox.addItem(type.name());
                }
                content.add(typeListBox);

                Button saveButton = new Button("Gem");
                content.add(saveButton);
                saveButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        InputItem inputItem = new InputItem();
                        inputItem.setName(nameInput.getValue());

                        int selectedIndex = typeListBox.getSelectedIndex();
                        inputItem.setType(typeListBox.getValue(selectedIndex));

                        feedItemSetup.inputItems.add(inputItem);
                        dialog.hide();
                        feedItemSetup.updateViews();
                    }
                });

                dialog.show();
            }
        });
        add(userInput);
    }
}
