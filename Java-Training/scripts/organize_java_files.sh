#!/bin/bash

# Create necessary directories
mkdir -p core-java/src/main/java/com/javatraining/core/basics/{variables,operators,controlflow,arrays,strings}
mkdir -p core-java/src/main/java/com/javatraining/core/oops/{classes,inheritance,polymorphism,abstraction,encapsulation,interfaces}
mkdir -p core-java/src/main/java/com/javatraining/core/collections/{lists,sets,maps,queues,comparators}
mkdir -p core-java/src/main/java/com/javatraining/core/exceptions
mkdir -p core-java/src/main/java/com/javatraining/core/io/{files,streams,serialization}
mkdir -p core-java/src/main/java/com/javatraining/core/threads
mkdir -p core-java/src/main/java/com/javatraining/core/advanced/{generics,annotations,reflection,lambda,streams,optional}

# Move files to appropriate directories
# Basics
find original-content/ -type f -name "*Loop*.java" -exec cp {} core-java/src/main/java/com/javatraining/core/basics/controlflow/ \;
find original-content/ -type f -name "*Array*.java" -exec cp {} core-java/src/main/java/com/javatraining/core/basics/arrays/ \;
find original-content/ -type f -name "*String*.java" -exec cp {} core-java/src/main/java/com/javatraining/core/basics/strings/ \;

# OOPs
find original-content/ -type f -name "*Inherit*.java" -exec cp {} core-java/src/main/java/com/javatraining/core/oops/inheritance/ \;
find original-content/ -type f -name "*Poly*.java" -exec cp {} core-java/src/main/java/com/javatraining/core/oops/polymorphism/ \;
find original-content/ -type f -name "*Abstract*.java" -exec cp {} core-java/src/main/java/com/javatraining/core/oops/abstraction/ \;
find original-content/ -type f -name "*Interface*.java" -exec cp {} core-java/src/main/java/com/javatraining/core/oops/interfaces/ \;

# Exceptions
find original-content/ -type f -name "*Exception*.java" -exec cp {} core-java/src/main/java/com/javatraining/core/exceptions/ \;

# Threads
find original-content/ -type f -name "*Thread*.java" -exec cp {} core-java/src/main/java/com/javatraining/core/threads/ \;
find original-content/ -type f -name "*Runnable*.java" -exec cp {} core-java/src/main/java/com/javatraining/core/threads/ \;
find original-content/ -type f -name "*DeadLock*.java" -exec cp {} core-java/src/main/java/com/javatraining/core/threads/ \;

# I/O
find original-content/ -type f -name "*File*.java" -exec cp {} core-java/src/main/java/com/javatraining/core/io/files/ \;
find original-content/ -type f -name "*Stream*.java" -exec cp {} core-java/src/main/java/com/javatraining/core/io/streams/ \;
find original-content/ -type f -name "*Serializ*.java" -exec cp {} core-java/src/main/java/com/javatraining/core/io/serialization/ \;

# Advanced
find original-content/ -type f -name "*Lambda*.java" -exec cp {} core-java/src/main/java/com/javatraining/core/advanced/lambda/ \;
find original-content/ -type f -name "*Stream*.java" -exec cp {} core-java/src/main/java/com/javatraining/core/advanced/streams/ \;
find original-content/ -type f -name "*Optional*.java" -exec cp {} core-java/src/main/java/com/javatraining/core/advanced/optional/ \;

# Clean up any empty directories
find core-java/src/main/java/com/javatraining/core/ -type d -empty -delete

echo "Java files have been organized into their respective categories."
