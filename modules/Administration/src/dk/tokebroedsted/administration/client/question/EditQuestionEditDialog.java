package dk.tokebroedsted.administration.client.question;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.administration.client.FeedSetupView;
import dk.tokebroedsted.administration.client.model.Question;
import dk.tokebroedsted.administration.client.view.AbstractItemEditDialog;

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
        for (Question.Type type : Question.Type.values()) {
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
                Question question = new Question();
                question.setName(questionInput.getValue());
                question.setType(getSelectedType());
                feedItemSetup.addQuestion(question);
                hide();
            }
        };
    }

    private Question.Type getSelectedType() {
        int selectedIndex = questionTypeSelect.getSelectedIndex();
        String itemText = questionTypeSelect.getItemText(selectedIndex);
        return Question.Type.valueOf(itemText);
    }

    private void updateTypeSpecificPart() {
        typeSpecificPanel.clear();

        Question.Type selectedType = getSelectedType();
        if (Question.Type.open.equals(selectedType)) {
            typeSpecificPanel.add(new Label("Brugeren vil blive bedt om at indtaste en tekst."));
        } else if (Question.Type.numeric.equals(selectedType)) {
            typeSpecificPanel.add(new Label("Brugeren vil blive bedt om at indtaste et tal."));
            //TODO mulighed for grænser?
        } else if (Question.Type.closed.equals(selectedType)) {
            typeSpecificPanel.add(new Label("Tilføj svar muligheder der kan vælges mellem:"));

        } else {
            throw new RuntimeException("Found a type that isn't supported");
        }
    }

}
