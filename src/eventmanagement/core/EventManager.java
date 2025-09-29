package eventmanagement.core;

public interface EventManager {
    void addEvent(Event event);
    void removeEvent(String eventId);
    Event findEvent(String eventId);
    void displayAllEvents();
    int getTotalEventCount();
    double calculateTotalBudget();
}
