package dk.tokebroedsted.commons.client.models;

import com.google.gwt.user.client.rpc.IsSerializable;

public class InputGWT implements IsSerializable {

    public InputGWT() {
    }

    public InputGWT(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public enum Type {
        string, numeric, date
    }

    private Integer id;

    private String name;

    private Type type;

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public String getVariableId() {
        return "<%input " + name + "%>";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
