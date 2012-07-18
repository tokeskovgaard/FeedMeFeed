package dk.tokebroedsted.commons.client.models;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 18-07-12
 * Time: 15:32
 * To change this template use File | Settings | File Templates.
 */
public class InputItemGWT implements IsSerializable, Serializable {

    private String value;
    private InputGWT inputGWT;

    public InputItemGWT() {
    }

    public InputItemGWT(InputGWT inputGWT) {
        this.inputGWT = inputGWT;
    }

    public String getName() {
        return inputGWT.getName();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
