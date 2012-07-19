package dk.tokebroedsted.commons.server.converters;

import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.InputGWT;
import dk.tokebroedsted.hibernate.ModelFactory;
import dk.tokebroedsted.hibernate.tables.Feed;
import dk.tokebroedsted.hibernate.tables.FeedInput;
import dk.tokebroedsted.hibernate.tables.User;

import java.util.ArrayList;

public class FeedConverter implements Converter<FeedGWT, Feed> {

    @Override
    public Feed toServerObject(FeedGWT feedGWT) {
        Feed feed;

        if (feedGWT.getFeedId() != null) {
            feed = new Feed(feedGWT.getFeedId());
        } else {
            feed = new Feed();
        }
        User owner = ModelFactory.getUser("toke");

        feed.setOwner(owner);
        feed.setTitle(feedGWT.getTitle());
        feed.setCss(feedGWT.getCss());
        feed.setHtml(feedGWT.getHtml());

        InputConverter inputConverter = InputConverter.toServer(feed);

        ArrayList<FeedInput> feedInputs = new ArrayList<FeedInput>();
        for (InputGWT inputGWT : feedGWT.getInputs()) {
            feedInputs.add(inputConverter.toServerObject(inputGWT));
        }
        feed.setFeedInputs(feedInputs);

        return feed;
    }


    @Override
    public FeedGWT toGwtObject(Feed feed) {

        InputConverter inputConverter = InputConverter.toGwt();

        ArrayList<InputGWT> inputs = new ArrayList<InputGWT>();
        for (FeedInput input : feed.getFeedInputs()) {
            inputs.add(inputConverter.toGwtObject(input));
        }

        FeedGWT feedGWT = new FeedGWT();
        feedGWT.setTitle(feed.getTitle());
        feedGWT.setCss(feed.getCss() == null ? "" : feed.getCss());
        feedGWT.setHTML(feed.getHtml() == null ? "" : feed.getHtml());
        feedGWT.setFeedId(feed.getId());
        feedGWT.setInputs(inputs);

        return feedGWT;
    }
}
