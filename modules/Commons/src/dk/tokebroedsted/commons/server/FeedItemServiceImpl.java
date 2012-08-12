package dk.tokebroedsted.commons.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import dk.tokebroedsted.commons.client.FeedItemService;
import dk.tokebroedsted.commons.client.models.QuestionItemGWT;
import dk.tokebroedsted.hibernate.ModelFactory;

public class FeedItemServiceImpl extends RemoteServiceServlet implements FeedItemService {

    @Override
    public Boolean save(QuestionItemGWT questionItemGWT) {
        ModelFactory.save(questionItemGWT);
        return true;
    }
}