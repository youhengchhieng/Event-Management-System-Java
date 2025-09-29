package eventmanagement.events;

import eventmanagement.core.Event;

public class WeddingEvent extends Event {
    private String brideName;
    private String groomName;
    private int guestCount;
    private boolean includesDecoration;
    
    public WeddingEvent(String eventId, String eventName, String eventDate, String eventVenue, 
                      int maxCapacity, double budget, String brideName, String groomName, 
                      int guestCount, boolean includesDecoration) {
        super(eventId, eventName, eventDate, eventVenue, maxCapacity, budget);
        this.brideName = brideName;
        this.groomName = groomName;
        this.guestCount = guestCount;
        this.includesDecoration = includesDecoration;
    }
    
    @Override
    public void planEvent() {
        System.out.println("Planning wedding for " + brideName + " and " + groomName);
        System.out.println("Expected number of guests: " + guestCount);
        System.out.println("Includes decoration: " + (includesDecoration ? "Yes" : "No"));
    }
    
    @Override
    public double calculateCost() {
        double baseCost = getBudget() * 0.7; // Use 70% of budget
        if (includesDecoration) {
            baseCost += 100000; // Add ₹100,000 for decoration
        }
        // Additional cost based on guest count
        baseCost += guestCount * 1200; // ₹1200 per guest
        return baseCost;
    }
    
    @Override
    public String getEventType() {
        return "Wedding";
    }
    
    // Getters and setters
    public String getBrideName() {
        return brideName;
    }
    
    public void setBrideName(String brideName) {
        this.brideName = brideName;
    }
    
    public String getGroomName() {
        return groomName;
    }
    
    public void setGroomName(String groomName) {
        this.groomName = groomName;
    }
    
    public int getGuestCount() {
        return guestCount;
    }
    
    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }
    
    public boolean isIncludesDecoration() {
        return includesDecoration;
    }
    
    public void setIncludesDecoration(boolean includesDecoration) {
        this.includesDecoration = includesDecoration;
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               "\nEvent Type: " + getEventType() +
               "\nBride: " + brideName +
               "\nGroom: " + groomName +
               "\nGuest Count: " + guestCount +
               "\nIncludes Decoration: " + (includesDecoration ? "Yes" : "No");
    }
}
