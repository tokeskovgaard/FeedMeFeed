package dk.tokebroedsted.user.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import dk.tokebroedsted.user.client.UserService;

public class UserServiceImpl extends RemoteServiceServlet implements UserService {
    // Implementation of sample interface method

    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}