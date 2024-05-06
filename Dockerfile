# Use a base image with Java 17
FROM openjdk:17-jdk-alpine

# Argument for the JAR file
ARG JAR_FILE=target/*.jar

# Copy the JAR file to the container
COPY ${JAR_FILE} app.jar

# Command to run the application
ENTRYPOINT ["java","-jar","/app.jar"]