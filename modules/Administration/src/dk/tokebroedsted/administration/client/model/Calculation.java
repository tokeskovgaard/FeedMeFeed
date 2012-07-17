package dk.tokebroedsted.administration.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 16-07-12
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 */
public class Calculation implements IsSerializable, Serializable {

    private String name;

    public Calculation() {
    }

    public String getName() {
        return name;
    }
}
