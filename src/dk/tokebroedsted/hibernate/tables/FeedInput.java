package dk.tokebroedsted.hibernate.tables;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@Entity
public class FeedInput {


    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public enum Type {string}

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Feed feed;

    private Type type;

    private String name;

    public int getId() {
        return id;
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
}
