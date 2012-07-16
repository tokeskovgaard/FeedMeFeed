package dk.tokebroedsted.user.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
