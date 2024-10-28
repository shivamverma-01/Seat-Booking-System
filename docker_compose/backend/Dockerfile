# Use an official Java runtime as a parent image
FROM openjdk:20-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY seat_reservation-0.0.1-SNAPSHOT.jar app.jar

# Expose the backend port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
