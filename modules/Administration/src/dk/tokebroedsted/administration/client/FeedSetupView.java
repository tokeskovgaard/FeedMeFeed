package dk.tokebroedsted.administration.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.administration.client.calculation.CalculationItemView;
import dk.tokebroedsted.administration.client.feedinput.InputItemView;
import dk.tokebroedsted.administration.client.model.*;
import dk.tokebroedsted.administration.client.pagesetup.HTMLSetup;
import dk.tokebroedsted.administration.client.question.QuestionItemView;
import dk.tokebroedsted.administration.client.view.*;

public class FeedSetupView extends FlowPanel {
    public Feed feed;

    public PreviewView previewView;
    private HTMLSetup htmlSetup;
    //    private SettingsPanel settingsPanel;
    private CalculationItemView calculationItemView;
    private InputItemView inputItemView;
    private QuestionItemView questionItemView;
    private AdministrationServiceAsync administrationService;

    public FeedSetupView(AdministrationServiceAsync administrationService) {
        this.administrationService = administrationService;
        feed = new Feed();
        this.setStyleName("feed-item-setup-view");

        setupView();
    }

    private void setupView() {
        this.clear();


        FlowPanel leftPanel = new FlowPanel();
        leftPanel.setStyleName("left-panel");
        add(leftPanel);

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


        Button saveButton = new Button("Gem");
        saveButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                administrationService.saveFeed(new AsyncCallback<String>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }

                    @Override
                    public void onSuccess(String result) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }
                });
            }
        });
        add(saveButton);

        updateViews();

    }

    public void updateViews() {
        previewView.updateView();
//        settingsPanel.updateView();
        inputItemView.updateView();
        questionItemView.updateView();
        calculationItemView.updateView();
    }

    public void addInputItem(FeedInput feedInput) {
        feed.feedInputList.add(feedInput);
        inputItemView.updateView();
    }


    public Feed getFeed() {
        return feed;
    }


    public void addQuestion(Question question) {
        feed.questions.add(question);
        questionItemView.updateView();
    }
}
