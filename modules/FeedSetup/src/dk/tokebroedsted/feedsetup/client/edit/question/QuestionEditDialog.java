package dk.tokebroedsted.feedsetup.client.edit.question;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.*;
import dk.tokebroedsted.commons.client.models.QuestionGWT;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 07-08-12
 * Time: 01:15
 * To change this template use File | Settings | File Templates.
 */
public class QuestionEditDialog extends DialogBox {
    private QuestionGWT questionGWT;
    private Command saveCommand;

    interface QuestionEditDialogUiBinder extends UiBinder<FlowPanel, QuestionEditDialog> {
    }

    private static QuestionEditDialogUiBinder ourUiBinder = GWT.create(QuestionEditDialogUiBinder.class);

    @UiField TextBox name;
    @UiField ListBox type;

    public QuestionEditDialog(QuestionGWT questionGWT, Command saveCommand) {
        setWidget(ourUiBinder.createAndBindUi(this));
        this.questionGWT = questionGWT;
        this.saveCommand = saveCommand;

        name.setValue(questionGWT.getName());

        QuestionGWT.Type[] values = QuestionGWT.Type.values();
        int selectedIndex = 0;
        for (int i = 0; i < values.length; i++) {
            QuestionGWT.Type value = values[i];
            type.addItem(value.name());
            if (value.equals(questionGWT.getType())) {
                selectedIndex = i;
            }
        }
        type.setSelectedIndex(selectedIndex);
    }

    @UiHandler("save")
    void save(ClickEvent e) {
        questionGWT.setName(name.getValue());
        QuestionGWT.Type selectedType = QuestionGWT.Type.valueOf(type.getValue(type.getSelectedIndex()));
        questionGWT.setType(selectedType);
        saveCommand.execute();
        hide();
    }

    @UiHandler("cancel")
    void cancel(ClickEvent e) {
        hide();
    }
}