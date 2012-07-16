package dk.tokebroedsted.administration.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AdministrationServiceAsync {
    void getMessage(AsyncCallback<String> async);
}
