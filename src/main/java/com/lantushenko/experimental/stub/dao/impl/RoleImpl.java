package com.lantushenko.experimental.stub.dao.impl;

import com.lantushenko.experimental.stub.dao.Role;

public class RoleImpl extends AbstractEntityImpl<RoleImpl> implements Role {

    public static String ADMIN_ROLE_NAME = "ROLE_ADMIN";
    public static String USER_ROLE_NAME = "ROLE_USER";

    private String name;

    public RoleImpl() {
        super(RoleImpl.class);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
