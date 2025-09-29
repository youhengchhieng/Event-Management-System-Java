# 🎉 Java Event Management System

[![Java](https://img.shields.io/badge/Java-JDK%208%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/technologies/downloads/)

A comprehensive, console-based Event Management System built with core Java. This application demonstrates key Object-Oriented Programming (OOP) principles to manage various types of events, users, and registrations in a structured and extensible way.

---

## 📋 Overview

This system provides a complete, self-contained solution for managing event and user data directly from a command-line interface. It is designed with a focus on clean architecture, modularity, and strong OOP foundations, making it a robust and extensible platform for event coordination. The application handles the entire lifecycle of event and user management, from creation to reporting.

---

## ✨ Key Features

- 📦 **Dynamic Object Management** Add, remove, and display events and users. The system uses a flexible, in-memory data structure (`LinkedList`) for efficient data handling.

- 🏛️ **Polymorphic Design** Create different types of events (**Corporate**, **Wedding**, **Conference**) and users (**Admin**, **Customer**). Each type has unique attributes and behaviors, showcasing the power of polymorphism.

- ⚙️ **Interactive Command-Line Interface (CLI)** A user-friendly, menu-driven interface allows for easy interaction with the system's features without needing a graphical front-end.

- ✔️ **Robust Data Validation** Built-in utility classes ensure data integrity by validating dates, email formats, phone numbers, and other user inputs before processing.

- ⚠️ **Structured Exception Handling** A custom exception hierarchy provides graceful error handling for invalid operations, such as searching for a non-existent event or entering incorrect data.

- 📊 **Reporting Module** Generate an on-demand summary report that displays key metrics like the total number of events, total budget across all events, and a count of events by category.

---

## 🗄️ Project Structure

The project is organized into a modular package structure to ensure a clean separation of concerns:

```
src/eventmanagement/
├── core/
│   ├── Event.java
│   ├── EventManager.java
│   └── Registration.java
├── events/
│   ├── ConferenceEvent.java
│   ├── CorporateEvent.java
│   └── WeddingEvent.java
├── exceptions/
│   ├── EventManagementException.java
│   ├── EventNotFoundException.java
│   └── ... (and other custom exceptions)
├── gui/
│   └── SimpleEventManagementGUI.java
├── users/
│   ├── Admin.java
│   ├── Customer.java
│   └── User.java
├── util/
│   ├── DateValidator.java
│   └── StringValidator.java
│
├── EventManagementSystem.java  # The main engine of the application.
├── Main1.java                  # The primary interactive CLI entry point.
└── GUILauncher.java            # The entry point for launching the GUI.
```

---

## 🛠️ Getting Started

Follow these instructions to get a copy of the project up and running on your local machine.

### Prerequisites

* Make sure you have a Java Development Kit (JDK) version 8 or higher installed.
* A terminal with `bash` support (like Git Bash on Windows, or the default terminal on macOS/Linux).

### Installation & Execution

1.  **Clone the repository**
    ```bash
    git clone <your-repository-url>
    cd <your-repository-directory>
    ```

2.  **Compile the source code**
    A build script is included to compile all `.java` files into the `bin` directory.
    ```bash
    bash build.sh
    ```

3.  **Run the application**
    Use the `run-main.sh` script to start the interactive console application.
    ```bash
    bash run-main.sh
    ```

---

## 💻 Usage

Once the application is running, you will be greeted with the main menu. Simply enter the number corresponding to the action you wish to perform.

```text
===== Event Management System =====
1. Add Event
2. Remove Event
3. Display All Events
4. Add User
5. Remove User
6. Display All Users
7. Generate Event Summary Report
8. Exit
Enter your choice: 
```
The application will guide you through the process for each option, prompting for required information like event details or user IDs.

---

## ⚙️ Architectural Concepts

The system is built on a foundation of proven software design principles for robustness and maintainability.

- ✅ **Object-Oriented Programming** for clean and reusable code.
    - **Abstraction**: Core logic is defined in abstract classes (`Event`, `User`) and interfaces (`EventManager`).
    - **Inheritance**: Concrete classes (`WeddingEvent`, `Admin`) extend base classes to inherit common functionality.
    - **Polymorphism**: The system treats all event and user types as their base objects, simplifying management logic.
- 🔗 **Separation of Concerns** by dividing the application into distinct packages for UI, business logic, data models, and utilities.
- ⚠️ **Custom Exception Hierarchy** to manage application-specific errors and provide clear feedback to the user.

---

## 🧪 Testing

To verify that the application is set up correctly:

1.  Run the application using `bash run-main.sh`.
2.  Select option `3` (Display All Events). It should report that no events are available.
3.  Select option `1` (Add Event) and follow the prompts to add a new event.
4.  Select option `3` again. The newly created event should now be displayed.
5.  Select option `8` to exit the application gracefully.

---

## 🔮 Future Enhancements

Planned features to enrich the system:

- 🗄️ **Database Persistence**: Integrate with a database (like MySQL or PostgreSQL) to store data permanently.
- 🎨 **Full GUI Implementation**: Develop the `SimpleEventManagementGUI` into a fully featured graphical interface using Swing or JavaFX.
- 🔐 **User Authentication**: Add a login system with roles and permissions.
- 📈 **Advanced Analytics**: Introduce more complex reports and data visualizations.
- 🌐 **API Endpoints**: Expose a REST API to allow other services to interact with the system.

---

## 👥 Author

This project was developed by **[Your Name/Team Name Here]**.

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps to contribute:

1.  Fork the repository.
2.  Create a new feature branch.
    ```bash
    git checkout -b feature/your-feature-name
    ```
3.  Commit your changes.
    ```bash
    git commit -m 'Add your amazing feature'
    ```
4.  Push to the branch.
    ```bash
    git push origin feature/your-feature-name
    ```
5.  Open a Pull Request. 🙌