package dk.tokebroedsted.hibernate.tables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Calculation {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Feed feed;

    private String name;

    private String calculation;

    public Calculation() {
    }

    public Calculation(Feed feed, String name, String calculation) {
        this.feed = feed;
        this.name = name;
        this.calculation = calculation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Feed getFeed() {
        return feed;
    }

    public String getCalculation() {
        return calculation;
    }
}
