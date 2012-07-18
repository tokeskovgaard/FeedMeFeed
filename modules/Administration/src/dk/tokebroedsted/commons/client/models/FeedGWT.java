package dk.tokebroedsted.commons.client.models;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FeedGWT implements IsSerializable, Serializable {

    private String title;
    private String css;
    private String html;
    private int feedId;

    private List<InputGWT> inputs = new ArrayList<InputGWT>();
    private List<QuestionGWT> questions = new ArrayList<QuestionGWT>();
    private List<CalculationGWT> calculations = new ArrayList<CalculationGWT>();

    public String getTitle() {
        return title;
    }

    public String getCss() {
        return css;
    }

    public String getHtml() {
        return html;
    }

    public void setHTML(String html) {
        this.html = html;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<InputGWT> getInputs() {
        return inputs;
    }

    public List<QuestionGWT> getQuestions() {
        return questions;
    }

    public List<CalculationGWT> getCalculations() {
        return calculations;
    }

    public void setFeedId(int feedId) {
        this.feedId = feedId;
    }

    public void setInputs(ArrayList<InputGWT> inputs) {
        this.inputs = inputs;
    }

    public int getFeedId() {
        return feedId;
    }
}
