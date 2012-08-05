package dk.tokebroedsted.commons.server.converters;

import dk.tokebroedsted.commons.client.models.UserGWT;
import dk.tokebroedsted.hibernate.tables.User;


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
