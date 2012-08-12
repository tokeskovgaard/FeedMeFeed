package dk.tokebroedsted.feedsetup.client.edit.calculation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.commons.client.models.CalculationGWT;

public class CalculationEditDialog extends DialogBox {
    private Command saveCommand;

    interface CalculationEditDialogUiBinder extends UiBinder<FlowPanel, CalculationEditDialog> {
    }

    private static CalculationEditDialogUiBinder ourUiBinder = GWT.create(CalculationEditDialogUiBinder.class);

    @UiField TextBox name;
    @UiField ListBox type;
    @UiField TextArea calculation;

    private CalculationGWT calculationGWT;


    public CalculationEditDialog(CalculationGWT calculationGWT, Command saveCommand) {
        setWidget(ourUiBinder.createAndBindUi(this));
        this.calculationGWT = calculationGWT;
        this.saveCommand = saveCommand;

        name.setValue(calculationGWT.getName());
        calculation.setValue(calculationGWT.getCalculation());

        CalculationGWT.Type[] values = CalculationGWT.Type.values();
        int selectedIndex = 0;
        for (int i = 0; i < values.length; i++) {
            CalculationGWT.Type value = values[i];
            if (value.equals(calculationGWT.getType())) {
                selectedIndex = i;
            }
            type.addItem(value.name());
        }
        type.setSelectedIndex(selectedIndex);
    }

    @UiHandler("save")
    void save(ClickEvent e) {
        calculationGWT.setName(name.getValue());
        calculationGWT.setCalculation(calculation.getValue());
        saveCommand.execute();
        hide();
    }

    @UiHandler("cancel")
    void cancel(ClickEvent e) {
        hide();
    }
}