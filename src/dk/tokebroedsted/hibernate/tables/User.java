package dk.tokebroedsted.hibernate.tables;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class User {

    @Id
    private int id;

    @OneToMany (mappedBy = "id")
    private Set<Feed> feeds;

    private String loginname, username, password, email;

    public User(int id, String loginname, String username, String password, String email) {
        this.id = id;
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
}
