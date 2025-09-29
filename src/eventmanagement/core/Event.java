package eventmanagement.core;

// Abstract class representing an Event
public abstract class Event {
    private String eventId;
    private String eventName;
    private String eventDate;
    private String eventVenue;
    private int maxCapacity;
    private double budget;
    
    // Constructor
    public Event(String eventId, String eventName, String eventDate, String eventVenue, int maxCapacity, double budget) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventVenue = eventVenue;
        this.maxCapacity = maxCapacity;
        this.budget = budget;
    }
    
    // Abstract methods to be implemented by subclasses
    public abstract void planEvent();
    public abstract double calculateCost();
    public abstract String getEventType();
    
    // Method overloading example - different ways to update event details
    public void updateEventDetails(String newName, String newDate) {
        this.eventName = newName;
        this.eventDate = newDate;
    }
    
    public void updateEventDetails(String newName, String newDate, String newVenue) {
        this.eventName = newName;
        this.eventDate = newDate;
        this.eventVenue = newVenue;
    }
    
    // Getters and setters
    public String getEventId() {
        return eventId;
    }
    
    public String getEventName() {
        return eventName;
    }
    
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    
    public String getEventDate() {
        return eventDate;
    }
    
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
    
    public String getEventVenue() {
        return eventVenue;
    }
    
    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }
    
    public int getMaxCapacity() {
        return maxCapacity;
    }
    
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    
    public double getBudget() {
        return budget;
    }
    
    public void setBudget(double budget) {
        this.budget = budget;
    }
    
    @Override
    public String toString() {
        return "Event ID: " + eventId + 
               "\nEvent Name: " + eventName + 
               "\nEvent Date: " + eventDate + 
               "\nEvent Venue: " + eventVenue +
               "\nMax Capacity: " + maxCapacity +
               "\nBudget: ₹" + budget;
    }
}
