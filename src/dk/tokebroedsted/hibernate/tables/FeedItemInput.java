package dk.tokebroedsted.hibernate.tables;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FeedItemInput {

    @Id
    private int id;

    @ManyToOne
    private Feed feed;

    @ManyToOne
    private FeedInput feedInput;

    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }
}
