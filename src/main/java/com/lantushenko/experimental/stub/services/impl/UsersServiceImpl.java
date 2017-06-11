package com.lantushenko.experimental.stub.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.lantushenko.experimental.stub.dao.User;
import com.lantushenko.experimental.stub.dao.impl.UserImpl;
import com.lantushenko.experimental.stub.exceptions.UserNotFound;
import com.lantushenko.experimental.stub.repositories.UserRepository;
import com.lantushenko.experimental.stub.services.UsersService;

@Component(value = "usersService")
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional(readOnly = true)
public class UsersServiceImpl implements UsersService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = false)
    public User add(String login, String fullName, String password, Integer expectedCalories) {
        UserImpl user = new UserImpl();
        user.setLogin(login);
        user.setFullName(fullName);
        user.setExpectedCalories(expectedCalories);
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        userRepository.insert(user);

        return user;
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(User user) {
        Assert.isTrue(!user.isTransient());
        userRepository.update(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateUserPassword(Long userId, String newPassword) {
        Assert.hasText(newPassword);
        User user = findUserById(userId);
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.update(user);
    }


    @Override
    @Transactional(readOnly = true)
    public void setPasswordNoUpdate(User user, String newPassword){
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
    }

    @Override
    public List<User> listUsers() {
        return userRepository.listUsers();
    }

    @Override
    public User findUserById(Long id) {
        User result = userRepository.findUserById(id);
        if (null == result) {
            throw new UserNotFound(Long.toString(id));
        }
        return result;
    }

    @Override
    public boolean isCurrentUserHasRole(String roleName){
        User user =  ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        for(GrantedAuthority authority: user.getAuthorities()){
            if(authority.getAuthority().equalsIgnoreCase(roleName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public User loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(login);
        if(null == user){
            throw new UsernameNotFoundException(String.format("User %s not found", login));
        }
        return user;
    }
}
