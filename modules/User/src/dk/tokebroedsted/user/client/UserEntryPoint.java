package dk.tokebroedsted.user.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 16-07-12
 * Time: 16:08
 * To change this template use File | Settings | File Templates.
 */
public class UserEntryPoint implements EntryPoint{

    @Override
    public void onModuleLoad() {
        RootPanel rootPanel = RootPanel.get("gwt");
        rootPanel.add(new Label("Virker"));
    }
}
