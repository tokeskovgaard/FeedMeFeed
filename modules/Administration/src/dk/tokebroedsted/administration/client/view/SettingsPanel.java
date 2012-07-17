package dk.tokebroedsted.administration.client.view;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;
import dk.tokebroedsted.administration.client.FeedSetupView;

public class SettingsPanel extends FlowPanel {
    private FeedSetupView feedItemSetup;

    public SettingsPanel(FeedSetupView feedItemSetup) {
        this.feedItemSetup = feedItemSetup;
        setStyleName("settings-panel");
    }

    public void updateView() {
        clear();

        FlowPanel heightInputPanel = new FlowPanel();
        heightInputPanel.add(new InlineLabel("Højde: "));
        final TextBox heightInput = new TextBox();
        heightInput.setValue(String.valueOf(feedItemSetup.feed.getHeight()));
        heightInputPanel.add(heightInput);

        FlowPanel widthInputPanel = new FlowPanel();
        widthInputPanel.add(new InlineLabel("Bredde: "));
        final TextBox widthInput = new TextBox();
        widthInput.setValue(String.valueOf(feedItemSetup.feed.getWidth()));
        widthInputPanel.add(widthInput);

        FlowPanel colorInputPanel = new FlowPanel();
        colorInputPanel.add(new InlineLabel("Farve: "));
        final TextBox colorInput = new TextBox();
        colorInput.setValue(feedItemSetup.feed.getColor());
        colorInputPanel.add(colorInput);

        add(heightInputPanel);
        add(widthInputPanel);
        add(colorInputPanel);

        ChangeHandler changeHappenedHandler = new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                feedItemSetup.feed.setWidth(Integer.parseInt(widthInput.getValue()));
                feedItemSetup.feed.setHeight(Integer.parseInt(heightInput.getValue()));
                feedItemSetup.feed.setColor(colorInput.getValue());

                feedItemSetup.updateViews();
            }
        };

        widthInput.addChangeHandler(changeHappenedHandler);
        heightInput.addChangeHandler(changeHappenedHandler);
        colorInput.addChangeHandler(changeHappenedHandler);
    }
}
