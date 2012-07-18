package dk.tokebroedsted.feed.client.content;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
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

    public FeedContentItem(FeedServiceAsync feedService, FeedGWT feedGWT, FeedItemGWT feedItemGWT) {
        this.feedService = feedService;
        setStyleName("feed-content-item");

        renderFeedItem(feedGWT, feedItemGWT);
    }

    private void renderFeedItem(FeedGWT feedGWT, FeedItemGWT feedItemGWT) {
        clear();

        add(new HTML(feedGWT.getCss()));

        String html = feedGWT.getHtml();

        for (InputItemGWT inputItem : feedItemGWT.getInputItems()) {
            html = html.replaceAll(inputItem.getName(), inputItem.getValue());
        }

        for (CalculationItemGWT calculationItem : feedItemGWT.getCalculationItems()) {
            html = html.replaceAll(calculationItem.getName(), calculationItem.getValue());
        }

        for (QuestionItemGWT questionItem : feedItemGWT.getQuestionItems()) {
            QuestionItemGWT.Type type = questionItem.getType();
            if (QuestionItemGWT.Type.numeric.equals(type)) {
                createNumericQuestionWidget(questionItem);

            } else {
                throw new RuntimeException("Found a type not supported");
            }
            //TODO render questionItems
        }

        add(new HTML(html));
    }

    private Widget createNumericQuestionWidget(final QuestionItemGWT questionItem) {
        final TextBox answerBox = new TextBox();
        answerBox.setStyleName(questionItem.getName());

        Integer numericAnswer = questionItem.getNumericAnswer();
        if (numericAnswer != null)
            answerBox.setValue(String.valueOf(numericAnswer));

        answerBox.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (KeyCodes.KEY_ENTER == event.getNativeKeyCode()) {
                    questionItem.setNumericAnswer(Integer.valueOf(answerBox.getValue()));
                    //TODO Save changes
//                    feedService.saveReply(questionItem, new DefaultCallback<Boolean>(){

//                        @Override
//                        public void onSuccess(Boolean result) {
//                        }
//                    });
                }
            }
        });

        return answerBox;
    }
}
