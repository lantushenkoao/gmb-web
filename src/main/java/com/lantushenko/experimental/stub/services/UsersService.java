package com.lantushenko.experimental.stub.services;

import java.util.List;

import com.lantushenko.experimental.stub.dao.User;
import org.springframework.transaction.annotation.Transactional;

public interface UsersService {

    /**
     * Save existing user
     * @param user
     */
    void save(User user);

    /**
     * Set password on user DAO using Spring Security encryption.
     * This method does not perform database updates
     * @param user
     * @param newPassword
     */
    void setPasswordNoUpdate(User user, String newPassword);

    /**
     * List all available users
     * @return
     */
    List<User> listUsers();

    /**
     * Create new user
     * @param login
     * @param fullName
     * @param password
     * @param expectedCalories
     * @return
     */
    User add(String login, String fullName, String password, Integer expectedCalories);

    User findUserById(Long id);

    void updateUserPassword(Long userId, String newPassword);

    void delete(User user);

    boolean isCurrentUserHasRole(String roleName);
}