package dk.tokebroedsted.commons.client.fragment;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import dk.tokebroedsted.commons.client.fragment.fragments.Fragment;
import dk.tokebroedsted.commons.client.fragment.fragments.HtmlFragment;
import dk.tokebroedsted.commons.client.fragment.fragments.calculation.CalculationFragment;
import dk.tokebroedsted.commons.client.fragment.fragments.input.InputFragment;
import dk.tokebroedsted.commons.client.fragment.fragments.question.QuestionFragment;
import dk.tokebroedsted.commons.client.models.CalculationGWT;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.InputGWT;
import dk.tokebroedsted.commons.client.models.QuestionGWT;

import java.util.LinkedList;

public class FragmentFactory {

    public static LinkedList<Fragment> buildFragmentList(FeedGWT feedGWT) {
        LinkedList<Fragment> fragmentList = new LinkedList<Fragment>();

        RegExp regExp = RegExp.compile("<%(input|question|calculation)\\s(.*?)%>");
        MatchResult matchResult;

        String remainingHtml = feedGWT.getHtml();
        while ((matchResult = regExp.exec(remainingHtml)) != null) {
            String htmlThatMatched = matchResult.getGroup(0);
            String type = matchResult.getGroup(1);
            String variableId = matchResult.getGroup(2);

            String passedHtml = remainingHtml.substring(0, matchResult.getIndex());
            fragmentList.add(new HtmlFragment(passedHtml));

            Fragment fragment = null;
            if ("input".equals(type)) {
                fragment = geInputFragment(feedGWT, variableId);
            } else if ("question".equals(type)) {
                fragment = getQuestionFragment(feedGWT, variableId);
            } else if ("calculation".equals(type)) {
                fragment = getCalculationFragment(feedGWT, variableId);
            }
            if (fragment == null) {
                fragment = new HtmlFragment(htmlThatMatched);
            }
            fragmentList.add(fragment);

            remainingHtml = remainingHtml.substring(matchResult.getIndex() + htmlThatMatched.length());
        }
        if (remainingHtml.length() > 0) {
            fragmentList.add(new HtmlFragment(remainingHtml));
        }
        return fragmentList;
    }

    private static InputFragment geInputFragment(FeedGWT feedGWT, String variableId) {
        for (InputGWT inputGWT : feedGWT.getInputs()) {
            if (inputGWT.getName().equals(variableId))
                return new InputFragment(inputGWT);
        }
        return null;
    }

    private static CalculationFragment getCalculationFragment(FeedGWT feedGWT, String variableId) {
        for (CalculationGWT calculationGWT : feedGWT.getCalculations()) {
            if (calculationGWT.getName().equals(variableId))
                return new CalculationFragment(calculationGWT);
        }

        return null;
    }

    private static QuestionFragment getQuestionFragment(FeedGWT feedGWT, String variableId) {
        for (QuestionGWT questionGWT : feedGWT.getQuestions()) {
            if (questionGWT.getName().equals(variableId))
                return new QuestionFragment(questionGWT);
        }
        return null;
    }
}
