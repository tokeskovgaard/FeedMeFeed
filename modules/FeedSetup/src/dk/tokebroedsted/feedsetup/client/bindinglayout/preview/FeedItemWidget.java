package dk.tokebroedsted.feedsetup.client.bindinglayout.preview;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.InlineHTML;
import dk.tokebroedsted.commons.client.models.*;

import java.util.LinkedList;

public class FeedItemWidget extends Composite {
    interface FeedItemWidgetUiBinder extends UiBinder<HTML, FeedItemWidget> {
    }

    private static FeedItemWidgetUiBinder ourUiBinder = GWT.create(FeedItemWidgetUiBinder.class);

    @UiField HTML rootPanel;

    public FeedItemWidget(LinkedList<FeedWidget.Fragment> htmlFragments, FeedItemGWT feedItemGwt) {
        initWidget(ourUiBinder.createAndBindUi(this));

        StringBuilder html = new StringBuilder();
        for (FeedWidget.Fragment fragment : htmlFragments) {
            if (fragment instanceof FeedWidget.PureFragment) {
                html.append(((FeedWidget.PureFragment) fragment).getPureHtml());
            } else if (fragment instanceof FeedWidget.InputFragment) {
                InputGWT inputGWT = ((FeedWidget.InputFragment) fragment).getInputGWT();
                InputItemGWT inputItem = feedItemGwt.getInputItem(inputGWT.getVariableId());
                InlineHTML inputHtml = new InlineHTML(inputItem.getValue());
                inputHtml.setStyleName(inputItem.getInputGWT().getName());
                html.append(inputHtml);
            } else if (fragment instanceof FeedWidget.QuestionFragment) {
                FeedWidget.QuestionFragment questionFragment = (FeedWidget.QuestionFragment) fragment;
                QuestionGWT questionGWT = questionFragment.getQuestionGWT();
                QuestionItemGWT questionItem = feedItemGwt.getQuestionItem(questionGWT);
                if (questionGWT.getType().equals(QuestionGWT.Type.bool)) {

                }
            } else if (fragment instanceof FeedWidget.CalculationFragment) {
                FeedWidget.CalculationFragment calculationFragment = (FeedWidget.CalculationFragment) fragment;
                CalculationGWT calculationGwt = calculationFragment.getCalculationGwt();
                CalculationValueGWT calculationValue = feedItemGwt.getCalculationValue(calculationGwt.getVariableId());
                if (calculationValue != null) {
                    html.append(calculationValue.getValue());
                }
            }
        }

        rootPanel.setHTML(html.toString());
    }
}