package dk.tokebroedsted.commons.converters;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 18-07-12
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */
public interface Converter<GwtClass, ServerClass> {

    public ServerClass toServerObject(GwtClass gwtObject);

    public GwtClass toGwtObject(ServerClass serverObject);
}
