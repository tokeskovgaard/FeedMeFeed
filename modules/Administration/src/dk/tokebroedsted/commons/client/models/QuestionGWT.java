package dk.tokebroedsted.commons.client.models;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 18-07-12
 * Time: 20:29
 * To change this template use File | Settings | File Templates.
 */
public class QuestionGWT implements IsSerializable, Serializable {
    public enum Type {numeric}

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
}
