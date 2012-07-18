package dk.tokebroedsted.administration.client.calculation;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import dk.tokebroedsted.administration.client.FeedSetupView;
import dk.tokebroedsted.commons.client.models.CalculationGWT;

public class CalculationItemView extends FlowPanel {
    private FeedSetupView feedItemSetup;

    public CalculationItemView(FeedSetupView feedItemSetup) {
        this.feedItemSetup = feedItemSetup;
        setStyleName("calculation-view");
    }

    public void updateView() {
        clear();

        add(new Label("Udregninger"));
        for (CalculationGWT calculation : feedItemSetup.getFeed().getCalculations()) {
            add(new Label(calculation.getName() + " - " + calculation.getVariableId()));
        }
        Button userInput = new Button("Tilføj ny beregning");
        add(userInput);
    }
}
