package dk.tokebroedsted.commons.client.preview;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.commons.client.fragment.fragments.Fragment;
import dk.tokebroedsted.commons.client.fragment.fragments.HtmlFragment;
import dk.tokebroedsted.commons.client.fragment.fragments.calculation.CalculationFragment;
import dk.tokebroedsted.commons.client.fragment.fragments.input.InputFragment;
import dk.tokebroedsted.commons.client.fragment.fragments.question.QuestionFragment;
import dk.tokebroedsted.commons.client.models.*;

import java.util.LinkedList;

public class FeedItemWidget extends Composite {
    interface FeedItemWidgetUiBinder extends UiBinder<FlowPanel, FeedItemWidget> {
    }

    private static FeedItemWidgetUiBinder ourUiBinder = GWT.create(FeedItemWidgetUiBinder.class);

    @UiField FlowPanel rootPanel;

    public FeedItemWidget(LinkedList<Fragment> htmlFragments, FeedItemGWT feedItemGwt) {
        initWidget(ourUiBinder.createAndBindUi(this));

        //TODO Problem with html tags being splitted because InnerHTML tags instead of raw html
        for (Fragment fragment : htmlFragments) {
            if (fragment instanceof HtmlFragment) {
                rootPanel.add(((HtmlFragment) fragment).createFragment(null));
            } else if (fragment instanceof InputFragment) {
                rootPanel.add(getFragmentWidget(feedItemGwt, (InputFragment) fragment));
            } else if (fragment instanceof QuestionFragment) {
                rootPanel.add(getFragmentWidget(feedItemGwt, (QuestionFragment) fragment));
            } else if (fragment instanceof CalculationFragment) {
                rootPanel.add(getFragmentWidget(feedItemGwt, (CalculationFragment) fragment));
            }
        }

        HTMLPanel htmlPanel = new HTMLPanel("<span class=\"<div id=\"test\"\\>Titel: <%input titel%></span>\n" +
                "<br />\n" +
                "Spg: <%question spg%>\n" +
                "<br />\n" +
                "Bool: <%calculation bool%>");
        rootPanel.add(htmlPanel);
    }

    private Widget getFragmentWidget(FeedItemGWT feedItemGWT, InputFragment fragment) {
        InputGWT inputGWT = fragment.getInputGWT();
        InputItemGWT inputItem = feedItemGWT.getInputItem(inputGWT.getVariableId());

        return fragment.createFragment(inputItem);
    }

    private Widget getFragmentWidget(FeedItemGWT feedItemGwt, CalculationFragment fragment) {
        CalculationGWT calculationGwt = fragment.getCalculationGWT();
        CalculationValueGWT calculationValue = feedItemGwt.getCalculationValue(calculationGwt.getVariableId());

        return fragment.createFragment(calculationValue);
    }

    private Widget getFragmentWidget(FeedItemGWT feedItemGwt, QuestionFragment fragment) {
        QuestionGWT questionGWT = fragment.getQuestionGWT();
        QuestionItemGWT questionItem = feedItemGwt.getQuestionItem(questionGWT);

        return fragment.createFragment(questionItem);
    }
}