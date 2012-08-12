package dk.tokebroedsted.commons.client.fragment.fragments;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import dk.tokebroedsted.commons.client.models.CalculationGWT;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 12-08-12
 * Time: 19:19
 * To change this template use File | Settings | File Templates.
 */
public class BoolCalculationFragment extends CalculationFragment<String> {
    interface BoolCalculationFragmentUiBinder extends UiBinder<Label, BoolCalculationFragment> {
    }

    private static BoolCalculationFragmentUiBinder ourUiBinder = GWT.create(BoolCalculationFragmentUiBinder.class);

    private CalculationGWT calculationGWT;

    @UiField Label boolFragment;

    public BoolCalculationFragment(CalculationGWT calculationGWT) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.calculationGWT = calculationGWT;
    }

    @Override
    public CalculationGWT getCalculationGWT() {
        return calculationGWT;
    }

    @Override
    public void setValue(String value) {
        boolFragment.setText(value);
    }
}