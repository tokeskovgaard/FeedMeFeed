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

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getVariableId() {
        return "<%calculation " + name + "%>";
    }
}
