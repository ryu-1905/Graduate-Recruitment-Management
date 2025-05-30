package main.exceptions;

import java.util.List;

// 10 digits, start with: 090, 098, 091, 031, 035, 038
public class InvalidPhoneNumberException extends Exception {
    public static final List<String> validPrefixes = java.util.List.of("090", "098", "091", "031", "035", "038");

    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
