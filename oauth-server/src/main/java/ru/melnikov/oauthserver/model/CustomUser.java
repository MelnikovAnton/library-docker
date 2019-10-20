package ru.melnikov.oauthserver.model;

import org.springframework.security.core.userdetails.User;

import java.security.Principal;

public class CustomUser extends User implements Principal {

    public CustomUser(UserEntity user) {
        super(user.getUsername(), user.getPassword(), user.getRoles());
    }

    @Override
    public String getName() {
        return this.getUsername();
    }
}
