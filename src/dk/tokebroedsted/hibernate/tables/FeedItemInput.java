package dk.tokebroedsted.hibernate.tables;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 17-07-12
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class FeedItemInput {

    @Id
    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
