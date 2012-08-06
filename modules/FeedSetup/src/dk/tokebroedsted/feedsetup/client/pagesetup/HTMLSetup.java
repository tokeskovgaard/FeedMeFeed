package dk.tokebroedsted.feedsetup.client.pagesetup;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import dk.tokebroedsted.feedsetup.client.FeedSetupView;
import dk.tokebroedsted.commons.client.models.InputGWT;
import dk.tokebroedsted.commons.client.models.QuestionGWT;

public class HTMLSetup extends FlowPanel {

    private FeedSetupView feedItemSetup;
    private TextArea html;
    private TextArea css;

    public HTMLSetup(final FeedSetupView feedItemSetup) {
        this.feedItemSetup = feedItemSetup;

        Label htmlTitel = new Label("HTML opsætning");
        htmlTitel.setStyleName("title");
        add(htmlTitel);

//        add(new Label("<div class=\"feedItem\">"));
        html = new TextArea();
        html.setValue(feedItemSetup.getFeed().getHtml());
        html.setStyleName("html-setup");
        add(html);
//        add(new Label("</div>"));

        Label cssTitel = new Label("CSS opsætning");
        cssTitel.setStyleName("title");
        add(cssTitel);

//        add(new Label("<style type=\"text/css\">"));
        css = new TextArea();
        css.setValue(feedItemSetup.getFeed().getCss());
        css.setStyleName("css-setup");
        add(css);
//        add(new Label("</style>"));


        KeyUpHandler keyUpHandler = new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                feedItemSetup.getFeed().setCss(css.getValue());
                feedItemSetup.getFeed().setHtml(html.getValue());

                feedItemSetup.previewView.updatePreview(generatePreviewHTML());
            }
        };
        html.addKeyUpHandler(keyUpHandler);
        css.addKeyUpHandler(keyUpHandler);
    }

    private HTML generatePreviewHTML() {
        String cssText = "<style type=\"text/css\">" + css.getText() + "</style>";

        //TODO make sure it is safe html
        String htmlText = html.getText();
        for (InputGWT feedInput : feedItemSetup.getFeed().getInputs()) {
            htmlText = htmlText.replaceAll(feedInput.getVariableId(), feedInput.getName());
        }

        for (QuestionGWT question : feedItemSetup.getFeed().getQuestions()) {
            htmlText = htmlText.replaceAll(question.getVariableId(), question.getName());
        }

        return new HTML(cssText + htmlText);
    }
}
