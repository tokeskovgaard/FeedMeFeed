package dk.tokebroedsted.commons.client.fragment.fragments.input;

import com.google.gwt.user.client.ui.Widget;
import dk.tokebroedsted.commons.client.fragment.fragments.Fragment;
import dk.tokebroedsted.commons.client.models.InputGWT;
import dk.tokebroedsted.commons.client.models.InputItemGWT;

public class InputFragment implements Fragment<InputItemGWT> {

    private InputGWT inputGWT;

    public InputFragment(InputGWT inputGWT) {
        this.inputGWT = inputGWT;
    }

    public InputGWT getInputGWT() {
        return inputGWT;
    }

    @Override
    public Widget createFragment(InputItemGWT value) {
        if (InputGWT.Type.string.equals(inputGWT.getType())) {
            return new StringInputWidget(inputGWT, value);
        }
        throw new RuntimeException("Can't handle the passed InputItem");
    }
}
