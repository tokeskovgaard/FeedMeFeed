package dk.tokebroedsted.hibernate.tables;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@Entity
public class FeedInput {

    private enum Type {string, number,image}

    @Id
    private int id;

    @ManyToOne
    private Feed feed;

    private Type type;

    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

//    public Type getType() {
//        return type;
//    }
//
//    public void setType(Type type) {
//        this.type = type;
//    }


    public String getKey() {
        return name;
    }

    public void setKey(String name) {
        this.name = name;
    }
}
