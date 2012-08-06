package dk.tokebroedsted.commons.client.previewhelp;

import dk.tokebroedsted.commons.client.models.*;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 06-08-12
 * Time: 22:38
 * To change this template use File | Settings | File Templates.
 */
public class DummyDataFactory {

    public static FeedItemGWT createDummyFeedItemGwt(FeedGWT feedGWT) {
        FeedItemGWT dummyFeedItemGwt = new FeedItemGWT();

        for (InputGWT inputGWT : feedGWT.getInputs()) {
            InputItemGWT inputItemGWT = new InputItemGWT(inputGWT);
            inputItemGWT.setValue("[{" + inputGWT.getName() + "}]");
            dummyFeedItemGwt.getInputItems().add(inputItemGWT);
        }

        Random random = new Random();
        for (CalculationGWT calculationGWT : feedGWT.getCalculations()) {
            String value = "";
            switch (calculationGWT.getType()) {
                case bool:
                    value = random.nextInt() % 2 == 0 ? "true" : "false";
            }
            CalculationValueGWT calculationValueGWT = new CalculationValueGWT(calculationGWT.getVariableId(), value);
            dummyFeedItemGwt.getCalculationValues().add(calculationValueGWT);
        }
        return dummyFeedItemGwt;

        //TODO implement rest
    }
}
