package dk.tokebroedsted.administration.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import dk.tokebroedsted.administration.client.AdministrationService;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.InputGWT;
import dk.tokebroedsted.commons.converters.FeedConverter;
import dk.tokebroedsted.hibernate.ModelFactory;
import dk.tokebroedsted.hibernate.tables.Feed;
import dk.tokebroedsted.hibernate.tables.FeedInput;
import dk.tokebroedsted.hibernate.tables.User;

import java.util.ArrayList;
import java.util.Set;

public class AdministrationServiceImpl extends RemoteServiceServlet implements AdministrationService {


    @Override
    public ArrayList<FeedGWT> getOwnedFeeds() {
        User user = ModelFactory.getUser("toke");
        Set<Feed> createdFeeds = user.getCreatedFeeds();

        ArrayList<FeedGWT> ownedFeeds = new ArrayList<FeedGWT>();
        FeedConverter feedConverter = new FeedConverter();
        for (Feed createdFeed : createdFeeds) {
            FeedGWT feedGWT = feedConverter.toGwtObject(createdFeed);
            ownedFeeds.add(feedGWT);
        }

        return ownedFeeds;
    }

    @Override
    public String saveFeed(FeedGWT feedGWT) {
        User owner = ModelFactory.getUser("toke");

        Feed feed;
        if (feedGWT.getFeedId() != null) {
            feed = ModelFactory.getFeed(feedGWT.getFeedId());
        } else
            feed = new Feed();


        feed.setOwner(owner);
        feed.setTitle(feedGWT.getTitle());
        feed.setCss(feedGWT.getCss());
        feed.setHtml(feedGWT.getHtml());
        ModelFactory.save(feed);

        for (InputGWT inputGWT : feedGWT.getInputs()) {
            createFeedInput(feed, inputGWT);
        }

        return "Yee";
    }

    private void createFeedInput(Feed feed, InputGWT inputGWT) {
        FeedInput.Type type;
        switch (inputGWT.getType()) {
            case string:
                type = FeedInput.Type.string;
                break;
            default:
                throw new RuntimeException("Found unexpected type");
        }

        FeedInput feedInput = new FeedInput();
        feedInput.setName(inputGWT.getName());
        feedInput.setType(type);
        feedInput.setFeed(feed);

        ModelFactory.save(feedInput);
    }

}