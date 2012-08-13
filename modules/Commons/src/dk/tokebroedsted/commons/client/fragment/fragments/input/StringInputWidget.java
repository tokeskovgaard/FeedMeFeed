package dk.tokebroedsted.commons.client.fragment.fragments.input;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineHTML;
import dk.tokebroedsted.commons.client.models.InputGWT;
import dk.tokebroedsted.commons.client.models.InputItemGWT;

public class StringInputWidget extends Composite {
    interface StringInputFragmentUiBinder extends UiBinder<InlineHTML, StringInputWidget> {
    }

    private static StringInputFragmentUiBinder ourUiBinder = GWT.create(StringInputFragmentUiBinder.class);

    @UiField InlineHTML inputFragment;

    public StringInputWidget(InputGWT inputGWT, InputItemGWT inputItemGWT) {
        initWidget(ourUiBinder.createAndBindUi(this));
        inputFragment.setHTML(inputItemGWT.getValue());
    }
}