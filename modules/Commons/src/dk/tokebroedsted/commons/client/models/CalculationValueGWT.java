package dk.tokebroedsted.commons.client.models;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 18-07-12
 * Time: 15:38
 * To change this template use File | Settings | File Templates.
 */
public class CalculationValueGWT implements IsSerializable {

    private String variableId;
    private String name;
    private String value;

    public CalculationValueGWT() {
    }

    public CalculationValueGWT(String variableId, String value) {
        this.variableId = variableId;
        this.value = value;
    }

    public String getVariableId() {
        return variableId;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
