package dk.tokebroedsted.commons.client.fragment.fragments.question;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import dk.tokebroedsted.commons.client.DefaultCallback;
import dk.tokebroedsted.commons.client.FeedItemService;
import dk.tokebroedsted.commons.client.models.QuestionGWT;
import dk.tokebroedsted.commons.client.models.QuestionItemGWT;

public class BoolQuestionWidget extends Composite {
    interface BoolFragmentUiBinder extends UiBinder<CheckBox, BoolQuestionWidget> {
    }

    private static BoolFragmentUiBinder ourUiBinder = GWT.create(BoolFragmentUiBinder.class);

    private QuestionGWT questionGWT;
    private QuestionItemGWT questionItemGWT;

    @UiField CheckBox boolFragment;

    public BoolQuestionWidget(QuestionGWT questionGWT, QuestionItemGWT questionItemGWT) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.questionGWT = questionGWT;
        this.questionItemGWT = questionItemGWT;
    }

    @UiHandler("boolFragment")
    void valueChanged(ClickEvent event) {
        if (questionItemGWT != null) {
            questionItemGWT.setBoolAnswer(boolFragment.getValue());
            FeedItemService.App.getInstance().save(questionItemGWT, new DefaultCallback<Boolean>() {
                @Override
                public void onSuccess(Boolean result) {
                    Window.alert("SAVED");
                }
            });
        }
    }
}