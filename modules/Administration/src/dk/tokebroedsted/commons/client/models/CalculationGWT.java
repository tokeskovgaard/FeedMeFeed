package dk.tokebroedsted.commons.client.models;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 18-07-12
 * Time: 20:28
 * To change this template use File | Settings | File Templates.
 */
public class CalculationGWT implements IsSerializable {

    private Integer id;

    private String name;

    private String calculation;


    private CalculationGWT() {
    }

    public CalculationGWT(Integer id, String name, String calculation) {
        this.id = id;
        this.name = name;
        this.calculation = calculation;
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
}
