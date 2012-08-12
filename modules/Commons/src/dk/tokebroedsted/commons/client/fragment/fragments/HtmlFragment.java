package dk.tokebroedsted.commons.client.fragment.fragments;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.InlineHTML;

public class HtmlFragment extends Fragment<String> {
    interface HtmlFragmentUiBinder extends UiBinder<InlineHTML, HtmlFragment> {
    }

    private static HtmlFragmentUiBinder ourUiBinder = GWT.create(HtmlFragmentUiBinder.class);

    @UiField InlineHTML htmlFragment;

    public HtmlFragment(String html) {
        initWidget(ourUiBinder.createAndBindUi(this));
        htmlFragment.setHTML(html);
    }

    @Override
    public void setValue(String value) {
        htmlFragment.setHTML(value);
    }

    public String getHtml() {
        return htmlFragment.getHTML();
    }
}
