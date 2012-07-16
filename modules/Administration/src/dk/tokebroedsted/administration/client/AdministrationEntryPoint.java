package dk.tokebroedsted.administration.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class AdministrationEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        RootPanel rootPanel = RootPanel.get("gwt");
        rootPanel.add(new Label("Yeeha!"));
    }
}
