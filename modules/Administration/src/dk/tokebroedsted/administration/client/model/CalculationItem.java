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
public class CalculationItem implements IsSerializable, Serializable {

    private String name;

    public CalculationItem() {
    }

    public String getName() {
        return name;
    }
}
