package com.lantushenko.experimental.stub.repositories;

import java.util.List;

import com.lantushenko.experimental.stub.dao.User;

public interface UserRepository extends AbstractRepository {

    List<User> listUsers();

    User findUserById(Long userId);

    User findUserByLogin(String login);

    void insert(User user);

    /**
     * Update the existing user
     */
    void update(User user);

    void delete(User delete);
}
