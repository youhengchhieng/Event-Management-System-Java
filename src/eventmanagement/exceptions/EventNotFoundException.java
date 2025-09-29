package eventmanagement.exceptions;

public class EventNotFoundException extends EventManagementException {
    public EventNotFoundException(String eventId) {
        super("Event with ID " + eventId + " not found.");
    }
}
