#!/bin/bash

# Build script for Event Management System

echo "Building Event Management System..."

# Create bin directory if it doesn't exist
mkdir -p bin

# Compile all Java files
javac -d bin src/eventmanagement/**/*.java src/eventmanagement/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
else
    echo "Compilation failed!"
    exit 1
fi

echo "Build complete."
