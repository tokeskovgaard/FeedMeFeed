package dk.tokebroedsted.commons.client.models;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 18-07-12
 * Time: 15:32
 * To change this template use File | Settings | File Templates.
 */
public class InputItemGWT implements IsSerializable {

    private int id;
    private String value;
    private InputGWT inputGWT;

    private InputItemGWT() {
    }

    public InputItemGWT(InputGWT inputGWT) {
        this.inputGWT = inputGWT;
    }

    public String getVariabelId() {
        return inputGWT.getVariabelId();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public InputGWT getInputGWT() {
        return inputGWT;
    }

    public int getId() {
        return id;
    }

}
