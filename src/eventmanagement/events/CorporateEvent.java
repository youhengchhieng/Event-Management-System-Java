package eventmanagement.events;

import eventmanagement.core.Event;

public class CorporateEvent extends Event {
    private String companyName;
    private String contactPerson;
    private boolean includesLunch;
    
    public CorporateEvent(String eventId, String eventName, String eventDate, String eventVenue, 
                        int maxCapacity, double budget, String companyName, String contactPerson, 
                        boolean includesLunch) {
        super(eventId, eventName, eventDate, eventVenue, maxCapacity, budget);
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.includesLunch = includesLunch;
    }
    
    @Override
    public void planEvent() {
        System.out.println("Planning corporate event for " + companyName);
        System.out.println("Contact person: " + contactPerson);
        System.out.println("Lunch included: " + (includesLunch ? "Yes" : "No"));
    }
    
    @Override
    public double calculateCost() {
        double baseCost = getBudget() * 0.8; // Use 80% of budget
        if (includesLunch) {
            baseCost += getMaxCapacity() * 500; // Add ₹500 per person for lunch
        }
        return baseCost;
    }
    
    @Override
    public String getEventType() {
        return "Corporate";
    }
    
    // Getter and setters for specific properties
    public String getCompanyName() {
        return companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public String getContactPerson() {
        return contactPerson;
    }
    
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    
    public boolean isIncludesLunch() {
        return includesLunch;
    }
    
    public void setIncludesLunch(boolean includesLunch) {
        this.includesLunch = includesLunch;
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               "\nEvent Type: " + getEventType() +
               "\nCompany: " + companyName +
               "\nContact Person: " + contactPerson +
               "\nIncludes Lunch: " + (includesLunch ? "Yes" : "No");
    }
}
