package com.ilovesshan.exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/15
 * @description:
 */
public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
