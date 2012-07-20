package dk.tokebroedsted.administration.client.question;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.administration.client.FeedSetupView;
import dk.tokebroedsted.administration.client.view.AbstractItemEditDialog;
import dk.tokebroedsted.commons.client.models.QuestionGWT;

public class EditQuestionEditDialog extends AbstractItemEditDialog {
    private ListBox questionTypeSelect;
    private FlowPanel typeSpecificPanel;
    private FeedSetupView feedItemSetup;
    private TextBox questionInput;

    public EditQuestionEditDialog(final FeedSetupView feedItemSetup) {
        this.feedItemSetup = feedItemSetup;
        buildDialog();
    }

    @Override
    protected void renderBody(FlowPanel content) {
        content.add(new Label("Spørgsmål:"));
        questionInput = new TextBox();
        content.add(questionInput);

        questionTypeSelect = new ListBox(false);
        for (QuestionGWT.Type type : QuestionGWT.Type.values()) {
            questionTypeSelect.addItem(type.toString());
        }
        questionTypeSelect.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                updateTypeSpecificPart();
            }
        });
        content.add(questionTypeSelect);
        typeSpecificPanel = new FlowPanel();
        content.add(typeSpecificPanel);
        updateTypeSpecificPart();
    }

    @Override
    protected ClickHandler createSaveItemClickHandler() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                QuestionGWT question = new QuestionGWT(feedItemSetup.getFeed().getId(), questionInput.getValue(), getSelectedType());
                feedItemSetup.addQuestion(question);
                hide();
            }
        };
    }

    private QuestionGWT.Type getSelectedType() {
        int selectedIndex = questionTypeSelect.getSelectedIndex();
        String itemText = questionTypeSelect.getItemText(selectedIndex);
        return QuestionGWT.Type.valueOf(itemText);
    }

    private void updateTypeSpecificPart() {
        typeSpecificPanel.clear();

        QuestionGWT.Type selectedType = getSelectedType();
        if (QuestionGWT.Type.numeric.equals(selectedType)) {
            typeSpecificPanel.add(new Label("Brugeren vil blive bedt om at indtaste et tal."));
        } else {
            throw new RuntimeException("Found a type that isn't supported");
        }
    }
}
