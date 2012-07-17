package dk.tokebroedsted.user.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: mads
 * Date: 7/17/12
 * Time: 5:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class User implements IsSerializable, Serializable {
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

    private String loginname, username, password, email;
    private int id;

    public User(int id, String loginname,String username, String password, String email) {
        this.id = id;
        this.loginname = loginname;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {
        id = -1;
    }

}
