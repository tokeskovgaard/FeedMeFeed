package dk.tokebroedsted.feed.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 18-07-12
 * Time: 15:36
 * To change this template use File | Settings | File Templates.
 */
public class QuestionItemGWT implements IsSerializable, Serializable {

    private Integer numericAnswer;
    private String name;

    public Integer getNumericAnswer() {
        return numericAnswer;
    }

    public String getName() {
        return name;
    }

    public void setNumericAnswer(Integer numericAnswer) {
        this.numericAnswer = numericAnswer;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Type {numeric}

    public Type type;

    public Type getType() {
        return type;
    }
}
