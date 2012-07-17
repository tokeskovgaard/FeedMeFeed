package dk.tokebroedsted.hibernate.tables;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@Entity
public class FeedInput {

    private enum Type {string, number, image}

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

    public void setId(int id) {
        this.id = id;
    }
}
