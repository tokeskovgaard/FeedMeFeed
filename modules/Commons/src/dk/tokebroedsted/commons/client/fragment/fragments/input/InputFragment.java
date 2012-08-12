package dk.tokebroedsted.commons.client.fragment.fragments.input;

import dk.tokebroedsted.commons.client.fragment.fragments.Fragment;
import dk.tokebroedsted.commons.client.models.InputGWT;
import dk.tokebroedsted.commons.client.models.InputItemGWT;

public abstract class InputFragment extends Fragment<InputItemGWT> {

    public abstract InputGWT getInputGWT();
}
