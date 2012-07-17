package dk.tokebroedsted.objects;

import dk.tokebroedsted.hibernate.tables.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class UserCookie {

    public static final int SECONDS_PER_YEAR = 60*60*24*365;
    private HttpServletResponse resp;
    private HttpServletRequest req;

    public UserCookie(HttpServletRequest req, HttpServletResponse resp) {
        this.resp = resp;
        this.req = req;
    }

    public void updateSession() {
        Cookie[] cookies = req.getCookies();
        String username = "";
        String passwordHash = "";
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("password")) {
                    passwordHash = cookies[i].getValue();
                }
                if (cookies[i].getName().equals("user")) {
                    username = cookies[i].getValue();
                }
            }
        }
        if(!username.equals("") && !passwordHash.equals("")) {
            DatabaseHandler dbHandler = new DatabaseHandler();
            User user = dbHandler.getUser(username);
            try {
                if(passwordHash.equals(hash(user.getUsername() + user.getPassword()))) {
                    HttpSession session = req.getSession(true);
                    session.setAttribute("user",username);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }

    public void setCookie(User user) {
        try {
            Cookie username = new Cookie("user", user.getLoginname());
            Cookie password = new Cookie("password", hash(user.getUsername() + user.getPassword()));
            username.setMaxAge(SECONDS_PER_YEAR);
            password.setMaxAge(SECONDS_PER_YEAR);
            resp.addCookie(username);
            resp.addCookie(password);
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    public void crashCookieAndSession() {
        HttpSession session = req.getSession(true);
        session.invalidate();
        try {
            Cookie username = new Cookie("user", "");
            Cookie password = new Cookie("password", "");
            username.setMaxAge(0);
            password.setMaxAge(0);
            resp.addCookie(username);
            resp.addCookie(password);
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    private String generateSessionId() throws UnsupportedEncodingException {
        String uid = new java.rmi.server.UID().toString(); // guaranteed unique
        return URLEncoder.encode(uid, "UTF-8"); // encode any special chars
    }

    private String hash(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(input.getBytes("UTF-8"));

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();

    }
    //TODO: Upgrade for security for session hoaxing
    public void updateAndCheckSession(String referrerPage) throws IOException {
        updateSession();
        HttpSession session = req.getSession(true);
        if(session.getAttribute("user") == null) {
            Cookie referrer = new Cookie("referrer", referrerPage);
            resp.addCookie(referrer);
            resp.sendRedirect("Login");

        }

    }
}
