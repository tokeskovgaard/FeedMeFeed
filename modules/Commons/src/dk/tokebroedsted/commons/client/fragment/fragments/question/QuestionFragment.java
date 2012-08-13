package dk.tokebroedsted.commons.client.fragment.fragments.question;

import com.google.gwt.user.client.ui.Widget;
import dk.tokebroedsted.commons.client.fragment.fragments.Fragment;
import dk.tokebroedsted.commons.client.models.QuestionGWT;
import dk.tokebroedsted.commons.client.models.QuestionItemGWT;

public class QuestionFragment implements Fragment<QuestionItemGWT> {

    private QuestionGWT questionGWT;

    public QuestionFragment(QuestionGWT questionGWT) {
        this.questionGWT = questionGWT;
    }

    public QuestionGWT getQuestionGWT() {
        return questionGWT;
    }

    @Override
    public Widget createFragment(QuestionItemGWT value) {
        if (QuestionGWT.Type.bool.equals(questionGWT.getType())) {
            return new BoolQuestionWidget(questionGWT, value);
        }
        throw new RuntimeException("Cant handle the passed QuestionItem.");
    }
}
