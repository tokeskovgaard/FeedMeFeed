package dk.tokebroedsted.administration.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

public class InputItem implements IsSerializable, Serializable {

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String typeName) {
        this.type = Type.valueOf(typeName);
    }

    public enum Type {image,string,number}

    private String name;
    private Type type;

    public InputItem() {
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }
}
