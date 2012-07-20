package dk.tokebroedsted.hibernate.tables;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Feed> createdFeeds = new HashSet<Feed>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "FeedSubscription",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "feed_id")
    )
    private Set<Feed> feedSubscriptions;

    @Column(nullable = false)
    private String loginname, password, email;

    @Column(unique = true)
    private String username;


    public User(int id, String loginname, String username, String password, String email) {
        this.id = id;
        this.loginname = loginname;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String loginname, String username, String password, String email) {
        this.loginname = loginname;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User() {
        id = -1;
    }

    public Set<Feed> getCreatedFeeds() {
        return createdFeeds;
    }

    public Set<Feed> getFeedSubscriptions() {
        return feedSubscriptions;
    }
}
