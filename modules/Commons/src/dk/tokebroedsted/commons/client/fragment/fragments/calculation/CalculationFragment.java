package dk.tokebroedsted.commons.client.fragment.fragments.calculation;

import com.google.gwt.user.client.ui.Widget;
import dk.tokebroedsted.commons.client.fragment.fragments.Fragment;
import dk.tokebroedsted.commons.client.models.CalculationGWT;
import dk.tokebroedsted.commons.client.models.CalculationValueGWT;

public class CalculationFragment implements Fragment<CalculationValueGWT> {

    private CalculationGWT calculationGWT;

    public CalculationFragment(CalculationGWT calculationGWT) {
        this.calculationGWT = calculationGWT;
    }

    public CalculationGWT getCalculationGWT() {
        return calculationGWT;
    }

    @Override
    public Widget createFragment(CalculationValueGWT value) {
        if (CalculationGWT.Type.bool.equals(calculationGWT.getType())) {
            return new BoolCalculationFragmentWidget(calculationGWT, value);
        }
        throw new RuntimeException("Can't handle the passed CalculationValue");
    }
}
