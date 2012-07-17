package dk.tokebroedsted.administration.client.model;

import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;
import java.util.Set;

public class Question implements IsSerializable, Serializable, EditableItem {

    private int id;

    public enum Type {numeric, open, closed}

    public Set<Choice> choices;

    private String name;

    private Type type;

    public Question() {
        id = Random.nextInt();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String getDisplayName() {
        return name + " - " + getVariableId();
    }

    @Override
    public String getVariableId() {
        return "<%question " + id + "%>";
    }
}
