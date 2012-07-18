package dk.tokebroedsted.hibernate.tables;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Feed {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User owner;

    private String html, css, title;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FeedInput> feedInputs;

    public Feed() {
    }

    public Feed(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
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

    public List<FeedInput> getFeedInputs() {
        return feedInputs;
    }

    public void setFeedInputs(List<FeedInput> feedInputs) {
        this.feedInputs = feedInputs;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
