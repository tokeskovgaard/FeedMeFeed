package dk.tokebroedsted.commons.client.fragment.fragments;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.InlineHTML;

public class HtmlFragmentWidget extends Composite {
    interface HtmlFragmentUiBinder extends UiBinder<HTML, HtmlFragmentWidget> {
    }

    private static HtmlFragmentUiBinder ourUiBinder = GWT.create(HtmlFragmentUiBinder.class);

    @UiField InlineHTML htmlFragment;

    public HtmlFragmentWidget(String html) {
        initWidget(ourUiBinder.createAndBindUi(this));
        htmlFragment.setHTML(html);
    }
}
