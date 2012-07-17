package dk.tokebroedsted.hibernate.tables;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class FeedItem {

    @Id
    private int id;

    @ManyToOne
    private Feed feed;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "id")
    private Set<User> subscribers;

    @OneToMany(mappedBy = "id")
    private Set<FeedItemInput> feedItemInputs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
