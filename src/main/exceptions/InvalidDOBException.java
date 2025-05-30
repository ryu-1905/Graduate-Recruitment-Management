package main.exceptions;

// Format: dd/MM/YYYY
public class InvalidDOBException extends Exception {
    public InvalidDOBException(String message) {
        super(message);
    }
}
