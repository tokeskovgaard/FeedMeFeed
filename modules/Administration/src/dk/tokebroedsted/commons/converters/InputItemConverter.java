package dk.tokebroedsted.commons.converters;

import dk.tokebroedsted.commons.client.models.InputGWT;
import dk.tokebroedsted.commons.client.models.InputItemGWT;
import dk.tokebroedsted.hibernate.tables.FeedInput;
import dk.tokebroedsted.hibernate.tables.FeedItem;
import dk.tokebroedsted.hibernate.tables.FeedItemInput;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 19-07-12
 * Time: 00:09
 * To change this template use File | Settings | File Templates.
 */
public class InputItemConverter implements Converter<InputItemGWT, FeedItemInput> {
    private FeedItem feedItem;

    public static InputItemConverter toServer(FeedItem feedItem) {
        return new InputItemConverter(feedItem);
    }

    public static InputItemConverter toGwt() {
        return new InputItemConverter();
    }

    private InputItemConverter(FeedItem feedItem) {
        this.feedItem = feedItem;
    }

    public InputItemConverter() {
    }

    @Override
    public FeedItemInput toServerObject(InputItemGWT inputItemGWT) {

        InputConverter inputConverter = InputConverter.toServer(feedItem.getFeed());
        FeedInput input = inputConverter.toServerObject(inputItemGWT.getInputGWT());

        FeedItemInput feedItemInput = new FeedItemInput();
        feedItemInput.setFeedItem(feedItem);
        feedItemInput.setValue(inputItemGWT.getValue());
        feedItemInput.setFeedInput(input);
        return feedItemInput;
    }

    @Override
    public InputItemGWT toGwtObject(FeedItemInput feedItemInput) {
        InputConverter inputConverter = InputConverter.toGwt();
        InputGWT inputGWT = inputConverter.toGwtObject(feedItemInput.getFeedInput());

        InputItemGWT inputItemGWT = new InputItemGWT(inputGWT);
        inputItemGWT.setValue(feedItemInput.getValue());
        return inputItemGWT;
    }
}
