package dk.tokebroedsted.feed.client.content;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import dk.tokebroedsted.commons.client.DefaultCallback;
import dk.tokebroedsted.commons.client.models.*;
import dk.tokebroedsted.feed.client.FeedServiceAsync;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 18-07-12
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 */
public class FeedContentItem extends FlowPanel {

    private FeedServiceAsync feedService;

    public FeedContentItem(FeedGWT feedGWT, FeedItemGWT feedItemGWT, FeedServiceAsync feedService) {
        this.feedService = feedService;
        setStyleName("feed-content-item");

        renderFeedItem(feedGWT, feedItemGWT);
    }

    private void renderFeedItem(FeedGWT feedGWT, FeedItemGWT feedItemGWT) {
        clear();

        String html = feedGWT.getHtml();

        for (InputItemGWT inputItem : feedItemGWT.getInputItems()) {
            html = html.replaceAll(inputItem.getVariabelId(), inputItem.getValue());
        }

        for (CalculationItemGWT calculationItem : feedItemGWT.getCalculationItems()) {
            html = html.replaceAll(calculationItem.getName(), calculationItem.getValue());
        }

        for (QuestionItemGWT questionItem : feedItemGWT.getQuestionItems()) {
            QuestionGWT.Type type = questionItem.getQuestionGWT().getType();
            if (QuestionGWT.Type.numeric.equals(type)) {
                Widget numericQuestionWidget = createNumericQuestionWidget(questionItem);

                html = html.replaceAll(questionItem.getQuestionGWT().getVariableId(), numericQuestionWidget.toString());
            } else {
                throw new RuntimeException("Found a type not supported");
            }
            //TODO render questionItems
        }

        HTML feedItemHTML = new HTML(html);
        feedItemHTML.setStyleName("feed-item");
        add(feedItemHTML);
    }

    private Widget createNumericQuestionWidget(final QuestionItemGWT questionItem) {
        final TextBox answerBox = new TextBox();
        answerBox.setStyleName(questionItem.getQuestionGWT().getName());

        Integer numericAnswer = questionItem.getNumericAnswer();
        if (numericAnswer != null)
            answerBox.setValue(String.valueOf(numericAnswer));

        answerBox.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (KeyCodes.KEY_ENTER == event.getNativeKeyCode()) {
                    questionItem.setNumericAnswer(Integer.valueOf(answerBox.getValue()));
                    //TODO Save changes
                    feedService.saveQuestionReply(questionItem, new DefaultCallback<Void>() {

                        @Override
                        public void onSuccess(Void result) {

                        }
                    });
                }
            }
        });

        return answerBox;
    }
}
