package dk.tokebroedsted.feed.client.content;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;

import dk.tokebroedsted.commons.client.DefaultCallback;
import dk.tokebroedsted.commons.client.models.*;
import dk.tokebroedsted.feed.client.FeedServiceAsync;

public class FeedContentItem extends FlowPanel {

    private FeedServiceAsync feedService;

    public FeedContentItem(FeedGWT feedGWT, FeedItemGWT feedItemGWT, FeedServiceAsync feedService) {
        this.feedService = feedService;
        setStyleName("feed-item");

        renderFeedItem(feedGWT, feedItemGWT);
    }

    private void renderFeedItem(FeedGWT feedGWT, FeedItemGWT feedItemGWT) {
        clear();

        String html = feedGWT.getHtml();

        for (InputItemGWT inputItem : feedItemGWT.getInputItems()) {
            html = html.replaceAll(inputItem.getVariabelId(), inputItem.getValue());
        }

//        for (CalculationItemGWT calculationItem : feedItemGWT.getCalculationItems()) {
//            html = html.replaceAll(calculationItem.getName(), calculationItem.getValue());
//        }

        int index = 0;
        int newIndex;
        while ((newIndex = html.indexOf("<%question ", index)) != -1) {
            String pureHtml = html.substring(index, newIndex);
            add(new InlineHTML(pureHtml));
            String remainingHTML = html.substring(newIndex);
            index = newIndex;

            for (QuestionGWT questionGWT : feedGWT.getQuestions()) {
                String variableId = questionGWT.getVariableId();
                if (remainingHTML.startsWith(variableId)) {
                    index += variableId.length();
                    Widget questionWidget = createQuestionWidget(feedItemGWT, questionGWT);
                    add(questionWidget);
                }
            }
        }

        if (index < html.length() - 1) {
            add(new InlineHTML(html.substring(index, html.length() - 1)));
        }


/*
        for (QuestionGWT question : feedGWT.getQuestions()) {
            Widget typeWidget = createQuestionWidget(feedItemGWT, question);

            html = html.replaceAll(question.getVariableId(), typeWidget.toString());
        }
*/
    }

    private Widget createQuestionWidget(FeedItemGWT feedItemGWT, QuestionGWT question) {
        QuestionItemGWT questionItem = feedItemGWT.getQuestionItem(question);

        Widget typeWidget;

        QuestionGWT.Type type = question.getType();
        if (QuestionGWT.Type.numeric.equals(type)) {

            typeWidget = createNumericQuestionWidget(questionItem);
        } else {
            throw new RuntimeException("Found a type not supported");
        }
        return typeWidget;
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
