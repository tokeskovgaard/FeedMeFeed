package dk.tokebroedsted.commons.client.models;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

public class QuestionItemGWT implements IsSerializable, Serializable {

    private QuestionGWT questionGWT;

    private Integer id;
    private Integer numericAnswer;
    private Integer feedItemId;

    public QuestionItemGWT() {
    }

    public QuestionItemGWT(QuestionGWT questionGWT) {
        this.questionGWT = questionGWT;
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
