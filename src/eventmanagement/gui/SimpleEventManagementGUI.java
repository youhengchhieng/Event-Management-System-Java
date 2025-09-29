package eventmanagement.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

import eventmanagement.EventManagementSystem;
import eventmanagement.core.Event;
import eventmanagement.events.*;
import eventmanagement.users.*;
import eventmanagement.util.*;

/**
 * A simplified GUI for the Event Management System.
 * Maintains core functionality while being easier to understand and explain.
 */
public class SimpleEventManagementGUI extends JFrame {
    private EventManagementSystem system;
    private JTabbedPane tabbedPane;
    private DefaultTableModel eventTableModel;
    private DefaultTableModel userTableModel;
    private JTable eventTable;
    private JTable userTable;
    
    public SimpleEventManagementGUI() {
        system = new EventManagementSystem();
        initialize();
    }
    
    private void initialize() {
        // Basic frame setup
        setTitle("Event Management System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        
        // Create tabs
        tabbedPane.addTab("Events", createEventsPanel());
        tabbedPane.addTab("Users", createUsersPanel());
        tabbedPane.addTab("Reports", createReportsPanel());
        
        // Add to frame
        add(tabbedPane);
    }
    
    private JPanel createEventsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Create button panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Event");
        JButton removeButton = new JButton("Remove Event");
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        
        // Create table model
        String[] columns = {"ID", "Name", "Type", "Date", "Venue", "Capacity", "Budget"};
        eventTableModel = new DefaultTableModel(columns, 0);
        eventTable = new JTable(eventTableModel);
        JScrollPane scrollPane = new JScrollPane(eventTable);
        
        // Add to panel
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Add event handlers
        addButton.addActionListener(e -> showAddEventDialog());
        removeButton.addActionListener(e -> {
            int row = eventTable.getSelectedRow();
            if (row != -1) {
                String eventId = (String) eventTableModel.getValueAt(row, 0);
                system.removeEvent(eventId);
                refreshEventTable();
            } else {
                JOptionPane.showMessageDialog(this, "Please select an event to remove");
            }
        });
        
        return panel;
    }
    
    private JPanel createUsersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Create button panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add User");
        JButton removeButton = new JButton("Remove User");
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        
        // Create table model
        String[] columns = {"ID", "Name", "Type", "Email", "Phone"};
        userTableModel = new DefaultTableModel(columns, 0);
        userTable = new JTable(userTableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        
        // Add to panel
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Add event handlers
        addButton.addActionListener(e -> showAddUserDialog());
        removeButton.addActionListener(e -> {
            int row = userTable.getSelectedRow();
            if (row != -1) {
                String userId = (String) userTableModel.getValueAt(row, 0);
                system.removeUser(userId);
                refreshUserTable();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a user to remove");
            }
        });
        
        return panel;
    }
    
    private JPanel createReportsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel controlPanel = new JPanel();
        JComboBox<String> reportType = new JComboBox<>(new String[] {
            "Event Summary", "Events by Type", "Budget Summary"
        });
        JButton generateButton = new JButton("Generate Report");
        controlPanel.add(new JLabel("Report Type:"));
        controlPanel.add(reportType);
        controlPanel.add(generateButton);
        
        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        
        panel.add(controlPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        generateButton.addActionListener(e -> {
            String type = (String) reportType.getSelectedItem();
            reportArea.setText(generateReport(type));
        });
        
        return panel;
    }
    
    private void showAddEventDialog() {
        // Create a tabbed dialog for different event types
        JDialog dialog = new JDialog(this, "Add New Event", true);
        JTabbedPane eventTypes = new JTabbedPane();
        
        // Common fields
        JTextField idField = new JTextField(10);
        JTextField nameField = new JTextField(20);
        JTextField dateField = new JTextField(10);
        JTextField venueField = new JTextField(20);
        JTextField capacityField = new JTextField(5);
        JTextField budgetField = new JTextField(10);
        
        // Create panel with common fields
        JPanel commonPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        commonPanel.add(new JLabel("Event ID:"));
        commonPanel.add(idField);
        commonPanel.add(new JLabel("Event Name:"));
        commonPanel.add(nameField);
        commonPanel.add(new JLabel("Date (dd/MM/yyyy):"));
        commonPanel.add(dateField);
        commonPanel.add(new JLabel("Venue:"));
        commonPanel.add(venueField);
        commonPanel.add(new JLabel("Capacity:"));
        commonPanel.add(capacityField);
        commonPanel.add(new JLabel("Budget:"));
        commonPanel.add(budgetField);
        
        // Corporate event panel
        JPanel corporatePanel = new JPanel(new GridLayout(9, 2, 5, 5));
        corporatePanel.add(commonPanel);
        JTextField companyField = new JTextField(20);
        JTextField contactField = new JTextField(20);
        JCheckBox lunchBox = new JCheckBox();
        corporatePanel.add(new JLabel("Company Name:"));
        corporatePanel.add(companyField);
        corporatePanel.add(new JLabel("Contact Person:"));
        corporatePanel.add(contactField);
        corporatePanel.add(new JLabel("Includes Lunch:"));
        corporatePanel.add(lunchBox);
        
        // Wedding event panel
        JPanel weddingPanel = new JPanel(new GridLayout(9, 2, 5, 5));
        weddingPanel.add(commonPanel);
        JTextField brideField = new JTextField(20);
        JTextField groomField = new JTextField(20);
        JTextField guestCountField = new JTextField(5);
        JCheckBox decorBox = new JCheckBox();
        weddingPanel.add(new JLabel("Bride Name:"));
        weddingPanel.add(brideField);
        weddingPanel.add(new JLabel("Groom Name:"));
        weddingPanel.add(groomField);
        weddingPanel.add(new JLabel("Guest Count:"));
        weddingPanel.add(guestCountField);
        weddingPanel.add(new JLabel("Includes Decoration:"));
        weddingPanel.add(decorBox);
        
        // Conference event panel
        JPanel conferencePanel = new JPanel(new GridLayout(9, 2, 5, 5));
        conferencePanel.add(commonPanel);
        JTextField topicField = new JTextField(20);
        JTextField speakerCountField = new JTextField(5);
        JCheckBox techBox = new JCheckBox();
        conferencePanel.add(new JLabel("Topic:"));
        conferencePanel.add(topicField);
        conferencePanel.add(new JLabel("Speaker Count:"));
        conferencePanel.add(speakerCountField);
        conferencePanel.add(new JLabel("Technical Equipment:"));
        conferencePanel.add(techBox);
        
        // Add panels to tabs
        eventTypes.addTab("Corporate", corporatePanel);
        eventTypes.addTab("Wedding", weddingPanel);
        eventTypes.addTab("Conference", conferencePanel);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        // Add components to dialog
        dialog.setLayout(new BorderLayout());
        dialog.add(eventTypes, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add action listeners
        saveButton.addActionListener(e -> {
            try {
                // Validate common fields
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String date = dateField.getText().trim();
                String venue = venueField.getText().trim();
                int capacity = Integer.parseInt(capacityField.getText().trim());
                double budget = Double.parseDouble(budgetField.getText().trim());
                
                // Create event based on selected tab
                Event event = null;
                int tab = eventTypes.getSelectedIndex();
                
                if (tab == 0) { // Corporate
                    String company = companyField.getText().trim();
                    String contact = contactField.getText().trim();
                    boolean lunch = lunchBox.isSelected();
                    event = new CorporateEvent(id, name, date, venue, capacity, budget, company, contact, lunch);
                } else if (tab == 1) { // Wedding
                    String bride = brideField.getText().trim();
                    String groom = groomField.getText().trim();
                    int guestCount = Integer.parseInt(guestCountField.getText().trim());
                    boolean decor = decorBox.isSelected();
                    event = new WeddingEvent(id, name, date, venue, capacity, budget, bride, groom, guestCount, decor);
                } else { // Conference
                    String topic = topicField.getText().trim();
                    int speakers = Integer.parseInt(speakerCountField.getText().trim());
                    boolean tech = techBox.isSelected();
                    event = new ConferenceEvent(id, name, date, venue, capacity, budget, topic, speakers, tech);
                }
                
                // Add event to system
                system.addEvent(event);
                refreshEventTable();
                dialog.dispose();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter valid numbers for capacity and budget.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage());
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void showAddUserDialog() {
        JDialog dialog = new JDialog(this, "Add New User", true);
        JTabbedPane userTypes = new JTabbedPane();
        
        // Common fields
        JTextField idField = new JTextField(10);
        JTextField nameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField phoneField = new JTextField(10);
        
        // Create panel with common fields
        JPanel commonPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        commonPanel.add(new JLabel("User ID:"));
        commonPanel.add(idField);
        commonPanel.add(new JLabel("Name:"));
        commonPanel.add(nameField);
        commonPanel.add(new JLabel("Email:"));
        commonPanel.add(emailField);
        commonPanel.add(new JLabel("Phone:"));
        commonPanel.add(phoneField);
        
        // Customer panel
        JPanel customerPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        customerPanel.add(commonPanel);
        JTextField addressField = new JTextField(30);
        JTextField preferredTypeField = new JTextField(20);
        customerPanel.add(new JLabel("Address:"));
        customerPanel.add(addressField);
        customerPanel.add(new JLabel("Preferred Event Type:"));
        customerPanel.add(preferredTypeField);
        
        // Admin panel
        JPanel adminPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        adminPanel.add(commonPanel);
        JTextField departmentField = new JTextField(20);
        JTextField roleField = new JTextField(20);
        adminPanel.add(new JLabel("Department:"));
        adminPanel.add(departmentField);
        adminPanel.add(new JLabel("Role:"));
        adminPanel.add(roleField);
        
        // Add panels to tabs
        userTypes.addTab("Customer", customerPanel);
        userTypes.addTab("Admin", adminPanel);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        // Add components to dialog
        dialog.setLayout(new BorderLayout());
        dialog.add(userTypes, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add action listeners
        saveButton.addActionListener(e -> {
            try {
                // Validate common fields
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String phone = phoneField.getText().trim();
                
                if (!StringValidator.isValidEmail(email)) {
                    throw new Exception("Invalid email format");
                }
                
                if (!StringValidator.isValidPhoneNumber(phone)) {
                    throw new Exception("Invalid phone number format");
                }
                
                // Create user based on selected tab
                User user = null;
                int tab = userTypes.getSelectedIndex();
                
                if (tab == 0) { // Customer
                    String address = addressField.getText().trim();
                    String preferredType = preferredTypeField.getText().trim();
                    user = new Customer(id, name, email, phone, address, preferredType);
                } else { // Admin
                    String department = departmentField.getText().trim();
                    String role = roleField.getText().trim();
                    user = new Admin(id, name, email, phone, department, role);
                }
                
                // Add user to system
                system.addUser(user);
                refreshUserTable();
                dialog.dispose();
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage());
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void refreshEventTable() {
        // Clear table
        eventTableModel.setRowCount(0);
        
        // Add rows for each event (more efficient approach)
        for (int i = 0; i < system.getTotalEventCount(); i++) {
            String eventId = "E" + (i+1);
            Event event = system.findEvent(eventId);
            
            if (event != null) {
                Object[] row = {
                    event.getEventId(),
                    event.getEventName(),
                    event.getEventType(),
                    event.getEventDate(),
                    event.getEventVenue(),
                    event.getMaxCapacity(),
                    event.getBudget()
                };
                eventTableModel.addRow(row);
            }
        }
        
        // If no events found with "E" prefix, try numeric IDs
        if (eventTableModel.getRowCount() == 0) {
            for (int j = 1; j <= 100; j++) {
                String eventId = String.valueOf(j);
                Event event = system.findEvent(eventId);
                
                if (event != null) {
                    Object[] row = {
                        event.getEventId(),
                        event.getEventName(),
                        event.getEventType(),
                        event.getEventDate(),
                        event.getEventVenue(),
                        event.getMaxCapacity(),
                        event.getBudget()
                    };
                    eventTableModel.addRow(row);
                }
            }
        }
    }
    
    private void refreshUserTable() {
        // Clear table
        userTableModel.setRowCount(0);
        
        // Add rows for each user (more efficient approach)
        String[] prefixes = {"U", "A", ""};
        
        for (String prefix : prefixes) {
            for (int i = 1; i <= 20; i++) { // Reduced from 50 to 20 for efficiency
                String userId = prefix + i;
                User user = system.findUser(userId);
                
                if (user != null) {
                    Object[] row = {
                        user.getUserId(),
                        user.getName(),
                        user.getUserType(),
                        user.getEmail(),
                        user.getPhone()
                    };
                    userTableModel.addRow(row);
                }
            }
        }
    }
    
    private String generateReport(String reportType) {
        StringBuilder report = new StringBuilder();
        report.append("=== ").append(reportType).append(" ===\n\n");
        
        switch (reportType) {
            case "Event Summary":
                report.append("Total events: ").append(system.getTotalEventCount()).append("\n");
                report.append("Total budget: ₹").append(system.calculateTotalBudget()).append("\n\n");
                
                // Add event details
                for (int i = 0; i < eventTableModel.getRowCount(); i++) {
                    report.append("Event: ").append(eventTableModel.getValueAt(i, 1)).append("\n");
                    report.append("Type: ").append(eventTableModel.getValueAt(i, 2)).append("\n");
                    report.append("Date: ").append(eventTableModel.getValueAt(i, 3)).append("\n");
                    report.append("Budget: ₹").append(eventTableModel.getValueAt(i, 6)).append("\n\n");
                }
                break;
                
            case "Events by Type":
                int corporateCount = 0;
                int weddingCount = 0;
                int conferenceCount = 0;
                
                for (int i = 0; i < eventTableModel.getRowCount(); i++) {
                    String type = (String) eventTableModel.getValueAt(i, 2);
                    switch (type) {
                        case "Corporate": corporateCount++; break;
                        case "Wedding": weddingCount++; break;
                        case "Conference": conferenceCount++; break;
                    }
                }
                
                report.append("Corporate Events: ").append(corporateCount).append("\n");
                report.append("Wedding Events: ").append(weddingCount).append("\n");
                report.append("Conference Events: ").append(conferenceCount).append("\n");
                break;
                
            case "Budget Summary":
                double totalBudget = system.calculateTotalBudget();
                report.append("Total Budget Allocation: ₹").append(totalBudget).append("\n\n");
                
                if (totalBudget > 0) {
                    report.append("Budget Distribution:\n");
                    for (int i = 0; i < eventTableModel.getRowCount(); i++) {
                        String name = (String) eventTableModel.getValueAt(i, 1);
                        double budget = (double) eventTableModel.getValueAt(i, 6);
                        double percentage = (budget / totalBudget) * 100;
                        report.append(name).append(": ₹").append(budget);
                        report.append(" (").append(String.format("%.1f", percentage)).append("%)\n");
                    }
                }
                break;
        }
        
        return report.toString();
    }
    
    public static void showGUI() {
        SwingUtilities.invokeLater(() -> {
            SimpleEventManagementGUI gui = new SimpleEventManagementGUI();
            gui.setVisible(true);
        });
    }
} 