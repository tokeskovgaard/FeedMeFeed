package dk.tokebroedsted.commons.client.preview;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import dk.tokebroedsted.commons.client.models.*;

import java.util.LinkedList;
import java.util.List;

public class FeedWidget extends Composite {
    interface FeedWidgetUiBinder extends UiBinder<HTMLPanel, FeedWidget> {
    }

    private static FeedWidgetUiBinder ourUiBinder = GWT.create(FeedWidgetUiBinder.class);


    @UiField HTML css;
    @UiField FlowPanel feedItems;

    public FeedWidget() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void renderFeedItems(FeedGWT feedGWT, List<FeedItemGWT> feedItemGWTs) {
        css.setHTML("<style type=\"text/css\">" + feedGWT.getCss() + "</style>");

        LinkedList<Fragment> fragmentList = buildFragmentList(feedGWT);

        feedItems.clear();

        for (FeedItemGWT feedItemGWT : feedItemGWTs) {
            FeedItemWidget feedItemWidget = new FeedItemWidget(fragmentList, feedItemGWT);
            feedItems.add(feedItemWidget);
        }
    }


    private LinkedList<Fragment> buildFragmentList(FeedGWT feedGWT) {
        LinkedList<Fragment> fragmentList = new LinkedList<Fragment>();
        String html = feedGWT.getHtml();
        int index = 0;
        int newIndex;
        while ((newIndex = html.indexOf("<%", index)) != -1) {
            String pureHtml = html.substring(index, newIndex);
            fragmentList.add(new PureFragment(pureHtml));

            String remainingHTML = html.substring(newIndex);

            boolean foundMatch = false;
            for (InputGWT inputGwt : feedGWT.getInputs()) {
                String variableId = inputGwt.getVariableId();
                if (remainingHTML.startsWith(variableId)) {
                    index = newIndex + variableId.length();
                    fragmentList.add(new InputFragment(inputGwt));
                    foundMatch = true;
                }
            }
            if (foundMatch)
                continue;

            for (QuestionGWT questionGWT : feedGWT.getQuestions()) {
                String variableId = questionGWT.getVariableId();
                if (remainingHTML.startsWith(variableId)) {
                    index = newIndex + variableId.length();
                    fragmentList.add(new QuestionFragment(questionGWT));
                    foundMatch = true;
                }
            }
            if (foundMatch)
                continue;

            for (CalculationGWT calculationGwt : feedGWT.getCalculations()) {
                String variableId = calculationGwt.getVariableId();
                if (remainingHTML.startsWith(variableId)) {
                    index = newIndex + variableId.length();
                    fragmentList.add(new CalculationFragment(calculationGwt));
                    foundMatch = true;
                }
            }
            if (foundMatch)
                continue;

            pureHtml = html.substring(index, index + "<%".length());
            fragmentList.add(new PureFragment(pureHtml));
            index += "<%input ".length();
        }

        if (index < html.length()) {
            fragmentList.add(new PureFragment(html.substring(index, html.length())));
        }
        return fragmentList;
    }


    interface Fragment {

    }

    class PureFragment implements Fragment {
        private String pureHtml;

        public PureFragment(String pureHtml) {
            this.pureHtml = pureHtml;
        }

        public String getPureHtml() {
            return pureHtml;
        }
    }

    class InputFragment implements Fragment {

        private InputGWT inputGWT;

        public InputFragment(InputGWT inputGWT) {
            this.inputGWT = inputGWT;
        }

        public InputGWT getInputGWT() {
            return inputGWT;
        }
    }

    class CalculationFragment implements Fragment {

        private CalculationGWT calculationGwt;

        public CalculationFragment(CalculationGWT calculationGwt) {
            this.calculationGwt = calculationGwt;
        }

        public CalculationGWT getCalculationGwt() {
            return calculationGwt;
        }
    }

    class QuestionFragment implements Fragment {

        private QuestionGWT questionGWT;

        public QuestionFragment(QuestionGWT questionGWT) {
            this.questionGWT = questionGWT;
        }

        public QuestionGWT getQuestionGWT() {
            return questionGWT;
        }
    }
}