package com.lantushenko.experimental.stub.web.serialize.json;

import com.lantushenko.experimental.stub.dao.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrentUserDto extends UserDto {
    private final List<String> roles;
    public CurrentUserDto(User user) {
        super(user);
        roles = new ArrayList<>();
        for(GrantedAuthority authority: user.getAuthorities()){
            roles.add(authority.getAuthority());
        }
    }

    public List<String> getRoles(){
        return Collections.unmodifiableList(roles);
    }
}
