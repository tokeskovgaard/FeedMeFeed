package dk.tokebroedsted.hibernate.tables;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class FeedItem {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User owner;

    @ManyToOne
    private Feed feed;

    @OneToMany(mappedBy = "feedItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FeedItemInput> feedItemInputs;


    @OneToMany(mappedBy = "feedItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<QuestionItem> questionItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Feed getFeed() {
        return feed;
    }

    public User getOwner() {
        return owner;
    }

    public Set<FeedItemInput> getFeedItemInputs() {
        return feedItemInputs;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setItemInputs(Set<FeedItemInput> feedItemInputs) {
        this.feedItemInputs = feedItemInputs;
    }

    public Set<QuestionItem> getQuestionItems() {
        return questionItems;
    }
}
