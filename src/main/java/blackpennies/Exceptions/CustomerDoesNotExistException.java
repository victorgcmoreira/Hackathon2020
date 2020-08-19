package blackpennies.Exceptions;

public class CustomerDoesNotExistException extends Exception {

    public CustomerDoesNotExistException(String message){
        super(message);
    }
}
