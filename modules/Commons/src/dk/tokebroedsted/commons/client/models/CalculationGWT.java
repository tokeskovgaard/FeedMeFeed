package dk.tokebroedsted.commons.client.models;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 18-07-12
 * Time: 20:28
 * To change this template use File | Settings | File Templates.
 */
public class CalculationGWT implements IsSerializable {

    private Integer id;

    public Type getType() {
        return type;
    }

    public enum Type {bool, numeric, text}

    private Type type;

    private String name;

    private String calculation;


    public CalculationGWT() {
    }

    public CalculationGWT(Integer id, String name, String calculation, Type type) {
        this.id = id;
        this.name = name;
        this.calculation = calculation;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCalculation() {
        return calculation;
    }

    public String getVariableId() {
        return "<%calculation " + name + "%>";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalculation(String calculation) {
        this.calculation = calculation;
    }
}
