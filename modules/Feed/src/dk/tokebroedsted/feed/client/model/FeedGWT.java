package dk.tokebroedsted.feed.client.model;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 18-07-12
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */
public class FeedGWT implements IsSerializable, Serializable {

    private String name;
    private String css;
    private String html;

    public String getName() {
        return name;
    }

    public String getCss() {
        return css;
    }

    public String getHtml() {
        return html;
    }

    public void setHTML(String html) {
        this.html = html;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public void setName(String name) {
        this.name = name;
    }
}
