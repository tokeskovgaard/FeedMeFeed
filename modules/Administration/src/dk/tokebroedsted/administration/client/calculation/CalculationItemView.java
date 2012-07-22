package dk.tokebroedsted.administration.client.calculation;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
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
        userInput.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                final DialogBox dialog = new DialogBox();
                dialog.setStyleName("dialog-with-buttons");
                dialog.center();

                FlowPanel dialogContent = new FlowPanel();

                dialogContent.add(new Label("Navn"));
                final TextBox nameInput = new TextBox();
                nameInput.setStyleName("name-input");
                dialogContent.add(nameInput);

                dialogContent.add(new Label("Udregning:"));
                final TextArea calculationInput = new TextArea();
                calculationInput.setStyleName("calculation-input");
                dialogContent.add(calculationInput);


                InlineLabel saveLabel = new InlineLabel("Gem");
                saveLabel.setStyleName("link-label");
                saveLabel.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        CalculationGWT calculationGWT = new CalculationGWT(null, nameInput.getValue(), calculationInput.getValue());
                        feedItemSetup.getFeed().getCalculations().add(calculationGWT);
                        updateView();
                        dialog.hide();
                    }
                });
                dialogContent.add(saveLabel);

                InlineLabel cancelLabel = new InlineLabel("Annuller");
                cancelLabel.setStyleName("link-label");
                cancelLabel.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        dialog.hide();
                    }
                });
                dialogContent.add(cancelLabel);

                dialog.add(dialogContent);
                dialog.show();
            }
        });
        add(userInput);
    }
}
