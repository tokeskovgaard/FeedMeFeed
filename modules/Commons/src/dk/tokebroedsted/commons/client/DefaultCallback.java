package dk.tokebroedsted.commons.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 18-07-12
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
public abstract class DefaultCallback<T> implements AsyncCallback<T> {
    @Override
    public void onFailure(Throwable caught) {
        Window.alert(caught.getMessage());
        throw new RuntimeException(caught);
    }
}
