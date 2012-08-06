package dk.tokebroedsted.commons.client.models;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;
import java.util.List;

public class FeedItemGWT implements IsSerializable {

    private List<InputItemGWT> inputItems = new ArrayList<InputItemGWT>();
    private List<QuestionItemGWT> questionItems = new ArrayList<QuestionItemGWT>();
    private List<CalculationValueGWT> calculationValues = new ArrayList<CalculationValueGWT>();

    private int id;
    private int feedId;

    public FeedItemGWT() {
    }

    public FeedItemGWT(int feedId, List<InputItemGWT> inputItems) {
        this.feedId = feedId;
        this.inputItems = inputItems;
    }

    public FeedItemGWT(int id, int feedId, List<InputItemGWT> inputItems, List<QuestionItemGWT> questionItems, List<CalculationValueGWT> calculationValues) {
        this.id = id;
        this.feedId = feedId;
        this.inputItems = inputItems;
        this.questionItems = questionItems;
        this.calculationValues = calculationValues;
    }

    public List<InputItemGWT> getInputItems() {
        return inputItems;
    }

    public InputItemGWT getInputItem(String variableId) {
        for (InputItemGWT inputItem : inputItems) {
            if (inputItem.getVariableId().equals(variableId)) {
                return inputItem;
            }
        }
        return null;
    }

    public int getFeedId() {
        return feedId;
    }

    public QuestionItemGWT getQuestionItem(QuestionGWT question) {
        for (QuestionItemGWT questionItem : questionItems) {
            if (questionItem.getQuestionGWT().equals(question)) {
                return questionItem;
            }
        }
        return new QuestionItemGWT(question, id);
    }

    public CalculationValueGWT getCalculationValue(String variableId) {
        for (CalculationValueGWT calculationValue : calculationValues) {
            if (calculationValue.getVariableId().equals(variableId)) {
                return calculationValue;
            }
        }
        return null;
    }

    public List<CalculationValueGWT> getCalculationValues() {
        return calculationValues;
    }
}
