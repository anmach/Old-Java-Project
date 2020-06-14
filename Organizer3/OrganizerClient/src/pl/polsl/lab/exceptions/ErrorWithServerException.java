package pl.polsl.lab.exceptions;

/**
 * Exception class thrown when recived error message from server
 * @author Anna Mach
 * @version 1.0
 */
public class ErrorWithServerException extends Exception {
    private final String information;
    
    /**
     * Constructor for exception
     * @param message - message from server
     */
    public ErrorWithServerException(String message) {
        this.information = message;
    }
    
    /**
     * Overrided getter for message
     * @return information - String with message from server
     */
    @Override
    public String getMessage(){
        return information;
    }
}