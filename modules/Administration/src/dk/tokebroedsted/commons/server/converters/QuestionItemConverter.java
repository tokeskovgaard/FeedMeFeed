package dk.tokebroedsted.commons.server.converters;

import dk.tokebroedsted.commons.client.models.QuestionGWT;
import dk.tokebroedsted.commons.client.models.QuestionItemGWT;
import dk.tokebroedsted.hibernate.ModelFactory;
import dk.tokebroedsted.hibernate.tables.FeedItem;
import dk.tokebroedsted.hibernate.tables.Question;
import dk.tokebroedsted.hibernate.tables.QuestionItem;
import dk.tokebroedsted.hibernate.tables.User;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 19-07-12
 * Time: 23:36
 * To change this template use File | Settings | File Templates.
 */
public class QuestionItemConverter implements Converter<QuestionItemGWT, QuestionItem> {

    @Override
    public QuestionItem toServerObject(QuestionItemGWT questionItemGWT) {
        QuestionItem questionItem;
        if (questionItemGWT.getId() != null) {
            questionItem = ModelFactory.getQuestionItem(questionItemGWT.getId());
        } else {
            questionItem = new QuestionItem();
        }

        User owner = ModelFactory.getUser("toke");
        FeedItem feedItem = ModelFactory.getFeedItem(questionItemGWT.getFeedItemId());
        QuestionGWT questionGWT = questionItemGWT.getQuestionGWT();
        Question question = ModelFactory.getQuestion(questionGWT.getId());

        questionItem.setQuestion(question);
        questionItem.setFeedItem(feedItem);
        questionItem.setOwner(owner);

        QuestionGWT.Type type = questionGWT.getType();
        if (QuestionGWT.Type.numeric == type) {
            questionItem.setNumericAnswer(questionItemGWT.getNumericAnswer());
        } else {
            throw new RuntimeException("Found unexpected type");
        }

        if (questionItemGWT.getId() == null) {
            ModelFactory.save(questionItem);
        }
        return questionItem;
    }

    @Override
    public QuestionItemGWT toGwtObject(QuestionItem serverObject) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
