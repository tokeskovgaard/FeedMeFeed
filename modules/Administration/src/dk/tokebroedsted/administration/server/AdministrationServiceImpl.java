package dk.tokebroedsted.administration.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import dk.tokebroedsted.administration.client.AdministrationService;
import dk.tokebroedsted.hibernate.HibernateUtil;
import dk.tokebroedsted.hibernate.tables.Feed;
import dk.tokebroedsted.hibernate.tables.FeedInput;

public class AdministrationServiceImpl extends RemoteServiceServlet implements AdministrationService {

    // Implementation of sample interface method
    public String getMessage() {
        return "Der var hul";
    }

    @Override
    public String saveFeed(dk.tokebroedsted.administration.client.model.Feed feedGWT) {

        Feed feed = new Feed();

        feed.setCss(feedGWT.getCss());
        feed.setHtml(feedGWT.getHtml());

        for (dk.tokebroedsted.administration.client.model.FeedInput feedInputGWT : feedGWT.feedInputList) {
            FeedInput feedInput = new FeedInput();
        }

//        feed.setFeedInputs();

        HibernateUtil.saveSomething(feed);

        return "";
    }
}