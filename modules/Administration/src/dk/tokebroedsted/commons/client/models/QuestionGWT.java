package dk.tokebroedsted.commons.client.models;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

public class QuestionGWT implements IsSerializable {


    private Integer id;
    private Integer feedId;


    public QuestionGWT() {
    }

    public QuestionGWT(Integer id, Integer feedId, String name, Type type) {
        this.id = id;
        this.feedId = feedId;
        this.name = name;
        this.type = type;
    }

    public QuestionGWT(Integer feedId, String name, Type type) {
        this.feedId = feedId;
        this.name = name;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public enum Type {numeric;}

    private Type type;

    private String name;

    public String getVariableId() {
        return "<%question " + name + "%>";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(QuestionGWT.Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof QuestionGWT && ((QuestionGWT) obj).getId() != null) {
            return ((QuestionGWT) obj).getId().equals(getId());
        }
        return super.equals(obj);
    }
}
