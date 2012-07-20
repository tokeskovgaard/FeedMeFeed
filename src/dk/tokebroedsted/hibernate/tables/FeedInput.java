package dk.tokebroedsted.hibernate.tables;

import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@Entity
public class FeedInput {


    public enum Type {string}

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Feed feed;

    @Column(nullable = false)
    private Type type;

    private String name;

    public int getId() {
        return id;
    }

    public FeedInput() {
    }

    public FeedInput(Feed feed, Type type, String name) {
        this.feed = feed;
        this.type = type;
        this.name = name;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setType(Type type) {
        this.type = type;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
