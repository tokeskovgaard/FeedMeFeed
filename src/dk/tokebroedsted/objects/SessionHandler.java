package dk.tokebroedsted.objects;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: mads
 * Date: 7/19/12
 * Time: 1:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class SessionHandler {

    public static String getCurrentUser(HttpServletRequest req) {
        //HttpServletRequest request = context.getThreadLocalRequest();
        HttpSession session = req.getSession();
        Cookie[] cookies = req.getCookies();
        //TODO: Make a decent check
        String user = (String)session.getAttribute("user");
        if(user == null){
            for(Cookie c : cookies) {
                if(c.getName().equals("user")) {
                    user = c.getValue();
                    session.setAttribute("user", c.getValue());
                    break;
                }
            }
        }
        return user;
    }
}
