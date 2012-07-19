package dk.tokebroedsted.hibernate.tables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 19-07-12
 * Time: 23:29
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Question {

    @Id
    @GeneratedValue
    private int id;

    public enum Type {numeric}

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
