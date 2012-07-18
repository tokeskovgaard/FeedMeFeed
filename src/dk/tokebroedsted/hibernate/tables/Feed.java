package dk.tokebroedsted.hibernate.tables;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Feed {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User owner;

    private String html, css;

    @OneToMany (mappedBy = "feed")
    private Set<FeedInput> feedInputs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public Set<FeedInput> getFeedInputs() {
        return feedInputs;
    }

    public void setFeedInputs(Set<FeedInput> feedInputs) {
        this.feedInputs = feedInputs;
    }
}
