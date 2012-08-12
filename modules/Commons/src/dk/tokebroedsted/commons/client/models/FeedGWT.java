package dk.tokebroedsted.commons.client.models;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;
import java.util.List;

public class FeedGWT implements IsSerializable {

    private String owner;
    private Integer subscribers;

    private String title;
    private String css = ".feed{}\n\n.feed-item{}";
    private String html = "";
    private Integer feedId;

    private List<InputGWT> inputs = new ArrayList<InputGWT>();
    private List<QuestionGWT> questions = new ArrayList<QuestionGWT>();
    private List<CalculationGWT> calculations = new ArrayList<CalculationGWT>();

    //    private List<FeedItemGWT> feedItems = new ArrayList<FeedItemGWT>();
    private boolean hasBeenInstantiated;

    public String getTitle() {
        return title;
    }

    public String getCss() {
        return css;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
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

    public List<CalculationGWT> getCalculations() {
        return calculations;
    }

    public void setInputs(ArrayList<InputGWT> inputs) {
        this.inputs = inputs;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public Integer getId() {
        return feedId;
    }

/*
    public void setFeedItems(List<FeedItemGWT> feedItems) {
        this.feedItems = feedItems;
    }
*/

    public void setHasBeenInstantiated(boolean hasBeenInstantiated) {
        this.hasBeenInstantiated = hasBeenInstantiated;
    }

    public boolean getHasBeenInstantiated() {
        return hasBeenInstantiated;
    }

//    public List<FeedItemGWT> getFeedItems() {
//        return feedItems;
//    }

    public void setQuestions(ArrayList<QuestionGWT> questions) {
        this.questions = questions;
    }

    public List<QuestionGWT> getQuestions() {
        return questions;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Integer subscribers) {
        this.subscribers = subscribers;
    }
}
