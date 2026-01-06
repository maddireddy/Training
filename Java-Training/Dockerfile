# Build stage
FROM maven:3.8.6-openjdk-17-slim AS build
WORKDIR /app

# Copy only the files needed for building to leverage Docker cache
COPY pom.xml .
COPY core-java/pom.xml core-java/pom.xml

# Download dependencies
RUN mvn -B dependency:go-offline -f pom.xml

# Copy source code
COPY . .

# Build the application
RUN mvn -B clean package -DskipTests

# Runtime stage
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/core-java/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Set the entry point
ENTRYPOINT ["java", "-jar", "app.jar"]
