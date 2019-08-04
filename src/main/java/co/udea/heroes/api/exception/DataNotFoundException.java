package co.udea.heroes.api.exception;

/**
 * Exception when any resource or data is not found.
 */
public class DataNotFoundException extends GeneralRuntimeException {

    private static final long serialVersionUID = 1L;

    public DataNotFoundException(String message) {
        super(message);
    }

}