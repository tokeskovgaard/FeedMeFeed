package dk.tokebroedsted.commons.client.fragment.fragments.input;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Widget;
import dk.tokebroedsted.commons.client.models.InputGWT;
import dk.tokebroedsted.commons.client.models.InputItemGWT;

public class StringInputFragment extends InputFragment {
    interface StringInputFragmentUiBinder extends UiBinder<InlineHTML, StringInputFragment> {
    }

    private static StringInputFragmentUiBinder ourUiBinder = GWT.create(StringInputFragmentUiBinder.class);

    private InputGWT inputGWT;
    @UiField InlineHTML inputFragment;

    public StringInputFragment(InputGWT inputGWT) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.inputGWT = inputGWT;
    }

    @Override
    public InputGWT getInputGWT() {
        return inputGWT;
    }

    @Override
    public void setValue(InputItemGWT inputItemGWT) {
        inputFragment.setHTML(inputItemGWT.getValue());
    }
}