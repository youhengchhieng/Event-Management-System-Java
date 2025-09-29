package eventmanagement.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import eventmanagement.EventManagementSystem;
import eventmanagement.core.Event;
import eventmanagement.events.CorporateEvent;
import eventmanagement.events.WeddingEvent;
import eventmanagement.events.ConferenceEvent;
import eventmanagement.users.User;
import eventmanagement.users.Customer;
import eventmanagement.users.Admin;
import eventmanagement.util.DateValidator;
import eventmanagement.util.StringValidator;

public class EventManagementGUI extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel eventsPanel;
    private JPanel usersPanel;
    private JPanel reportsPanel;
    private JTextArea eventsTextArea;
    private JTextArea usersTextArea;
    private JTextArea reportTextArea;
    
    // Reference to the system backend
    private EventManagementSystem system;
    
    public EventManagementGUI() {
        setTitle("Event Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initialize the system
        system = new EventManagementSystem();
        
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        
        // Create panels
        createEventsPanel();
        createUsersPanel();
        createReportsPanel();
        
        // Add panels to tabbed pane
        tabbedPane.addTab("Events", eventsPanel);
        tabbedPane.addTab("Users", usersPanel);
        tabbedPane.addTab("Reports", reportsPanel);
        
        // Add tabbed pane to frame
        getContentPane().add(tabbedPane);
        
        // Set frame location
        setLocationRelativeTo(null);
    }
    
    private void createEventsPanel() {
        eventsPanel = new JPanel();
        eventsPanel.setLayout(new BorderLayout());
        
        JPanel controlPanel = new JPanel();
        JButton addButton = new JButton("Add Event");
        JButton editButton = new JButton("Edit Event");
        JButton deleteButton = new JButton("Delete Event");
        JButton refreshButton = new JButton("Refresh");
        
        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);
        controlPanel.add(refreshButton);
        
        eventsTextArea = new JTextArea();
        eventsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(eventsTextArea);
        
        eventsPanel.add(controlPanel, BorderLayout.NORTH);
        eventsPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Add event listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddEventDialog();
            }
        });
        
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String eventId = JOptionPane.showInputDialog(eventsPanel, "Enter Event ID to edit:");
                if (eventId != null && !eventId.trim().isEmpty()) {
                    Event event = system.findEvent(eventId);
                    if (event != null) {
                        // Show edit dialog (simplified for now)
                        JOptionPane.showMessageDialog(eventsPanel, "Edit functionality will be implemented in a future version.");
                    } else {
                        JOptionPane.showMessageDialog(eventsPanel, "Event not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String eventId = JOptionPane.showInputDialog(eventsPanel, "Enter Event ID to delete:");
                if (eventId != null && !eventId.trim().isEmpty()) {
                    Event event = system.findEvent(eventId);
                    if (event != null) {
                        int result = JOptionPane.showConfirmDialog(eventsPanel, 
                            "Are you sure you want to delete the event: " + event.getEventName() + "?", 
                            "Confirm Delete", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            system.removeEvent(eventId);
                            refreshEventsDisplay();
                        }
                    } else {
                        JOptionPane.showMessageDialog(eventsPanel, "Event not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshEventsDisplay();
            }
        });
        
        // Initially display events
        refreshEventsDisplay();
    }
    
    private void showAddEventDialog() {
        // Create a custom dialog for adding an event
        JDialog dialog = new JDialog(this, "Add New Event", true);
        dialog.setLayout(new BorderLayout());
        
        // Create event type selection panel at the top with bigger buttons
        JPanel eventTypePanel = new JPanel();
        eventTypePanel.setBorder(BorderFactory.createTitledBorder("Select Event Type"));
        eventTypePanel.setLayout(new GridLayout(1, 3, 10, 10));
        
        JRadioButton corporateRadio = new JRadioButton("Corporate Event");
        JRadioButton weddingRadio = new JRadioButton("Wedding Event");
        JRadioButton conferenceRadio = new JRadioButton("Conference Event");
        
        // Set corporate as default
        corporateRadio.setSelected(true);
        
        // Group the radio buttons
        ButtonGroup eventTypeGroup = new ButtonGroup();
        eventTypeGroup.add(corporateRadio);
        eventTypeGroup.add(weddingRadio);
        eventTypeGroup.add(conferenceRadio);
        
        // Add to panel with some styling
        corporateRadio.setFont(new Font(corporateRadio.getFont().getName(), Font.BOLD, 14));
        weddingRadio.setFont(new Font(weddingRadio.getFont().getName(), Font.BOLD, 14));
        conferenceRadio.setFont(new Font(conferenceRadio.getFont().getName(), Font.BOLD, 14));
        
        eventTypePanel.add(corporateRadio);
        eventTypePanel.add(weddingRadio);
        eventTypePanel.add(conferenceRadio);
        
        // Main form panel
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add form fields (without event type since it's now in its own panel)
        formPanel.add(new JLabel("Event ID:"));
        JTextField eventIdField = new JTextField();
        formPanel.add(eventIdField);
        
        formPanel.add(new JLabel("Event Name:"));
        JTextField eventNameField = new JTextField();
        formPanel.add(eventNameField);
        
        formPanel.add(new JLabel("Event Date (dd/MM/yyyy):"));
        JTextField eventDateField = new JTextField();
        formPanel.add(eventDateField);
        
        formPanel.add(new JLabel("Event Venue:"));
        JTextField eventVenueField = new JTextField();
        formPanel.add(eventVenueField);
        
        formPanel.add(new JLabel("Max Capacity:"));
        JTextField maxCapacityField = new JTextField();
        formPanel.add(maxCapacityField);
        
        formPanel.add(new JLabel("Budget:"));
        JTextField budgetField = new JTextField();
        formPanel.add(budgetField);
        
        // Additional fields panel that changes based on event type
        JPanel additionalFieldsPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        additionalFieldsPanel.setBorder(BorderFactory.createTitledBorder("Event-Specific Details"));
        
        // Corporate event fields 
        JTextField companyNameField = new JTextField();
        JTextField contactPersonField = new JTextField();
        JCheckBox includesLunchCheckbox = new JCheckBox();
        
        // Wedding event fields
        JTextField brideNameField = new JTextField();
        JTextField groomNameField = new JTextField();
        JTextField weddingThemeField = new JTextField();
        
        // Conference event fields
        JTextField conferenceTopicField = new JTextField();
        JTextField speakerNameField = new JTextField();
        JTextField durationField = new JTextField();
        
        // Initial setup for Corporate event
        updateAdditionalFields(additionalFieldsPanel, "Corporate", 
                              companyNameField, contactPersonField, includesLunchCheckbox,
                              brideNameField, groomNameField, weddingThemeField,
                              conferenceTopicField, speakerNameField, durationField);
        
        // Listen for changes to the radio buttons
        ActionListener radioListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType;
                if (corporateRadio.isSelected()) {
                    selectedType = "Corporate";
                } else if (weddingRadio.isSelected()) {
                    selectedType = "Wedding";
                } else {
                    selectedType = "Conference";
                }
                
                updateAdditionalFields(additionalFieldsPanel, selectedType,
                                     companyNameField, contactPersonField, includesLunchCheckbox,
                                     brideNameField, groomNameField, weddingThemeField,
                                     conferenceTopicField, speakerNameField, durationField);
                dialog.pack();
            }
        };
        
        corporateRadio.addActionListener(radioListener);
        weddingRadio.addActionListener(radioListener);
        conferenceRadio.addActionListener(radioListener);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save Event");
        JButton cancelButton = new JButton("Cancel");
        
        // Make the save button more noticeable
        saveButton.setBackground(new Color(0, 120, 0));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font(saveButton.getFont().getName(), Font.BOLD, 14));
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        // Add action listeners to buttons
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validate common fields
                    String eventId = eventIdField.getText().trim();
                    String eventName = eventNameField.getText().trim();
                    String eventDate = eventDateField.getText().trim();
                    String eventVenue = eventVenueField.getText().trim();
                    
                    if (eventId.isEmpty() || eventName.isEmpty() || eventDate.isEmpty() || eventVenue.isEmpty()) {
                        throw new Exception("All fields are required");
                    }
                    
                    if (!DateValidator.isValidDate(eventDate)) {
                        throw new Exception("Invalid date format. Use dd/MM/yyyy");
                    }
                    
                    if (!DateValidator.isFutureDate(eventDate)) {
                        throw new Exception("Event date must be in the future");
                    }
                    
                    int maxCapacity = Integer.parseInt(maxCapacityField.getText().trim());
                    double budget = Double.parseDouble(budgetField.getText().trim());
                    
                    if (maxCapacity <= 0 || budget <= 0) {
                        throw new Exception("Max capacity and budget must be positive numbers");
                    }
                    
                    // Determine selected event type from radio buttons
                    String eventType;
                    if (corporateRadio.isSelected()) {
                        eventType = "Corporate";
                    } else if (weddingRadio.isSelected()) {
                        eventType = "Wedding";
                    } else if (conferenceRadio.isSelected()) {
                        eventType = "Conference";
                    } else {
                        throw new Exception("Please select an event type");
                    }
                    
                    Event newEvent = null;
                    
                    // Create appropriate event type
                    switch (eventType) {
                        case "Corporate":
                            String companyName = companyNameField.getText().trim();
                            String contactPerson = contactPersonField.getText().trim();
                            boolean includesLunch = includesLunchCheckbox.isSelected();
                            
                            if (companyName.isEmpty() || contactPerson.isEmpty()) {
                                throw new Exception("Company name and contact person are required");
                            }
                            
                            newEvent = new CorporateEvent(eventId, eventName, eventDate, eventVenue, 
                                                       maxCapacity, budget, companyName, contactPerson, includesLunch);
                            break;
                            
                        case "Wedding":
                            String brideName = brideNameField.getText().trim();
                            String groomName = groomNameField.getText().trim();
                            String weddingTheme = weddingThemeField.getText().trim();
                            
                            if (brideName.isEmpty() || groomName.isEmpty()) {
                                throw new Exception("Bride and groom names are required");
                            }
                            
                            // Wedding event with additional required parameters
                            int guestCount = 100; // Default guest count
                            boolean includesDecoration = true; // Default decoration setting
                            
                            newEvent = new WeddingEvent(eventId, eventName, eventDate, eventVenue, 
                                                     maxCapacity, budget, brideName, groomName,
                                                     guestCount, includesDecoration);
                            break;
                            
                        case "Conference":
                            String conferenceTopic = conferenceTopicField.getText().trim();
                            String speakerName = speakerNameField.getText().trim();
                            int duration = Integer.parseInt(durationField.getText().trim());
                            
                            if (conferenceTopic.isEmpty() || speakerName.isEmpty() || duration <= 0) {
                                throw new Exception("Topic, speaker name, and valid duration are required");
                            }
                            
                            // Using duration as speaker count and default equipment needed
                            int speakerCount = 1; // Default to 1 speaker
                            boolean technicalEquipmentNeeded = true;
                            
                            newEvent = new ConferenceEvent(eventId, eventName, eventDate, eventVenue, 
                                                        maxCapacity, budget, conferenceTopic, speakerCount,
                                                        technicalEquipmentNeeded);
                            break;
                    }
                    
                    if (newEvent != null) {
                        system.addEvent(newEvent);
                        JOptionPane.showMessageDialog(dialog, 
                                                  "Event added successfully:\n" + eventType + ": " + eventName, 
                                                  "Success", JOptionPane.INFORMATION_MESSAGE);
                        refreshEventsDisplay();
                        dialog.dispose();
                    }
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid number format. Please check capacity, budget, and duration fields.", 
                                              "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage(), 
                                              "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        
        // Create main panel to hold all components
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add components to the main panel
        mainPanel.add(eventTypePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(additionalFieldsPanel, BorderLayout.SOUTH);
        
        // Assemble the dialog
        dialog.add(mainPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void updateAdditionalFields(JPanel panel, String eventType,
                                      JTextField companyNameField, JTextField contactPersonField, JCheckBox includesLunchCheckbox,
                                      JTextField brideNameField, JTextField groomNameField, JTextField weddingThemeField,
                                      JTextField conferenceTopicField, JTextField speakerNameField, JTextField durationField) {
        // Clear the panel
        panel.removeAll();
        
        // Add appropriate fields based on event type
        switch (eventType) {
            case "Corporate":
                panel.add(new JLabel("Company Name:"));
                panel.add(companyNameField);
                panel.add(new JLabel("Contact Person:"));
                panel.add(contactPersonField);
                panel.add(new JLabel("Includes Lunch:"));
                panel.add(includesLunchCheckbox);
                break;
                
            case "Wedding":
                panel.add(new JLabel("Bride Name:"));
                panel.add(brideNameField);
                panel.add(new JLabel("Groom Name:"));
                panel.add(groomNameField);
                panel.add(new JLabel("Wedding Theme:"));
                panel.add(weddingThemeField);
                break;
                
            case "Conference":
                panel.add(new JLabel("Conference Topic:"));
                panel.add(conferenceTopicField);
                panel.add(new JLabel("Speaker Name:"));
                panel.add(speakerNameField);
                panel.add(new JLabel("Duration (hours):"));
                panel.add(durationField);
                break;
        }
        
        panel.revalidate();
        panel.repaint();
    }
    
    private void refreshEventsDisplay() {
        // Clear the text area
        eventsTextArea.setText("");
        
        // Get all events from the system and display them
        List<Event> allEvents = system.getAllEvents();
        
        if (allEvents.isEmpty()) {
            eventsTextArea.setText("No events available.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("===== All Events =====\n");
            
            for (Event event : allEvents) {
                sb.append("---------------------\n");
                sb.append(event.toString()).append("\n");
                sb.append("---------------------\n\n");
            }
            
            eventsTextArea.setText(sb.toString());
        }
    }
    
    private void createUsersPanel() {
        usersPanel = new JPanel();
        usersPanel.setLayout(new BorderLayout());
        
        JPanel controlPanel = new JPanel();
        JButton addButton = new JButton("Add User");
        JButton editButton = new JButton("Edit User");
        JButton deleteButton = new JButton("Delete User");
        JButton refreshButton = new JButton("Refresh");
        
        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);
        controlPanel.add(refreshButton);
        
        usersTextArea = new JTextArea();
        usersTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(usersTextArea);
        
        usersPanel.add(controlPanel, BorderLayout.NORTH);
        usersPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Add event listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddUserDialog();
            }
        });
        
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userId = JOptionPane.showInputDialog(usersPanel, "Enter User ID to edit:");
                if (userId != null && !userId.trim().isEmpty()) {
                    User user = system.findUser(userId);
                    if (user != null) {
                        // Show edit dialog (simplified for now)
                        JOptionPane.showMessageDialog(usersPanel, "Edit functionality will be implemented in a future version.");
                    } else {
                        JOptionPane.showMessageDialog(usersPanel, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userId = JOptionPane.showInputDialog(usersPanel, "Enter User ID to delete:");
                if (userId != null && !userId.trim().isEmpty()) {
                    User user = system.findUser(userId);
                    if (user != null) {
                        int result = JOptionPane.showConfirmDialog(usersPanel, 
                            "Are you sure you want to delete user: " + user.getName() + "?", 
                            "Confirm Delete", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            system.removeUser(userId);
                            refreshUsersDisplay();
                        }
                    } else {
                        JOptionPane.showMessageDialog(usersPanel, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshUsersDisplay();
            }
        });
        
        // Initially display users
        refreshUsersDisplay();
    }
    
    private void showAddUserDialog() {
        // Create a custom dialog for adding a user
        JDialog dialog = new JDialog(this, "Add New User", true);
        dialog.setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add form fields
        formPanel.add(new JLabel("User Type:"));
        String[] userTypes = {"Customer", "Admin"};
        JComboBox<String> userTypeCombo = new JComboBox<>(userTypes);
        formPanel.add(userTypeCombo);
        
        formPanel.add(new JLabel("User ID:"));
        JTextField userIdField = new JTextField();
        formPanel.add(userIdField);
        
        formPanel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        formPanel.add(nameField);
        
        formPanel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        formPanel.add(emailField);
        
        formPanel.add(new JLabel("Phone:"));
        JTextField phoneField = new JTextField();
        formPanel.add(phoneField);
        
        // Additional fields panel that changes based on user type
        JPanel additionalFieldsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        
        // Customer fields
        JTextField addressField = new JTextField();
        JTextField preferredEventTypeField = new JTextField();
        
        // Admin fields
        JTextField departmentField = new JTextField();
        JTextField accessLevelField = new JTextField();
        
        // Initial setup for Customer
        updateUserAdditionalFields(additionalFieldsPanel, "Customer", 
                                 addressField, preferredEventTypeField,
                                 departmentField, accessLevelField);
        
        // Listen for changes to user type
        userTypeCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) userTypeCombo.getSelectedItem();
                updateUserAdditionalFields(additionalFieldsPanel, selectedType,
                                        addressField, preferredEventTypeField,
                                        departmentField, accessLevelField);
                dialog.pack();
            }
        });
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        // Add action listeners to buttons
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validate common fields
                    String userId = userIdField.getText().trim();
                    String name = nameField.getText().trim();
                    String email = emailField.getText().trim();
                    String phone = phoneField.getText().trim();
                    
                    if (userId.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                        throw new Exception("All fields are required");
                    }
                    
                    if (!StringValidator.isValidEmail(email)) {
                        throw new Exception("Invalid email format");
                    }
                    
                    if (!StringValidator.isValidPhoneNumber(phone)) {
                        throw new Exception("Invalid phone number format");
                    }
                    
                    String userType = (String) userTypeCombo.getSelectedItem();
                    User newUser = null;
                    
                    // Create appropriate user type
                    switch (userType) {
                        case "Customer":
                            String address = addressField.getText().trim();
                            String preferredEventType = preferredEventTypeField.getText().trim();
                            
                            if (address.isEmpty()) {
                                throw new Exception("Address is required");
                            }
                            
                            newUser = new Customer(userId, name, email, phone, address, preferredEventType);
                            break;
                            
                        case "Admin":
                            String department = departmentField.getText().trim();
                            String accessLevel = accessLevelField.getText().trim();
                            
                            if (department.isEmpty() || accessLevel.isEmpty()) {
                                throw new Exception("Department and access level are required");
                            }
                            
                            newUser = new Admin(userId, name, email, phone, department, accessLevel);
                            break;
                    }
                    
                    if (newUser != null) {
                        system.addUser(newUser);
                        refreshUsersDisplay();
                        dialog.dispose();
                    }
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage(), 
                                              "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        
        // Assemble the dialog
        dialog.add(formPanel, BorderLayout.NORTH);
        dialog.add(additionalFieldsPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void updateUserAdditionalFields(JPanel panel, String userType,
                                         JTextField addressField, JTextField preferredEventTypeField,
                                         JTextField departmentField, JTextField accessLevelField) {
        // Clear the panel
        panel.removeAll();
        
        // Add appropriate fields based on user type
        switch (userType) {
            case "Customer":
                panel.add(new JLabel("Address:"));
                panel.add(addressField);
                panel.add(new JLabel("Preferred Event Type:"));
                panel.add(preferredEventTypeField);
                break;
                
            case "Admin":
                panel.add(new JLabel("Department:"));
                panel.add(departmentField);
                panel.add(new JLabel("Access Level:"));
                panel.add(accessLevelField);
                break;
        }
        
        panel.revalidate();
        panel.repaint();
    }
    
    private void refreshUsersDisplay() {
        // Clear the text area
        usersTextArea.setText("");
        
        // Check if we have users to display
        boolean hasUsers = false;
        StringBuilder sb = new StringBuilder();
        sb.append("===== All Users =====\n");
        
        // Try various user ID patterns
        // Since we don't have direct access to the users list, we need to try different IDs
        
        // Try alphabetic IDs (U1, U2, A1, A2, etc.)
        for (char c : new char[]{'U', 'A', 'C'}) {
            for (int i = 1; i <= 100; i++) {
                String userId = String.valueOf(c) + i;
                User user = system.findUser(userId);
                if (user != null) {
                    sb.append("---------------------\n");
                    sb.append(user.toString()).append("\n");
                    sb.append("---------------------\n\n");
                    hasUsers = true;
                }
            }
        }
        
        // Try numeric IDs
        for (int i = 1; i <= 100; i++) {
            String userId = String.valueOf(i);
            User user = system.findUser(userId);
            if (user != null) {
                sb.append("---------------------\n");
                sb.append(user.toString()).append("\n");
                sb.append("---------------------\n\n");
                hasUsers = true;
            }
        }
        
        if (hasUsers) {
            usersTextArea.setText(sb.toString());
        } else {
            usersTextArea.setText("No users available. Add users using the 'Add User' button.");
        }
    }
    
    private void createReportsPanel() {
        reportsPanel = new JPanel();
        reportsPanel.setLayout(new BorderLayout());
        
        JPanel controlPanel = new JPanel();
        JButton generateButton = new JButton("Generate Report");
        JComboBox<String> reportTypeComboBox = new JComboBox<>(new String[]{"Events Summary", "Revenue Report", "User Activity"});
        
        controlPanel.add(new JLabel("Report Type:"));
        controlPanel.add(reportTypeComboBox);
        controlPanel.add(generateButton);
        
        reportTextArea = new JTextArea();
        reportTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportTextArea);
        
        reportsPanel.add(controlPanel, BorderLayout.NORTH);
        reportsPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Add event listener for generate button
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedReport = (String) reportTypeComboBox.getSelectedItem();
                generateReport(selectedReport);
            }
        });
    }
    
    private void generateReport(String reportType) {
        reportTextArea.setText("Generating " + reportType + "...\n\n");
        
        switch (reportType) {
            case "Events Summary":
                if (system.getTotalEventCount() == 0) {
                    reportTextArea.append("No events available for reporting.");
                } else {
                    reportTextArea.append("Total Events: " + system.getTotalEventCount() + "\n");
                    reportTextArea.append("Total Budget: ₹" + system.calculateTotalBudget() + "\n\n");
                    reportTextArea.append("Event Details:\n");
                    reportTextArea.append("----------------\n");
                    
                    // This is a simplified implementation
                    // In a real application, you would iterate through all events
                    for (int i = 0; i < system.getTotalEventCount(); i++) {
                        String eventId = "E" + (i + 1);
                        Event event = system.findEvent(eventId);
                        if (event != null) {
                            reportTextArea.append(event.getEventId() + ": " + event.getEventName() + 
                                               " (" + event.getEventType() + ")\n");
                            reportTextArea.append("Date: " + event.getEventDate() + "\n");
                            reportTextArea.append("Venue: " + event.getEventVenue() + "\n");
                            reportTextArea.append("Budget: ₹" + event.getBudget() + "\n");
                            reportTextArea.append("----------------\n");
                        }
                    }
                }
                break;
                
            case "Revenue Report":
                reportTextArea.append("This is a sample revenue report.\n");
                reportTextArea.append("Total events: " + system.getTotalEventCount() + "\n");
                reportTextArea.append("Total budget allocation: ₹" + system.calculateTotalBudget() + "\n");
                break;
                
            case "User Activity":
                reportTextArea.append("This is a sample user activity report.\n");
                reportTextArea.append("This feature will be available in a future update.");
                break;
        }
    }
    
    public static void showGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EventManagementGUI().setVisible(true);
            }
        });
    }
}
