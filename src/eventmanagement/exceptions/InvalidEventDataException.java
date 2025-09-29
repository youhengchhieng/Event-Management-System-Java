package eventmanagement.exceptions;

public class InvalidEventDataException extends EventManagementException {
    public InvalidEventDataException(String message) {
        super("Invalid event data: " + message);
    }
}
