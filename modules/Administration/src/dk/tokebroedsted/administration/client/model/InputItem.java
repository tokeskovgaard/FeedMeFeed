package dk.tokebroedsted.administration.client.model;

import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

public class InputItem implements IsSerializable, Serializable {

    public enum Type {string,image,number}

    private String name;
    private Type type;
    private int id;

    public void setName(String name) {
        this.name = name;

        //TODO lav id rigtigt.
        id = Random.nextInt();
    }

    public void setType(String typeName) {
        this.type = Type.valueOf(typeName);
    }

    public String getDisplayName() {
        return name+" - "+getVariableId();
    }

    public String getVariableId(){
        return "<%input "+id+"%>";
    }


    public InputItem() {
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }
}
