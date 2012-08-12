package dk.tokebroedsted.commons.client.fragment;

import dk.tokebroedsted.commons.client.fragment.fragments.BoolCalculationFragment;
import dk.tokebroedsted.commons.client.fragment.fragments.Fragment;
import dk.tokebroedsted.commons.client.fragment.fragments.HtmlFragment;
import dk.tokebroedsted.commons.client.fragment.fragments.input.StringInputFragment;
import dk.tokebroedsted.commons.client.fragment.fragments.question.BoolQuestionFragment;
import dk.tokebroedsted.commons.client.models.CalculationGWT;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.InputGWT;
import dk.tokebroedsted.commons.client.models.QuestionGWT;

import java.util.LinkedList;

public class FragmentFactory {

    public static LinkedList<Fragment> buildFragmentList(FeedGWT feedGWT) {
        LinkedList<Fragment> fragmentList = new LinkedList<Fragment>();
        String html = feedGWT.getHtml();
        int index = 0;
        int newIndex;


        //TODO Use matcher instead
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
                        fragmentList.add(new StringInputFragment(inputGwt));
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
                        fragmentList.add(new BoolQuestionFragment(questionGWT));
                }
            }
            if (foundMatch)
                continue;

            for (CalculationGWT calculationGwt : feedGWT.getCalculations()) {
                String variableId = calculationGwt.getVariableId();
                if (remainingHTML.startsWith(variableId)) {
                    index = newIndex + variableId.length() + 2;
                    if (CalculationGWT.Type.bool.equals(calculationGwt.getType()))
                        fragmentList.add(new BoolCalculationFragment(calculationGwt));
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
        }
        return fragmentList;
    }
}
