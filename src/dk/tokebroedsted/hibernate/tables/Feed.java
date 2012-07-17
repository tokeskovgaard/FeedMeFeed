package dk.tokebroedsted.hibernate.tables;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Feed {

    @Id
    private int id;

    @ManyToOne
    private User owner;

    @OneToMany (mappedBy = "id")
    private Set<FeedInput> feedInputs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
