package dk.tokebroedsted.commons.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import dk.tokebroedsted.commons.client.models.QuestionItemGWT;

public interface FeedItemServiceAsync {

    void save(QuestionItemGWT questionItemGWT, AsyncCallback<Boolean> callback);
}
