#!/bin/bash

# Create necessary directories if they don't exist
mkdir -p core-java/src/main/java/com/javatraining/core/examples
mkdir -p core-java/src/main/java/com/javatraining/core/notes

# Copy Core-Java examples and notes
cp -r /tmp/Core-Java/ConsoleLearning core-java/src/main/java/com/javatraining/core/examples/
cp /tmp/Core-Java/Notes_Buffer.txt core-java/src/main/java/com/javatraining/core/notes/
cp /tmp/Core-Java/OOPS\ Principles core-java/src/main/java/com/javatraining/core/notes/
cp /tmp/Core-Java/Scrum core-java/src/main/java/com/javatraining/core/notes/
cp /tmp/Core-Java/Tasks core-java/src/main/java/com/javatraining/core/notes/

# Copy Learning repository content
# Object-Oriented Programming examples
cp -r /tmp/Learning/abstractLearning core-java/src/main/java/com/javatraining/core/examples/
cp -r /tmp/Learning/classPack core-java/src/main/java/com/javatraining/core/examples/
cp -r /tmp/Learning/inheritenceLearning core-java/src/main/java/com/javatraining/core/examples/
cp -r /tmp/Learning/polyLearning core-java/src/main/java/com/javatraining/core/examples/
cp -r /tmp/Learning/staticLearning core-java/src/main/java/com/javatraining/core/examples/

# Advanced Topics
cp -r /tmp/Learning/exceptionHandling core-java/src/main/java/com/javatraining/advanced/examples/
cp -r /tmp/Learning/packageLearning core-java/src/main/java/com/javatraining/advanced/examples/

# Interface and Abstraction
cp -r /tmp/Learning/intfLearning core-java/src/main/java/com/javatraining/core/examples/
cp -r /tmp/Learning/dataAbstractionLearning core-java/src/main/java/com/javatraining/core/examples/

# Other Concepts
cp -r /tmp/Learning/contructorLearning core-java/src/main/java/com/javatraining/core/examples/
cp -r /tmp/Learning/messagePassing core-java/src/main/java/com/javatraining/core/examples/
cp -r /tmp/Learning/swithchStatement core-java/src/main/java/com/javatraining/core/examples/

# PDFs and Documentation
mkdir -p core-java/src/main/resources/docs
cp /tmp/Core-Java/*.pdf core-java/src/main/resources/docs/
cp /tmp/Core-Java/*.pptx core-java/src/main/resources/docs/

# Create a README for the integrated content
echo "# Integrated Content from Core-Java and Learning Repositories

This directory contains integrated content from the following repositories:
- https://github.com/maddireddy/Core-Java
- https://github.com/maddireddy/Learning

## Structure
- `examples/`: Practical code examples organized by topic
- `notes/`: Documentation and reference materials
- `resources/docs/`: PDFs and presentations

## How to Use
1. Navigate to the specific topic you're interested in
2. Review the examples and notes
3. Run the examples using Maven or your preferred Java IDE
" > core-java/src/main/java/com/javatraining/core/INTEGRATION.md

echo "Content organization complete. Please review the structure and make any necessary adjustments."
