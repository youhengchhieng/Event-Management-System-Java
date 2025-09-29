#!/bin/bash

# Run script for Event Management System CLI version

# Check if bin directory exists
if [ ! -d "bin" ]; then
    echo "Bin directory not found. Building project first..."
    ./build.sh
fi

# Run the application
echo "Running Event Management System CLI..."
java -cp bin eventmanagement.Main1

echo "Application terminated." 