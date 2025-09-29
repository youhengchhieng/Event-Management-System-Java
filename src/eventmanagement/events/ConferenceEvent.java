package eventmanagement.events;

import eventmanagement.core.Event;

public class ConferenceEvent extends Event {
    private String topic;
    private int speakerCount;
    private boolean technicalEquipmentNeeded;
    
    public ConferenceEvent(String eventId, String eventName, String eventDate, String eventVenue, 
                         int maxCapacity, double budget, String topic, int speakerCount, 
                         boolean technicalEquipmentNeeded) {
        super(eventId, eventName, eventDate, eventVenue, maxCapacity, budget);
        this.topic = topic;
        this.speakerCount = speakerCount;
        this.technicalEquipmentNeeded = technicalEquipmentNeeded;
    }
    
    @Override
    public void planEvent() {
        System.out.println("Planning conference on " + topic);
        System.out.println("Number of speakers: " + speakerCount);
        System.out.println("Technical equipment needed: " + (technicalEquipmentNeeded ? "Yes" : "No"));
    }
    
    @Override
    public double calculateCost() {
        double baseCost = getBudget() * 0.6; // Use 60% of budget
        if (technicalEquipmentNeeded) {
            baseCost += 30000; // Add ₹30,000 for technical equipment
        }
        // Additional cost for speakers
        baseCost += speakerCount * 10000; // ₹10,000 per speaker
        return baseCost;
    }
    
    @Override
    public String getEventType() {
        return "Conference";
    }
    
    // Getters and setters
    public String getTopic() {
        return topic;
    }
    
    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    public int getSpeakerCount() {
        return speakerCount;
    }
    
    public void setSpeakerCount(int speakerCount) {
        this.speakerCount = speakerCount;
    }
    
    public boolean isTechnicalEquipmentNeeded() {
        return technicalEquipmentNeeded;
    }
    
    public void setTechnicalEquipmentNeeded(boolean technicalEquipmentNeeded) {
        this.technicalEquipmentNeeded = technicalEquipmentNeeded;
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               "\nEvent Type: " + getEventType() +
               "\nTopic: " + topic +
               "\nSpeaker Count: " + speakerCount +
               "\nTechnical Equipment Needed: " + (technicalEquipmentNeeded ? "Yes" : "No");
    }
}
