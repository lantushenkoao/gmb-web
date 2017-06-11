package com.lantushenko.experimental.stub.exceptions;

public class UserNotFound extends RuntimeException {

    public UserNotFound(String login) {
        super(String.format("Failed to find user %s. Please ensure that login and password are correct", login));
    }
}
