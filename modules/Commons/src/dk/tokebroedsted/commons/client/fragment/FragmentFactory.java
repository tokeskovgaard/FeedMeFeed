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
        String html = feedGWT.getHtml();
//        int index = 0;
//        int newIndex;


        RegExp regExp = RegExp.compile("<%(input|question|calculation)\\s(.*?)%>");
        /*       SplitResult split = regExp.split(html);
        String splitString = "";
        for (int i = 0; i < split.length(); i++) {
            splitString += split.get(i) + "###\n";
        }
        Window.alert(splitString);*/


        int lastIndex = 0;
        MatchResult matchResult;

        while ((matchResult = regExp.exec(html)) != null) {
            fragmentList.add(new HtmlFragment(html.substring(lastIndex, matchResult.getIndex())));

            String totalString = matchResult.getGroup(0);
            String type = matchResult.getGroup(1);
            String variableId = matchResult.getGroup(2);
            if ("input".equals(type)) {
                fragmentList.add(new InputFragment(geInput(feedGWT, variableId)));
            } else if ("question".equals(type)) {
                fragmentList.add(new QuestionFragment(getQuestion(feedGWT, variableId)));
            } else if ("calculation".equals(type)) {
                fragmentList.add(new CalculationFragment(getCalculation(feedGWT, variableId)));
            } else {
                fragmentList.add(new HtmlFragment(totalString));
            }

            html = html.substring(matchResult.getIndex());
            lastIndex = matchResult.getIndex();
        }
        if (lastIndex < html.length()) {
            fragmentList.add(new HtmlFragment(html.substring(lastIndex, html.length())));
        }

//        RegExp regExp = RegExp.compile("<%([question|calculation])\\s(.+?)%>");

        /*//TODO Use matcher instead
        while ((newIndex = html.indexOf("<%", index)) != -1) {
            String pureHtml = html.substring(index, newIndex);
            fragmentList.add(new HtmlFragment(pureHtml));

            String remainingHTML = html.substring(newIndex);

            boolean foundMatch = false;
            for (InputGWT inputGwt : feedGWT.getInputs()) {
                String variableId = inputGwt.getVariableId();
                if (remainingHTML.startsWith(variableId)) {
                    index = newIndex + variableId.length();
                    if (InputGWT.Type.string.equals(inputGwt.getType()))
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
                    foundMatch = true;
                    if (questionGWT.getType().equals(QuestionGWT.Type.bool))
                        fragmentList.add(new QuestionFragment(questionGWT));
                }
            }
            if (foundMatch)
                continue;

            for (CalculationGWT calculationGwt : feedGWT.getCalculations()) {
                String variableId = calculationGwt.getVariableId();
                if (remainingHTML.startsWith(variableId)) {
                    index = newIndex + variableId.length() + 2;
                    if (CalculationGWT.Type.bool.equals(calculationGwt.getType()))
                        fragmentList.add(new CalculationFragment(calculationGwt));
                    foundMatch = true;
                }
            }
            if (foundMatch)
                continue;

            pureHtml = html.substring(index, index + "<%".length());
            fragmentList.add(new HtmlFragment(pureHtml));
            index += "<%input ".length();
        }

        if (index < html.length()) {
            fragmentList.add(new HtmlFragment(html.substring(index, html.length())));
        }*/
        return fragmentList;
    }

    private static InputGWT geInput(FeedGWT feedGWT, String variableId) {
        for (InputGWT inputGWT : feedGWT.getInputs()) {
            if (inputGWT.getVariableId().equals(variableId))
                return inputGWT;
        }
        throw new RuntimeException("No input matched");

    }

    private static CalculationGWT getCalculation(FeedGWT feedGWT, String variableId) {
        for (CalculationGWT calculationGWT : feedGWT.getCalculations()) {
            if (calculationGWT.getVariableId().equals(variableId))
                return calculationGWT;
        }
        throw new RuntimeException("No calculation matched");
    }

    private static QuestionGWT getQuestion(FeedGWT feedGWT, String variableId) {
        for (QuestionGWT questionGWT : feedGWT.getQuestions()) {
            if (questionGWT.getVariableId().equals(variableId))
                return questionGWT;
        }
        throw new RuntimeException("No question matched");
    }
}
