package eventmanagement;

import eventmanagement.core.*;
import eventmanagement.events.*;
import eventmanagement.exceptions.*;
import eventmanagement.gui.EventManagementGUI;
import eventmanagement.users.*;
import eventmanagement.util.*;

import java.util.LinkedList;
import java.util.List;

public class EventManagementSystem implements EventManager, Registration {
    // Using LinkedList instead of arrays as per requirement
    private List<Event> events;
    private List<User> users;
    private List<Registration> registrations;
    
    public EventManagementSystem() {
        this.events = new LinkedList<>();
        this.users = new LinkedList<>();
        this.registrations = new LinkedList<>();
    }
    
    // Implementation of EventManager interface
    @Override
    public void addEvent(Event event) {
        try {
            if (event == null) {
                throw new InvalidEventDataException("Event cannot be null");
            }
            
            // Check if the event ID already exists
            for (Event e : events) {
                if (e.getEventId().equals(event.getEventId())) {
                    throw new InvalidEventDataException("Event ID already exists");
                }
            }
            
            // Validate event date
            if (!DateValidator.isValidDate(event.getEventDate())) {
                throw new InvalidEventDataException("Invalid date format. Use dd/MM/yyyy");
            }
            
            if (!DateValidator.isFutureDate(event.getEventDate())) {
                throw new InvalidEventDataException("Event date must be in the future");
            }
            
            events.add(event);
            System.out.println("Event added successfully: " + event.getEventName());
            
        } catch (InvalidEventDataException e) {
            System.err.println("Error adding event: " + e.getMessage());
        }
    }
    
    @Override
    public void removeEvent(String eventId) {
        try {
            Event eventToRemove = findEvent(eventId);
            
            if (eventToRemove == null) {
                throw new EventNotFoundException(eventId);
            }
            
            events.remove(eventToRemove);
            System.out.println("Event removed successfully: " + eventToRemove.getEventName());
            
        } catch (EventNotFoundException e) {
            System.err.println("Error removing event: " + e.getMessage());
        }
    }
    
    @Override
    public Event findEvent(String eventId) {
        for (Event event : events) {
            if (event.getEventId().equals(eventId)) {
                return event;
            }
        }
        return null;
    }
    
    @Override
    public void displayAllEvents() {
        if (events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }
        
        System.out.println("===== All Events =====");
        for (Event event : events) {
            System.out.println("---------------------");
            System.out.println(event);
            System.out.println("---------------------");
        }
    }
    
    @Override
    public int getTotalEventCount() {
        return events.size();
    }
    
    @Override
    public double calculateTotalBudget() {
        double totalBudget = 0;
        for (Event event : events) {
            totalBudget += event.getBudget();
        }
        return totalBudget;
    }
    
    // Implementation of Registration interface
    @Override
    public boolean registerParticipant(String eventId, String participantId) {
        try {
            Event event = findEvent(eventId);
            
            if (event == null) {
                throw new EventNotFoundException(eventId);
            }
            
            // Check if the event is at capacity
            int currentRegistrationCount = getRegisteredCount(eventId);
            if (currentRegistrationCount >= event.getMaxCapacity()) {
                throw new RegistrationFullException(eventId);
            }
            
            // In a real implementation, we would add the registration to a collection
            // For simplicity, we'll just print a message
            System.out.println("Participant " + participantId + " registered for event " + eventId);
            return true;
            
        } catch (EventManagementException e) {
            System.err.println("Registration error: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean cancelRegistration(String eventId, String participantId) {
        try {
            Event event = findEvent(eventId);
            
            if (event == null) {
                throw new EventNotFoundException(eventId);
            }
            
            // In a real implementation, we would remove the registration from a collection
            // For simplicity, we'll just print a message
            System.out.println("Participant " + participantId + " registration canceled for event " + eventId);
            return true;
            
        } catch (EventManagementException e) {
            System.err.println("Cancellation error: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public int getRegisteredCount(String eventId) {
        // In a real implementation, we would count registrations for the given event
        // For simplicity, we'll return a dummy value
        return 0;
    }
    
    @Override
    public void displayRegisteredParticipants(String eventId) {
        try {
            Event event = findEvent(eventId);
            
            if (event == null) {
                throw new EventNotFoundException(eventId);
            }
            
            // In a real implementation, we would display participants from a collection
            System.out.println("===== Registered Participants for " + event.getEventName() + " =====");
            System.out.println("No participants to display in this simplified implementation.");
            
        } catch (EventManagementException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // User management methods
    public void addUser(User user) {
        if (user == null) {
            System.err.println("Error: User cannot be null");
            return;
        }
        
        // Check if user ID already exists
        for (User u : users) {
            if (u.getUserId().equals(user.getUserId())) {
                System.err.println("Error: User ID already exists");
                return;
            }
        }
        
        // Validate user data
        if (StringValidator.isNullOrEmpty(user.getName())) {
            System.err.println("Error: User name cannot be empty");
            return;
        }
        
        if (!StringValidator.isValidEmail(user.getEmail())) {
            System.err.println("Error: Invalid email format");
            return;
        }
        
        if (!StringValidator.isValidPhoneNumber(user.getPhone())) {
            System.err.println("Error: Invalid phone number format");
            return;
        }
        
        users.add(user);
        System.out.println("User added successfully: " + user.getName());
    }
    
    public void removeUser(String userId) {
        User userToRemove = null;
        
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                userToRemove = user;
                break;
            }
        }
        
        if (userToRemove != null) {
            users.remove(userToRemove);
            System.out.println("User removed successfully: " + userToRemove.getName());
        } else {
            System.err.println("Error: User with ID " + userId + " not found");
        }
    }
    
    public User findUser(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
    
    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users available.");
            return;
        }
        
        System.out.println("===== All Users =====");
        for (User user : users) {
            System.out.println("---------------------");
            System.out.println(user);
            System.out.println("---------------------");
        }
    }
    
    // Generate reports
    public void generateEventSummaryReport() {
        System.out.println("===== Event Summary Report =====");
        System.out.println("Total number of events: " + getTotalEventCount());
        System.out.println("Total budget: ₹" + calculateTotalBudget());
        
        int corporateCount = 0;
        int weddingCount = 0;
        int conferenceCount = 0;
        
        for (Event event : events) {
            if (event instanceof CorporateEvent) {
                corporateCount++;
            } else if (event instanceof WeddingEvent) {
                weddingCount++;
            } else if (event instanceof ConferenceEvent) {
                conferenceCount++;
            }
        }
        
        System.out.println("Corporate events: " + corporateCount);
        System.out.println("Wedding events: " + weddingCount);
        System.out.println("Conference events: " + conferenceCount);
    }

    // Add a method to get all events as a list
    public List<Event> getAllEvents() {
        return new LinkedList<>(events);
    }
}
