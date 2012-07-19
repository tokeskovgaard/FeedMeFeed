package dk.tokebroedsted.commons.server.converters;

import dk.tokebroedsted.commons.client.models.UserGWT;
import dk.tokebroedsted.hibernate.tables.User;

/**
 * Created with IntelliJ IDEA.
 * User: mads
 * Date: 7/19/12
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */

public class UserConverter implements Converter<UserGWT, User> {

    @Override
    public User toServerObject(UserGWT userGWT) {


        User user = new User();
        user.setId(userGWT.getId());
        user.setEmail(userGWT.getEmail());
        user.setLoginname(userGWT.getLoginname());
        user.setUsername(userGWT.getUsername());
        user.setPassword(userGWT.getPassword());

        return user;

    }

    @Override
    public UserGWT toGwtObject(User user) {

        UserGWT userGWT = new UserGWT();
        userGWT.setId(user.getId());
        userGWT.setLoginname(user.getLoginname());
        userGWT.setUsername(user.getUsername());
        userGWT.setEmail(user.getEmail());
        userGWT.setPassword(user.getPassword());

        return userGWT;

    }

}
