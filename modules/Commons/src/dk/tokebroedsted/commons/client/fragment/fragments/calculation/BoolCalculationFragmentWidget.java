package dk.tokebroedsted.commons.client.fragment.fragments.calculation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import dk.tokebroedsted.commons.client.models.CalculationGWT;
import dk.tokebroedsted.commons.client.models.CalculationValueGWT;

public class BoolCalculationFragmentWidget extends Composite {
    interface BoolCalculationFragmentUiBinder extends UiBinder<Label, BoolCalculationFragmentWidget> {
    }

    private static BoolCalculationFragmentUiBinder ourUiBinder = GWT.create(BoolCalculationFragmentUiBinder.class);


    @UiField Label boolFragment;

    public BoolCalculationFragmentWidget(CalculationGWT calculationGWT, CalculationValueGWT value) {
        initWidget(ourUiBinder.createAndBindUi(this));
        if (value != null)
            boolFragment.setText(value.getValue());
    }
}