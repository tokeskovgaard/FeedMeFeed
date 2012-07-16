package dk.tokebroedsted.administration.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class AdministrationEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
        final RootPanel rootPanel = RootPanel.get("gwt");

        Label titel = new Label("Yeeha!");
        rootPanel.add(titel);


        AdministrationServiceAsync instance = AdministrationService.App.getInstance();
        instance.getMessage("Mojne", new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onSuccess(String result) {
                rootPanel.add(new Label(result));
            }
        });
    }
}
