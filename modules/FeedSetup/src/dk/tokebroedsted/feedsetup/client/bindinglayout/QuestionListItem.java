package dk.tokebroedsted.feedsetup.client.bindinglayout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import dk.tokebroedsted.commons.client.models.QuestionGWT;

public class QuestionListItem extends Composite {
    private Command deleteCommand;
    private QuestionGWT questionGWT;

    interface QuestionListItemUiBinder extends UiBinder<FlowPanel, QuestionListItem> {
    }

    private static QuestionListItemUiBinder ourUiBinder = GWT.create(QuestionListItemUiBinder.class);
    @UiField InlineLabel name;


    public QuestionListItem(QuestionGWT questionGWT) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.questionGWT = questionGWT;
        name.setText(questionGWT.getName());
    }

    public void setDeleteCommand(Command deleteCommand) {
        this.deleteCommand = deleteCommand;
    }

    @UiHandler("deleteImage")
    void delete(ClickEvent e) {
        deleteCommand.execute();
    }

    @UiHandler("name")
    void edit(ClickEvent e) {
        QuestionEditDialog questionEditDialog = new QuestionEditDialog(questionGWT, new Command() {
            @Override
            public void execute() {
            }
        });
    }

}