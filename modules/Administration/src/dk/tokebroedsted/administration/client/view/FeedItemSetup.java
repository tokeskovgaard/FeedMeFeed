package dk.tokebroedsted.administration.client.view;

import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.administration.client.model.CalculationItem;
import dk.tokebroedsted.administration.client.model.FeedItem;
import dk.tokebroedsted.administration.client.model.InputItem;
import dk.tokebroedsted.administration.client.model.QuestionItem;

import java.util.ArrayList;

public class FeedItemSetup extends FlowPanel {
    ArrayList<InputItem> inputItems = new ArrayList<InputItem>();
    ArrayList<QuestionItem> questionItems = new ArrayList<QuestionItem>();
    ArrayList<CalculationItem> calculationItems = new ArrayList<CalculationItem>();

    FeedItem feedItem;
    private FeedItemPreview feedItemPreview;
    private SettingsPanel settingsPanel;
    private CalculationItemView calculationItemView;
    private InputItemView inputItemView;
    private QuestionItemView questionItemView;

    public FeedItemSetup() {
        feedItem = new FeedItem();
        this.setStyleName("feed-item-setup-view");

        setupView();
    }

    private void setupView() {
        this.clear();

        feedItemPreview = new FeedItemPreview(this);
        add(feedItemPreview);

        settingsPanel = new SettingsPanel(this);
        add(settingsPanel);

        inputItemView = new InputItemView(this);
        add(inputItemView);

        questionItemView = new QuestionItemView(this);
        add(questionItemView);

        calculationItemView = new CalculationItemView(this);
        add(calculationItemView);

        updateViews();

    }

    void updateViews() {
        feedItemPreview.updateView();
        settingsPanel.updateView();
        inputItemView.updateView();
        questionItemView.updateView();
        calculationItemView.updateView();
    }





}
