package dk.tokebroedsted.hibernate.tables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FeedItemInput {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private FeedItem feedItem;

    @ManyToOne
    private FeedInput feedInput;

    private String value;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public FeedItem getFeedItem() {
        return feedItem;
    }

    public FeedInput getFeedInput() {
        return feedInput;
    }

    public String getValue() {
        return value;
    }

    public void setFeedItem(FeedItem feedItem) {
        this.feedItem = feedItem;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setFeedInput(FeedInput feedInput) {
        this.feedInput = feedInput;
    }
}
