package com.lantushenko.experimental.stub.web.serialize.json;

import com.lantushenko.experimental.stub.dao.User;

public class UserDto extends AbstractDto {

    private User user;

    public UserDto(User user) {
        setId(user.getId());
        this.user = user;
    }

    public String getLogin() {
        return user.getLogin();
    }

    public String getFullName() {
        return user.getFullName();
    }
}
