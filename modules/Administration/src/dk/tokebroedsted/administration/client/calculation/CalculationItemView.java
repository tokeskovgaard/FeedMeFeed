package dk.tokebroedsted.administration.client.calculation;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import dk.tokebroedsted.administration.client.FeedSetupView;
import dk.tokebroedsted.administration.client.model.Calculation;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 16-07-12
 * Time: 19:34
 * To change this template use File | Settings | File Templates.
 */
public class CalculationItemView extends FlowPanel {
    private FeedSetupView feedItemSetup;

    public CalculationItemView(FeedSetupView feedItemSetup) {
        this.feedItemSetup = feedItemSetup;
        setStyleName("calculation-view");
    }

    public void updateView() {
        clear();

        add(new Label("Udregninger"));
        for (Calculation calculation : feedItemSetup.getFeed().calculations) {
            add(new Label(calculation.getName()));
        }
        Button userInput = new Button("Tilføj ny beregning");
        add(userInput);
    }
}
