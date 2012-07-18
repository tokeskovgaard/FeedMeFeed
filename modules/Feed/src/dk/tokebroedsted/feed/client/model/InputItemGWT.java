package dk.tokebroedsted.feed.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 18-07-12
 * Time: 15:32
 * To change this template use File | Settings | File Templates.
 */
public class InputItemGWT implements IsSerializable, Serializable {


    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
