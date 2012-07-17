package dk.tokebroedsted.administration.client.model;

import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

public class FeedInput implements IsSerializable, Serializable, EditableItem {

    public enum Type {string, image, number}

    private String name;
    private Type type;
    private int id;

    public void setName(String name) {
        this.name = name;

        id = Random.nextInt();
    }

    public void setType(String typeName) {
        this.type = Type.valueOf(typeName);
    }

    @Override
    public String getDisplayName() {
        return name + " - " + getVariableId();
    }

    @Override
    public String getVariableId() {
        return "<%input " + id + "%>";
    }


    public FeedInput() {
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }
}
