package dk.tokebroedsted.commons.client.models;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FeedItemGWT implements IsSerializable, Serializable {

    private List<InputItemGWT> inputItems = new ArrayList<InputItemGWT>();
    private List<QuestionItemGWT> questionItems = new ArrayList<QuestionItemGWT>();
    private List<CalculationItemGWT> calculationItems = new ArrayList<CalculationItemGWT>();

    private FeedGWT feedGWT;
    private int feedId;

    public FeedItemGWT() {
    }

    public FeedItemGWT(FeedGWT feedGWT) {

        this.feedGWT = feedGWT;
    }

    public FeedItemGWT(int feedId) {
        this.feedId = feedId;
    }

    public List<InputItemGWT> getInputItems() {
        return inputItems;
    }

    public List<QuestionItemGWT> getQuestionItems() {
        return questionItems;
    }

    public List<CalculationItemGWT> getCalculationItems() {
        return calculationItems;
    }

    public void setInputItems(List<InputItemGWT> inputItems) {
        this.inputItems = inputItems;
    }

    public void setQuestionItems(List<QuestionItemGWT> questionItems) {
        this.questionItems = questionItems;
    }

    public void setCalculationItems(List<CalculationItemGWT> calculationItems) {
        this.calculationItems = calculationItems;
    }

    public int getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }
}
