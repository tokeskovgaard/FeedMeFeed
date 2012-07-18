package dk.tokebroedsted.administration.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import dk.tokebroedsted.administration.client.AdministrationService;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.converters.FeedConverter;
import dk.tokebroedsted.hibernate.HibernateUtil;
import dk.tokebroedsted.hibernate.tables.Feed;

public class AdministrationServiceImpl extends RemoteServiceServlet implements AdministrationService {

    // Implementation of sample interface method
    public String getMessage() {
        return "Der var hul";
    }

    @Override
    public String saveFeed(FeedGWT feedGWT) {
        FeedConverter feedConverter = new FeedConverter();

        Feed feed = feedConverter.toServerObject(feedGWT);
        HibernateUtil.saveSomething(feed);
        return "Yee";
    }

}