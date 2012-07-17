package dk.tokebroedsted.hibernate.tables;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FeedItem {

    @Id
    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
