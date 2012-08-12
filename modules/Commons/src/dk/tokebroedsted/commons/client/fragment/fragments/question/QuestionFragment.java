package dk.tokebroedsted.commons.client.fragment.fragments.question;

import dk.tokebroedsted.commons.client.fragment.fragments.Fragment;
import dk.tokebroedsted.commons.client.models.QuestionGWT;
import dk.tokebroedsted.commons.client.models.QuestionItemGWT;

public abstract class QuestionFragment extends Fragment<QuestionItemGWT> {

    public abstract QuestionGWT getQuestionGWT();
}
