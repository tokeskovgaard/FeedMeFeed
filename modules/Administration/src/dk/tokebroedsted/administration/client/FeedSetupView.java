package dk.tokebroedsted.administration.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.administration.client.calculation.CalculationItemView;
import dk.tokebroedsted.administration.client.feedinput.InputItemView;
import dk.tokebroedsted.administration.client.pagesetup.HTMLSetup;
import dk.tokebroedsted.administration.client.question.QuestionItemView;
import dk.tokebroedsted.administration.client.view.*;
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
    private AdministrationServiceAsync administrationService;

    public FeedSetupView(AdministrationServiceAsync administrationService) {
        this.administrationService = administrationService;
        feed = new FeedGWT();
        this.setStyleName("feed-item-setup-view");

        setupView();
    }

    private void setupView() {
        this.clear();


        FlowPanel leftPanel = new FlowPanel();
        leftPanel.setStyleName("left-panel");
        add(leftPanel);

        add(new Label("Titel: "));
        final TextBox titleInput = new TextBox();
        titleInput.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                feed.setTitle(titleInput.getValue());
            }
        });
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
                administrationService.saveFeed(feed, new AsyncCallback<String>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        Window.alert("WEEE");
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
