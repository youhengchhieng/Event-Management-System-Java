package eventmanagement.exceptions;

public class RegistrationFullException extends EventManagementException {
    public RegistrationFullException(String eventId) {
        super("Registration for event " + eventId + " is full. Cannot accept more participants.");
    }
}
