package dk.tokebroedsted.administration.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import dk.tokebroedsted.administration.client.model.QuestionItem;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 16-07-12
 * Time: 19:31
 * To change this template use File | Settings | File Templates.
 */
public class QuestionItemView extends FlowPanel {


    private FeedItemSetup feedItemSetup;

    public QuestionItemView(FeedItemSetup feedItemSetup) {
        this.feedItemSetup = feedItemSetup;
        setStyleName("question-view");
    }

    void updateView() {
        clear();
        add(new Label("Spørgsmål"));
        for (QuestionItem questionItem : feedItemSetup.questionItems) {
            add(new Label(questionItem.getName()));
        }
        Button userInput = new Button("Tilføj nyt spørgsmål");
        add(userInput);
    }

}
