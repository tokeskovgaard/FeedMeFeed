package dk.tokebroedsted.commons.converters;

import dk.tokebroedsted.commons.client.models.InputGWT;
import dk.tokebroedsted.commons.client.models.InputItemGWT;
import dk.tokebroedsted.hibernate.tables.FeedItemInput;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 19-07-12
 * Time: 00:09
 * To change this template use File | Settings | File Templates.
 */
public class InputItemConverter implements Converter<InputItemGWT, FeedItemInput> {
    @Override
    public FeedItemInput toServerObject(InputItemGWT inputItemGWT) {
        return null;
    }

    @Override
    public InputItemGWT toGwtObject(FeedItemInput feedItemInput) {
        InputConverter inputConverter = new InputConverter();
        InputGWT inputGWT = inputConverter.toGwtObject(feedItemInput.getFeedInput());

        InputItemGWT inputItemGWT = new InputItemGWT(inputGWT);
        inputItemGWT.setValue(feedItemInput.getValue());
        return inputItemGWT;
    }
}
