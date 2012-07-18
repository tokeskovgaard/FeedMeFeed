package dk.tokebroedsted.feed.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 18-07-12
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */
public class FeedItemGWT implements IsSerializable, Serializable {
    private List<InputItemGWT> inputItems;
    private List<QuestionItemGWT> questionItems;
    private List<CalculationItemGWT> calculationItems;

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
}
