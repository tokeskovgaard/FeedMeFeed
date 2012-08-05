package dk.tokebroedsted.commons.client.models;

import com.google.gwt.user.client.rpc.IsSerializable;


public class QuestionItemGWT implements IsSerializable {

    private Integer id;
    private Integer feedItemId;

    private QuestionGWT questionGWT;
    private Integer numericAnswer;
    private Boolean boolAnswer;


    public QuestionItemGWT() {
    }

    public QuestionItemGWT(QuestionGWT questionGWT, int feedItemId) {
        this.questionGWT = questionGWT;
        this.feedItemId = feedItemId;
    }

    public QuestionItemGWT(int id, int feedItemId, QuestionGWT questionGWT, Integer numericAnswer, Boolean boolAnswer) {
        this.id = id;
        this.feedItemId = feedItemId;
        this.questionGWT = questionGWT;
        this.numericAnswer = numericAnswer;
        this.boolAnswer = boolAnswer;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof QuestionItemGWT) {
            return ((QuestionItemGWT) obj).getId().equals(getId());
        }
        return super.equals(obj);
    }


    public Boolean getBoolAnswer() {
        return boolAnswer;
    }

    public void setBoolAnswer(Boolean boolAnswer) {
        this.boolAnswer = boolAnswer;
    }
}
