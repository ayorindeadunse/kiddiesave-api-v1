package com.kiddiesave.kiddiesave.exceptions;


public class UserException extends Exception{
        private String user;
    public UserException(String user) {
        super(String.format("The already exists." + user));
    }
}
