#!/bin/bash

# Run script for Event Management System

# Check if bin directory exists
if [ ! -d "bin" ]; then
    echo "Bin directory not found. Building project first..."
    ./build.sh
fi

# Run the application
echo "Running Event Management System GUI..."
java -cp bin eventmanagement.GUILauncher

echo "Application terminated."
