package eventmanagement;

import eventmanagement.core.*;
import eventmanagement.events.*;
import eventmanagement.users.*;
import eventmanagement.util.*;
import java.util.Scanner;

public class EventUI {
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

                    // Create a sample CorporateEvent (can be replaced with other types)
                    Event event = new CorporateEvent(eventId, eventName, eventDate, eventVenue, maxCapacity, budget, "Company", "Contact", true);
                    system.addEvent(event);
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

                    // Create a sample Customer (can be replaced with Admin or other types)
                    User user = new Customer(userId, name, email, phone, "Address", "Preferred Event Type");
                    system.addUser(user);
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
