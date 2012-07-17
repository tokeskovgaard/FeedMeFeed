package dk.tokebroedsted.hibernate.tables;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Feed {

    @Id
    private int id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feed")
    private Set<FeedInput> feedInputs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<FeedInput> getFeedInputs() {
        return feedInputs;
    }

    public void setFeedInputs(Set<FeedInput> feedInputs) {
        this.feedInputs = feedInputs;
    }
}
