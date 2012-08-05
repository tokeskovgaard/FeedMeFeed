package dk.tokebroedsted.feedsetup.client.question;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import dk.tokebroedsted.feedsetup.client.FeedSetupView;
import dk.tokebroedsted.commons.client.models.QuestionGWT;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 17-07-12
 * Time: 20:10
 * To change this template use File | Settings | File Templates.
 */
public class QuestionItemView extends FlowPanel {


    private FeedSetupView feedItemSetup;

    public QuestionItemView(FeedSetupView feedItemSetup) {
        this.feedItemSetup = feedItemSetup;

    }

    public void updateView() {
        clear();
        Label titel = new Label("Spørgsmål");
        titel.setStyleName("titel");
        add(titel);

        for (QuestionGWT question : feedItemSetup.getFeed().getQuestions()) {
            Label listItem = new Label(question.getName() + " - " + question.getVariableId());
            listItem.setStyleName("list-item");
            add(listItem);
        }

        Button userInput = new Button("Tilføj nyt spørgsmål");
        userInput.setStyleName("add-new-button");
        userInput.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                EditQuestionEditDialog dialog = new EditQuestionEditDialog(feedItemSetup);
                dialog.show();
            }
        });
        add(userInput);
    }
}
