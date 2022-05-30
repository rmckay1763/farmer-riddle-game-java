package farmer.model;

/**
 * Thrown to indicated that an action is not allowed in the current state.
 */
public class IllegalActionException extends RuntimeException {
    
    /**
     * Default constructor.
     */
    public IllegalActionException() {
        super();
    }

    /**
     * Overloaded constructor to set the error message.
     * @param message Description of the error.
     */
    public IllegalActionException(String message) {
        super(message);
    }
}
