package dk.tokebroedsted.feedsetup.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.feedsetup.client.calculation.CalculationItemView;
import dk.tokebroedsted.feedsetup.client.userinput.InputItemView;
import dk.tokebroedsted.feedsetup.client.pagesetup.HTMLSetup;
import dk.tokebroedsted.feedsetup.client.question.QuestionItemView;
import dk.tokebroedsted.feedsetup.client.view.*;
import dk.tokebroedsted.commons.client.DefaultCallback;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.InputGWT;
import dk.tokebroedsted.commons.client.models.QuestionGWT;

public class FeedSetupView extends FlowPanel {
    public FeedGWT feed;

    public PreviewView previewView;
    private HTMLSetup htmlSetup;
    private CalculationItemView calculationItemView;
    private InputItemView inputItemView;
    private QuestionItemView questionItemView;
    private FeedSetupServiceAsync FeedSetupService;

    public FeedSetupView(FeedSetupServiceAsync FeedSetupService) {
        this.FeedSetupService = FeedSetupService;
        this.setStyleName("feed-item-setup-view");
    }

    public void displayFeed(final FeedGWT feed) {
        this.clear();

        this.feed = feed;

        FlowPanel leftPanel = new FlowPanel();
        leftPanel.setStyleName("left-panel");
        add(leftPanel);

        final TextBox titleInput = new TextBox();
        titleInput.setValue(feed.getTitle());
        titleInput.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                feed.setTitle(titleInput.getValue());
            }
        });
        leftPanel.add(new Label("Titel: "));
        leftPanel.add(titleInput);

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
                FeedSetupService.saveFeed(feed, new DefaultCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Window.alert("Saved feed");
                    }
                });
            }
        });
        add(saveButton);

        updateViews();

    }

    public void updateViews() {
        previewView.updateView();
        inputItemView.updateView();
        questionItemView.updateView();
        calculationItemView.updateView();
    }

    public void addInputItem(InputGWT feedInput) {
        feed.getInputs().add(feedInput);
        inputItemView.updateView();
    }


    public FeedGWT getFeed() {
        return feed;
    }


    public void addQuestion(QuestionGWT question) {
        feed.getQuestions().add(question);
        questionItemView.updateView();
    }
}