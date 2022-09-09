package com.kiddiesave.kiddiesave.exceptions;

public class UserNotFoundException extends RuntimeException{
    private Long userid;
    private String email;
    private String mobile;


    public UserNotFoundException(Long userid) {
        super(String.format("The user with ", userid, "does not exist : '%s'"
        ));
    }

    public UserNotFoundException(String email) {
        super(String.format("The user with ",email," does not exist : '%s'"));
        this.email = email;
    }

    public UserNotFoundException(String message, String mobile) {
        super(String.format("The user with ",mobile, " does not exist : '%s'"+ message));
        this.mobile = mobile;
    }

    public String UserNotFoundException(String message)
    {
        return String.format(message);
    }
    public String UserNotFoundException(Long userid) {
       return String.format("The user with ", userid, "does not exist : '%s'"
        );
    }
}
