package com.lantushenko.experimental.stub.dao.impl;

import java.util.*;

import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.lantushenko.experimental.stub.dao.Role;
import com.lantushenko.experimental.stub.dao.User;

public class UserImpl extends AbstractEntityImpl<UserImpl> implements User {

    private String login;
    private String fullName;
    private String password;
    private Integer expectedCalories;
    private Set<Role> roles;

    public UserImpl() {
        super(UserImpl.class);
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> result = new HashSet<>();
        result.add(new SimpleGrantedAuthority(RoleImpl.USER_ROLE_NAME));
        if (null == roles) {
            return result;
        }
        for (Role userRole : roles) {
            result.add(new SimpleGrantedAuthority(userRole.getName()));
        }

        return Collections.unmodifiableCollection(result);
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Integer getExpectedCalories() {
        return expectedCalories;
    }

    @Override
    public void setExpectedCalories(Integer expectedCalories) {
        this.expectedCalories = expectedCalories;
    }
}
