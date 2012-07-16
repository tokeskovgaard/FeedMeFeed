package dk.tokebroedsted.administration.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.awt.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 16-07-12
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
public class FeedItem implements IsSerializable, Serializable {

    int width = 600;
    int height = 200;
    String color = "#FFF";

    public FeedItem() {
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
