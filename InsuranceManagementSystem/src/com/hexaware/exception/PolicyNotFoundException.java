package com.hexaware.exception;


@SuppressWarnings("serial")

public class PolicyNotFoundException extends Exception {
    
    public PolicyNotFoundException(String message) {
        super(message);
    }
    
    public PolicyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
