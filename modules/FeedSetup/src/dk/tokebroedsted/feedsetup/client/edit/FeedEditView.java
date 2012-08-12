package dk.tokebroedsted.feedsetup.client.edit;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.commons.client.DefaultCallback;
import dk.tokebroedsted.commons.client.models.*;
import dk.tokebroedsted.commons.client.previewhelp.DummyDataFactory;
import dk.tokebroedsted.feedsetup.client.FeedSetupEntryPoint;
import dk.tokebroedsted.feedsetup.client.edit.calculation.CalculationEditDialog;
import dk.tokebroedsted.feedsetup.client.edit.calculation.CalculationListItem;
import dk.tokebroedsted.commons.client.preview.FeedWidget;
import dk.tokebroedsted.feedsetup.client.edit.question.QuestionEditDialog;
import dk.tokebroedsted.feedsetup.client.edit.question.QuestionListItem;
import dk.tokebroedsted.feedsetup.client.edit.userinput.UserInputEditDialog;
import dk.tokebroedsted.feedsetup.client.edit.userinput.UserInputListItem;

import java.util.ArrayList;

public class FeedEditView extends Composite {

    private boolean isEditingHtml;
    private boolean isShowingSingleFeedItemPreview;

    interface FeedSetupLayoutViewUiBinder extends UiBinder<HTMLPanel, FeedEditView> {
    }

    private static FeedSetupLayoutViewUiBinder ourUiBinder = GWT.create(FeedSetupLayoutViewUiBinder.class);

    @UiField TextBox title;
    @UiField FlowPanel userInputPanel;
    @UiField FlowPanel questionsPanel;
    @UiField FlowPanel calculationsPanel;
    @UiField TextArea editingArea;
    @UiField Button addCalculationButton;
    @UiField Button addUserInputButton;
    @UiField FeedWidget feedWidget;

    final FeedGWT feedGWT; // cannot be private

    public FeedEditView(FeedGWT feedGWT) {
        initWidget(ourUiBinder.createAndBindUi(this));

        //TODO Only for testing. Remove when going live!
        if (feedGWT.getTitle() == null || feedGWT.getTitle().length() == 0) {
            feedGWT.setCss(".feed{\n" +
                    "background-color: gray;\n" +
                    "padding: 5px;\n" +
                    "padding-bottom:5px;\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    ".feed-item{\n" +
                    "background-color:lightgray;\n" +
                    "padding:5px;\n" +
                    "\n" +
                    "margin-top: 5px;\n" +
                    "margin-bottom: 5px;\n" +
                    "}\n" +
                    "\n" +
                    ".titel{\n" +
                    "font-weight: bold;\n" +
                    "}\n" +
                    "\n" +
                    ".true {\n" +
                    "text-decoration:line-through;\n" +
                    "}");

            feedGWT.setHtml("<span class=\"<%calculation bool%>\">Titel: <%input titel%></span>\n" +
                    "<br />\n" +
                    "Spg: <%question spg%>\n" +
                    "<br />\n" +
                    "Bool: <%calculation bool%>");

            feedGWT.getInputs().add(new InputGWT("titel", InputGWT.Type.string));
            feedGWT.getQuestions().add(new QuestionGWT(null, null, "spg", QuestionGWT.Type.bool));
            feedGWT.getCalculations().add(new CalculationGWT(null, "bool", "true", CalculationGWT.Type.bool));
        }

        this.feedGWT = feedGWT;

        isEditingHtml = true;
        editingArea.setValue(feedGWT.getHtml());
        title.setValue(feedGWT.getTitle());

        for (InputGWT inputGWT : feedGWT.getInputs()) {
            addInputToView(inputGWT);
        }

        for (QuestionGWT questionGWT : feedGWT.getQuestions()) {
            addQuestionToView(questionGWT);
        }

        for (CalculationGWT calculationGWT : feedGWT.getCalculations()) {
            addCalculationToView(calculationGWT);
        }

        updatePreview();
    }

    private void addInputToView(final InputGWT inputGWT) {
        final UserInputListItem listItem = new UserInputListItem(inputGWT);
        listItem.setDeleteCommand(new Command() {
            @Override
            public void execute() {
                feedGWT.getInputs().remove(inputGWT);
                userInputPanel.remove(listItem);
            }
        });
        userInputPanel.add(listItem);
    }

    private void addCalculationToView(final CalculationGWT calculationGWT) {
        final CalculationListItem listItem = new CalculationListItem(calculationGWT);
        listItem.setDeleteCommand(new Command() {
            @Override
            public void execute() {
                feedGWT.getCalculations().remove(calculationGWT);
                calculationsPanel.remove(listItem);
            }
        });
        calculationsPanel.add(listItem);
    }


    private void addQuestionToView(final QuestionGWT questionGWT) {
        final QuestionListItem listItem = new QuestionListItem(questionGWT);
        listItem.setDeleteCommand(new Command() {
            @Override
            public void execute() {
                feedGWT.getQuestions().remove(questionGWT);
                questionsPanel.remove(listItem);
            }
        });
        questionsPanel.add(listItem);

    }

    private void updatePreview() {
        ArrayList<FeedItemGWT> dummyFeedItemGWTs = new ArrayList<FeedItemGWT>();
        if (isShowingSingleFeedItemPreview) {
            dummyFeedItemGWTs.add(DummyDataFactory.createDummyFeedItemGwt(feedGWT));
        } else {
            dummyFeedItemGWTs.add(DummyDataFactory.createDummyFeedItemGwt(feedGWT));
            dummyFeedItemGWTs.add(DummyDataFactory.createDummyFeedItemGwt(feedGWT));
            dummyFeedItemGWTs.add(DummyDataFactory.createDummyFeedItemGwt(feedGWT));
        }
        feedWidget.renderFeedItems(feedGWT, dummyFeedItemGWTs);
    }

    @UiHandler("saveFeedButton")
    void saveFeed(ClickEvent e) {
        FeedSetupEntryPoint.feedService.saveFeed(feedGWT, new DefaultCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }
        });
    }

    @UiHandler("title")
    void titleChanged(ChangeEvent event) {
        feedGWT.setTitle(title.getValue());
    }


    @UiHandler("addUserInputButton")
    void addUserInput(ClickEvent e) {
        final InputGWT inputGWT = new InputGWT();
        UserInputEditDialog inputEditDialog = new UserInputEditDialog(inputGWT, new Command() {
            @Override
            public void execute() {
                feedGWT.getInputs().add(inputGWT);
                addInputToView(inputGWT);

            }
        });
        inputEditDialog.getElement().getStyle().setLeft(addUserInputButton.getAbsoluteLeft(), Style.Unit.PX);
        inputEditDialog.getElement().getStyle().setTop(addUserInputButton.getAbsoluteTop(), Style.Unit.PX);
        inputEditDialog.show();
    }

    @UiHandler("addQuestionButton")
    void addQuestion(ClickEvent e) {
        final QuestionGWT questionGWT = new QuestionGWT();
        QuestionEditDialog questionEditDialog = new QuestionEditDialog(questionGWT, new Command() {
            @Override
            public void execute() {
                feedGWT.getQuestions().add(questionGWT);
                addQuestionToView(questionGWT);
            }
        });
        questionEditDialog.getElement().getStyle().setLeft(addCalculationButton.getAbsoluteLeft(), Style.Unit.PX);
        questionEditDialog.getElement().getStyle().setTop(addCalculationButton.getAbsoluteTop(), Style.Unit.PX);
        questionEditDialog.show();
    }

    @UiHandler("addCalculationButton")
    void addCalculation(ClickEvent e) {
        final CalculationGWT calculationGWT = new CalculationGWT();
        CalculationEditDialog calculationEditDialog = new CalculationEditDialog(calculationGWT, new Command() {
            @Override
            public void execute() {
                feedGWT.getCalculations().add(calculationGWT);
                addCalculationToView(calculationGWT);
            }
        });
        calculationEditDialog.getElement().getStyle().setLeft(addCalculationButton.getAbsoluteLeft(), Style.Unit.PX);
        calculationEditDialog.getElement().getStyle().setTop(addCalculationButton.getAbsoluteTop(), Style.Unit.PX);
        calculationEditDialog.show();
    }

    @UiHandler("editingArea")
    void htmlSetupChanged(KeyUpEvent e) {
        if (isEditingHtml) {
            feedGWT.setHtml(editingArea.getValue());
        } else {
            feedGWT.setCss(editingArea.getValue());
        }
        updatePreview();
    }

    @UiHandler("htmlSetup")
    void chooseHtmlSetup(ClickEvent e) {
        isEditingHtml = true;
        editingArea.setValue(feedGWT.getHtml());
    }

    @UiHandler("cssSetup")
    void chooseCssSetup(ClickEvent e) {
        isEditingHtml = false;
        editingArea.setValue(feedGWT.getCss());
    }

    @UiHandler("singlePreview")
    void chooseSinglePreview(ClickEvent e) {
        isShowingSingleFeedItemPreview = true;
        updatePreview();
    }

    @UiHandler("listPreview")
    void chooseListPreview(ClickEvent e) {
        isShowingSingleFeedItemPreview = false;
        updatePreview();
    }
}