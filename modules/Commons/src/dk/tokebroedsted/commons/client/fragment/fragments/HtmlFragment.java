package dk.tokebroedsted.commons.client.fragment.fragments;

import com.google.gwt.user.client.ui.Widget;

public class HtmlFragment implements Fragment<String> {

    private String html;

    public HtmlFragment(String html) {
        this.html = html;
    }

    @Override
    public Widget createFragment(String value) {
        return new HtmlFragmentWidget(html);
    }
}
