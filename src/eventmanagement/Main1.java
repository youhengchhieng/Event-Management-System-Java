package eventmanagement;

import eventmanagement.core.*;
import eventmanagement.events.*;
import eventmanagement.users.*;
import eventmanagement.util.*;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        // Create an instance of the Event Management System
        EventManagementSystem system = new EventManagementSystem();
        Scanner scanner = new Scanner(System.in);

        // Infinite loop to display menu until user exits
        while (true) {
            System.out.println("\n===== Event Management System =====");
            System.out.println("1. Add Event");
            System.out.println("2. Remove Event");
            System.out.println("3. Display All Events");
            System.out.println("4. Add User");
            System.out.println("5. Remove User");
            System.out.println("6. Display All Users");
            System.out.println("7. Generate Event Summary Report");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            // Read user choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Perform operations based on user choice
            switch (choice) {
                case 1:
                    // Add a new event
                    System.out.print("Enter Event ID: ");
                    String eventId = scanner.nextLine();
                    System.out.print("Enter Event Name: ");
                    String eventName = scanner.nextLine();
                    System.out.print("Enter Event Date (dd/MM/yyyy): ");
                    String eventDate = scanner.nextLine();
                    System.out.print("Enter Event Venue: ");
                    String eventVenue = scanner.nextLine();
                    System.out.print("Enter Max Capacity: ");
                    int maxCapacity = scanner.nextInt();
                    System.out.print("Enter Budget: ");
                    double budget = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    
                    // Ask for event type
                    System.out.println("\nSelect Event Type:");
                    System.out.println("1. Corporate Event");
                    System.out.println("2. Wedding Event");
                    System.out.println("3. Conference Event");
                    System.out.print("Enter your choice (1-3): ");
                    int eventTypeChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    
                    Event event = null;
                    
                    switch (eventTypeChoice) {
                        case 1: // Corporate Event
                            System.out.print("Enter Company Name: ");
                            String companyName = scanner.nextLine();
                            System.out.print("Enter Contact Person: ");
                            String contactPerson = scanner.nextLine();
                            System.out.print("Include Lunch? (true/false): ");
                            boolean includesLunch = scanner.nextBoolean();
                            scanner.nextLine(); // Consume newline
                            
                            event = new CorporateEvent(eventId, eventName, eventDate, eventVenue, 
                                                      maxCapacity, budget, companyName, contactPerson, includesLunch);
                            break;
                            
                        case 2: // Wedding Event
                            System.out.print("Enter Bride's Name: ");
                            String brideName = scanner.nextLine();
                            System.out.print("Enter Groom's Name: ");
                            String groomName = scanner.nextLine();
                            System.out.print("Enter Guest Count: ");
                            int guestCount = scanner.nextInt();
                            System.out.print("Include Decoration? (true/false): ");
                            boolean includesDecoration = scanner.nextBoolean();
                            scanner.nextLine(); // Consume newline
                            
                            event = new WeddingEvent(eventId, eventName, eventDate, eventVenue, 
                                                    maxCapacity, budget, brideName, groomName, 
                                                    guestCount, includesDecoration);
                            break;
                            
                        case 3: // Conference Event
                            System.out.print("Enter Conference Topic: ");
                            String topic = scanner.nextLine();
                            System.out.print("Enter Speaker Count: ");
                            int speakerCount = scanner.nextInt();
                            System.out.print("Technical Equipment Needed? (true/false): ");
                            boolean technicalEquipmentNeeded = scanner.nextBoolean();
                            scanner.nextLine(); // Consume newline
                            
                            event = new ConferenceEvent(eventId, eventName, eventDate, eventVenue, 
                                                       maxCapacity, budget, topic, speakerCount, 
                                                       technicalEquipmentNeeded);
                            break;
                            
                        default:
                            System.out.println("Invalid event type choice. Defaulting to Corporate Event.");
                            event = new CorporateEvent(eventId, eventName, eventDate, eventVenue, 
                                                     maxCapacity, budget, "Default Company", "Default Contact", true);
                    }
                    
                    if (event != null) {
                        system.addEvent(event);
                    }
                    break;

                case 2:
                    // Remove an existing event
                    System.out.print("Enter Event ID to remove: ");
                    String removeEventId = scanner.nextLine();
                    system.removeEvent(removeEventId);
                    break;

                case 3:
                    // Display all events
                    system.displayAllEvents();
                    break;

                case 4:
                    // Add a new user
                    System.out.print("Enter User ID: ");
                    String userId = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();

                    // Ask for user type
                    System.out.println("\nSelect User Type:");
                    System.out.println("1. Customer");
                    System.out.println("2. Admin");
                    System.out.print("Enter your choice (1-2): ");
                    int userTypeChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    
                    User user = null;
                    
                    switch (userTypeChoice) {
                        case 1: // Customer
                            System.out.print("Enter Address: ");
                            String address = scanner.nextLine();
                            System.out.print("Enter Preferred Event Type: ");
                            String preferredEventType = scanner.nextLine();
                            
                            user = new Customer(userId, name, email, phone, address, preferredEventType);
                            break;
                            
                        case 2: // Admin
                            System.out.print("Enter Department: ");
                            String department = scanner.nextLine();
                            System.out.print("Enter Access Level: ");
                            String accessLevel = scanner.nextLine();
                            
                            user = new Admin(userId, name, email, phone, department, accessLevel);
                            break;
                            
                        default:
                            System.out.println("Invalid user type choice. Defaulting to Customer.");
                            user = new Customer(userId, name, email, phone, "Default Address", "Default Event Type");
                    }
                    
                    if (user != null) {
                        system.addUser(user);
                    }
                    break;

                case 5:
                    // Remove an existing user
                    System.out.print("Enter User ID to remove: ");
                    String removeUserId = scanner.nextLine();
                    system.removeUser(removeUserId);
                    break;

                case 6:
                    // Display all users
                    system.displayAllUsers();
                    break;

                case 7:
                    // Generate a summary report of events
                    system.generateEventSummaryReport();
                    break;

                case 8:
                    // Exit the program
                    System.out.println("Exiting Event Management System. Goodbye!");
                    scanner.close(); // Close the Scanner resource
                    return;

                default:
                    // Handle invalid input
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
