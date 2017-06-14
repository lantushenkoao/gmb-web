package com.lantushenko.experimental.stub.dao;

import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.security.core.userdetails.UserDetails;

public interface User extends AbstractEntity, UserDetails {

    String getLogin();

    void setLogin(String login);

    String getFullName();

    void setFullName(String fullName);

    String getPassword();

    void setPassword(String password);

    Set<Role> getRoles();

    void setRoles(Set<Role> roles);
}