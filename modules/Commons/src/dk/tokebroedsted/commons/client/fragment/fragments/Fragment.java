package dk.tokebroedsted.commons.client.fragment.fragments;

import com.google.gwt.user.client.ui.Widget;

public interface Fragment<T> {

    public Widget createFragment(T value);
}
