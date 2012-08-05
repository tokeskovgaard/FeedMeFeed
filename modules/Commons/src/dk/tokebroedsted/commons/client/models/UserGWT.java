package dk.tokebroedsted.commons.client.models;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created with IntelliJ IDEA.
 * User: mads
 * Date: 7/19/12
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserGWT implements IsSerializable {

    private int id;
    private String loginname, username, email, password;

    public UserGWT(String loginname, String username, String email, String password) {
        this.id = 0;
        this.loginname = loginname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserGWT(int id, String loginname, String username, String email, String password) {

        this.id = id;
        this.loginname = loginname;
        this.username = username;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserGWT() {

    }
}
