package dk.tokebroedsted.commons.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 19-07-12
 * Time: 11:06
 * To change this template use File | Settings | File Templates.
 */
public abstract class DialogExt extends DialogBox {


    protected abstract Widget getBody();

    protected abstract void onSave();
}
