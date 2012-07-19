package dk.tokebroedsted.objects;


import dk.tokebroedsted.hibernate.tables.Feed;
import dk.tokebroedsted.hibernate.tables.User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mads
 * Date: 7/16/12
 * Time: 5:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseHandler {

    private Connection con;
    private static String username = "kagemei_fisk";
    private static String password = "feedsaregood";

    public DatabaseHandler() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:mysql://mydb20.surftown.dk:3306/kagemei_feedme";
        try {

            con =  DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(String loginname, String password) {

        User user = new User();

        try{
            password = hashPassword(password);
            String sql = "SELECT * FROM User WHERE loginname = ? AND password = ?";
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setString(1, loginname);
            prest.setString(2, password);
            ResultSet rs = prest.executeQuery();
            while (rs.next()){
                user.setLoginname(rs.getString("loginname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));

            }
            prest.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        return user;
    }

    public User getUser(String loginname) {

        User user = new User();

        try{

            String sql = "SELECT * FROM Users WHERE loginname = ?";
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setString(1, loginname);
            ResultSet rs = prest.executeQuery();
            while (rs.next()){
                user.setLoginname(rs.getString("loginname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));

            }
            prest.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        return user;
    }


    public void close() {
        try {
            con.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }




    private String hashPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes("UTF-8"));

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();

    }

    public List<Feed> getFeedsForUser(String user) {
        try{
            String sql = "SELECT * FROM Feed WHERE Id = (SELECT Feedid FROM SubscribedToFeed WHERE Userid = ?)";
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setString(1, user);
            ResultSet rs = prest.executeQuery();
            while (rs.next()){

//                user.setLoginname(rs.getInt("Id"));
//                user.setEmail(rs.getString("description"));
//                user.setPassword(rs.getString("html"));
//                user.setId(rs.getString("css"));
//                user.setUsername(rs.getInt("ownerid"));

            }
            prest.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        return null;

    }
}
