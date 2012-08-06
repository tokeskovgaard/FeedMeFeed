package dk.tokebroedsted.feedsetup.client.bindinglayout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import dk.tokebroedsted.commons.client.models.CalculationGWT;

public class CalculationListItem extends Composite {
    private CalculationGWT calculationGWT;
    private Command deleteCommand;

    interface CalculationListItemUiBinder extends UiBinder<FlowPanel, CalculationListItem> {
    }

    private static CalculationListItemUiBinder ourUiBinder = GWT.create(CalculationListItemUiBinder.class);
    @UiField InlineLabel name;

    public CalculationListItem(CalculationGWT calculationGWT) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.calculationGWT = calculationGWT;

        name.setText(calculationGWT.getName());
    }

    public void setDeleteCommand(Command deleteCommand) {
        this.deleteCommand = deleteCommand;
    }

    @UiHandler("deleteImage")
    void delete(ClickEvent e) {
        deleteCommand.execute();
    }

    @UiHandler("name")
    void editInputItem(ClickEvent e) {
        CalculationEditDialog inputEditDialog = new CalculationEditDialog(calculationGWT, new Command() {
            @Override
            public void execute() {
            }
        });
        inputEditDialog.show();
    }
}