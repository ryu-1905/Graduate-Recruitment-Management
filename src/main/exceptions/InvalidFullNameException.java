package main.exceptions;

// min: 10 && max: 50
public class InvalidFullNameException extends Exception {
    public InvalidFullNameException(String message) {
        super(message);
    }
}
