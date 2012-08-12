package dk.tokebroedsted.commons.client.fragment.fragments;

import com.google.gwt.user.client.ui.Composite;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 12-08-12
 * Time: 18:06
 * To change this template use File | Settings | File Templates.
 */
public abstract class Fragment<T> extends Composite {
    public abstract void setValue(T value);
}
