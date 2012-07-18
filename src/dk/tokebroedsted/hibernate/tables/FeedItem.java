package dk.tokebroedsted.hibernate.tables;

import javax.persistence.*;
import java.util.Set;

@Entity
public class FeedItem {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Feed feed;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "feedItem", cascade = CascadeType.ALL)
    private Set<FeedItemInput> feedItemInputs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
