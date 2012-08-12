package dk.tokebroedsted.commons.client.preview;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import dk.tokebroedsted.commons.client.fragment.fragments.BoolCalculationFragment;
import dk.tokebroedsted.commons.client.fragment.fragments.CalculationFragment;
import dk.tokebroedsted.commons.client.fragment.fragments.Fragment;
import dk.tokebroedsted.commons.client.fragment.fragments.HtmlFragment;
import dk.tokebroedsted.commons.client.fragment.fragments.input.InputFragment;
import dk.tokebroedsted.commons.client.fragment.fragments.input.StringInputFragment;
import dk.tokebroedsted.commons.client.fragment.fragments.question.BoolQuestionFragment;
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

        Widget result;
        for (Fragment fragment : htmlFragments) {
            if (fragment instanceof HtmlFragment) {
                rootPanel.add(new HtmlFragment(((HtmlFragment) fragment).getHtml()));
            } else if (fragment instanceof InputFragment) {
                rootPanel.add(getAdjustedInputFragment(feedItemGwt, (InputFragment) fragment));
            } else if (fragment instanceof QuestionFragment) {
                rootPanel.add(getAdjustedQuestionFragment(feedItemGwt, (QuestionFragment) fragment));
            } else if (fragment instanceof CalculationFragment) {
                rootPanel.add(getAdjustedCalculationFragment(feedItemGwt, (CalculationFragment) fragment));
            }
        }
    }

    private InputFragment getAdjustedInputFragment(FeedItemGWT feedItemGWT, InputFragment fragment) {
        InputGWT inputGWT = fragment.getInputGWT();
        InputItemGWT inputItem = feedItemGWT.getInputItem(inputGWT.getVariableId());

        StringInputFragment fragmentCopy = new StringInputFragment(inputGWT);
        fragmentCopy.setValue(inputItem);
        return fragmentCopy;
    }

    private CalculationFragment getAdjustedCalculationFragment(FeedItemGWT feedItemGwt, CalculationFragment fragment) {
        CalculationGWT calculationGwt = fragment.getCalculationGWT();
        CalculationValueGWT calculationValue = feedItemGwt.getCalculationValue(calculationGwt.getVariableId());

        BoolCalculationFragment fragmentCopy = new BoolCalculationFragment(calculationGwt);
        if (calculationValue != null)
            fragmentCopy.setValue(calculationValue.getValue());

        return fragmentCopy;
    }

    private QuestionFragment getAdjustedQuestionFragment(FeedItemGWT feedItemGwt, QuestionFragment fragment) {
        QuestionGWT questionGWT = fragment.getQuestionGWT();
        QuestionItemGWT questionItem = feedItemGwt.getQuestionItem(questionGWT);

        BoolQuestionFragment fragmentCopy = new BoolQuestionFragment(questionGWT);
        fragmentCopy.setValue(questionItem);
        return fragmentCopy;
    }
}