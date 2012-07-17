package dk.tokebroedsted.administration.client.view;

import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.administration.client.model.CalculationItem;
import dk.tokebroedsted.administration.client.model.FeedItem;
import dk.tokebroedsted.administration.client.model.InputItem;
import dk.tokebroedsted.administration.client.model.QuestionItem;
import dk.tokebroedsted.administration.client.pagesetup.HTMLSetup;

import java.util.ArrayList;

public class FeedItemSetup extends FlowPanel {
    private ArrayList<InputItem> inputItems = new ArrayList<InputItem>();
    ArrayList<QuestionItem> questionItems = new ArrayList<QuestionItem>();
    ArrayList<CalculationItem> calculationItems = new ArrayList<CalculationItem>();

    public FeedItem feedItem;
    public PreviewView previewView;
    private HTMLSetup htmlSetup;
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


        FlowPanel leftPanel = new FlowPanel();
        leftPanel.setStyleName("left-panel");
        add(leftPanel);

        settingsPanel = new SettingsPanel(this);
        leftPanel.add(settingsPanel);

        inputItemView = new InputItemView(this);
        leftPanel.add(inputItemView);

        questionItemView = new QuestionItemView(this);
        leftPanel.add(questionItemView);

        calculationItemView = new CalculationItemView(this);
        leftPanel.add(calculationItemView);

        FlowPanel middlePanel = new FlowPanel();
        middlePanel.setStyleName("middle-panel");
        add(middlePanel);

        htmlSetup = new HTMLSetup(this);
        middlePanel.add(htmlSetup);

        FlowPanel rightPanel = new FlowPanel();
        rightPanel.setStyleName("right-panel");
        add(rightPanel);

        previewView = new PreviewView(this);
        rightPanel.add(previewView);

        updateViews();

    }

    public void updateViews() {
        previewView.updateView();
        settingsPanel.updateView();
        inputItemView.updateView();
        questionItemView.updateView();
        calculationItemView.updateView();
    }

    void addInputItem(InputItem item){
        inputItems.add(item);
    }


    public ArrayList<InputItem> getInputItems() {
        return inputItems;
    }
}
