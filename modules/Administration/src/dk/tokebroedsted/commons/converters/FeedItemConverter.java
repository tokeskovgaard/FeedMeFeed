package dk.tokebroedsted.commons.converters;

import dk.tokebroedsted.commons.client.models.FeedItemGWT;
import dk.tokebroedsted.commons.client.models.InputItemGWT;
import dk.tokebroedsted.hibernate.tables.FeedItem;
import dk.tokebroedsted.hibernate.tables.FeedItemInput;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 19-07-12
 * Time: 00:01
 * To change this template use File | Settings | File Templates.
 */
public class FeedItemConverter implements Converter<FeedItemGWT, FeedItem> {

    @Override
    public FeedItem toServerObject(FeedItemGWT feedItemGWT) {
        return null;
    }

    @Override
    public FeedItemGWT toGwtObject(FeedItem feedItem) {
        ArrayList<InputItemGWT> inputItemGWTs = new ArrayList<InputItemGWT>();
        InputItemConverter inputItemConverter = new InputItemConverter();
        for (FeedItemInput feedItemInput : feedItem.getFeedItemInputs()) {
            inputItemGWTs.add(inputItemConverter.toGwtObject(feedItemInput));
        }

        FeedItemGWT feedItemGWT = new FeedItemGWT(feedItem.getFeed().getId());
        feedItemGWT.setInputItems(inputItemGWTs);

        return feedItemGWT;
    }
}
