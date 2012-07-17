package dk.tokebroedsted.administration.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import dk.tokebroedsted.administration.client.AdministrationService;
import dk.tokebroedsted.hibernate.HibernateUtil;
import dk.tokebroedsted.hibernate.tables.Feed;

public class AdministrationServiceImpl extends RemoteServiceServlet implements AdministrationService {

    // Implementation of sample interface method
    public String getMessage() {
        return "Der var hul";
    }

    @Override
    public String saveFeed() {
        Feed feed = new Feed();
        feed.setCss("CSS");
        feed.setHtml("TEST");
        HibernateUtil.saveSomething(feed);
        return "";
    }
}