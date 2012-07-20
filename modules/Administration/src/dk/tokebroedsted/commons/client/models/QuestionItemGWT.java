package dk.tokebroedsted.commons.client.models;

import com.google.gwt.user.client.rpc.IsSerializable;


public class QuestionItemGWT implements IsSerializable {

    private Integer id;
    private Integer feedItemId;

    private QuestionGWT questionGWT;
    private Integer numericAnswer;


    public QuestionItemGWT() {
    }

    public QuestionItemGWT(QuestionGWT questionGWT, int feedItemId) {
        this.questionGWT = questionGWT;
        this.feedItemId = feedItemId;
    }

    public QuestionItemGWT(int id, int feedItemId, QuestionGWT questionGWT, Integer numericAnswer) {
        this.id = id;
        this.feedItemId = feedItemId;
        this.questionGWT = questionGWT;
        this.numericAnswer = numericAnswer;
    }

    public Integer getNumericAnswer() {
        return numericAnswer;
    }

    public void setNumericAnswer(Integer numericAnswer) {
        this.numericAnswer = numericAnswer;
    }

    public QuestionGWT getQuestionGWT() {
        return questionGWT;
    }

    public Integer getId() {
        return id;
    }

    public Integer getFeedItemId() {
        return feedItemId;
    }
}
