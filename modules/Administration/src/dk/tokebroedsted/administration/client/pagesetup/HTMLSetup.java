package dk.tokebroedsted.administration.client.pagesetup;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import dk.tokebroedsted.administration.client.model.InputItem;
import dk.tokebroedsted.administration.client.view.FeedItemSetup;

public class HTMLSetup  extends FlowPanel {

    private FeedItemSetup feedItemSetup;
    private TextArea html;
    private TextArea css;

    public HTMLSetup(final FeedItemSetup feedItemSetup) {
        this.feedItemSetup = feedItemSetup;

        Label htmlTitel = new Label("HTML opsætning");
        htmlTitel.setStyleName("title");
        add(htmlTitel);

        html = new TextArea();
        html.setStyleName("html-setup");
        add(html);

        Label cssTitel = new Label("CSS opsætning");
        cssTitel.setStyleName("title");
        add(cssTitel);

        css = new TextArea();
        css.setStyleName("css-setup");
        add(css);


        ValueChangeHandler<String> valueChangeHandler = new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> stringValueChangeEvent) {
                feedItemSetup.previewView.updatePreview(generatePreviewHTML());
            }
        };
        KeyPressHandler keyPressHandler = new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (KeyCodes.KEY_ENTER == event.getUnicodeCharCode()) {
                    feedItemSetup.previewView.updatePreview(generatePreviewHTML());
                }
            }
        };

        html.addValueChangeHandler(valueChangeHandler);
        html.addKeyPressHandler(keyPressHandler);

        css.addValueChangeHandler(valueChangeHandler);
        css.addKeyPressHandler(keyPressHandler);
    }

    private HTML generatePreviewHTML(){
        String cssText = "<style type=\"text/css\">"+css.getText()+"</style>";

        //TODO make sure it is safe html
        String htmlText = html.getText();
        for (InputItem inputItem : feedItemSetup.getInputItems()) {
            htmlText = htmlText.replaceAll(inputItem.getVariableId(),inputItem.getName());
        }

        return new HTML(cssText+htmlText);
    }
}
