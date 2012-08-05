package dk.tokebroedsted;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 05-08-12
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
public class URLImp {
    private final String prefix = "/FeedMeFeed/";
    private HashMap<String, String> parameters = new HashMap<String, String>();
    private String url;

    public URLImp(Class clazz) {
    }

    public URLImp(String urlName) {
        url = prefix + urlName;
    }

    @Override
    public String toString() {
        String returnUrl = url;
        if (parameters.size() > 0) {
            returnUrl += "?";
            for (String key : parameters.keySet()) {
                String value = parameters.get(key);
                returnUrl += key + "=" + value;
            }
        }
        return returnUrl;
    }

    public void addParameter(String key, Object value) {
        parameters.put(key, value.toString());
    }
}
