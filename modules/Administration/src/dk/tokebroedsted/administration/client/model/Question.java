package dk.tokebroedsted.administration.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;
import java.util.Set;

public class Question implements IsSerializable, Serializable {
    public enum Type {open, closed}

    public Set<Choice> choices;

    private String name;
    private Type type;

    public Question() {
    }

    public String getName() {
        return name;
    }
}
