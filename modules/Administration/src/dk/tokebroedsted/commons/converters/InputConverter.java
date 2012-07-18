package dk.tokebroedsted.commons.converters;

import dk.tokebroedsted.commons.client.models.InputGWT;
import dk.tokebroedsted.hibernate.tables.Feed;
import dk.tokebroedsted.hibernate.tables.FeedInput;

public class InputConverter implements Converter<InputGWT, FeedInput> {
    private Feed feed;

    public InputConverter(Feed feed) {
        this.feed = feed;
    }

    public InputConverter() {
    }

    @Override
    public FeedInput toServerObject(InputGWT inputGWT) {
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
        return feedInput;
    }

    @Override
    public InputGWT toGwtObject(FeedInput feedInput) {
        InputGWT.Type type;
        switch (feedInput.getType()) {
            case string:
                type = InputGWT.Type.string;
                break;
            default:
                throw new RuntimeException("Found unexpected type");
        }

        InputGWT inputGWT = new InputGWT();
        inputGWT.setName(feedInput.getName());
        inputGWT.setType(type);

        return inputGWT;
    }
}
