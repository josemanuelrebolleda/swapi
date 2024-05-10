# Use a specific version of the base image with Maven and Java 17 for building the app
FROM maven:3.8.3-openjdk-17-slim AS build

# Set the current working directory in the image
WORKDIR /movies

# Copy the pom.xml file to the working directory
COPY pom.xml .

# Download all required dependencies into one layer
RUN mvn dependency:go-offline -B

# Copy the rest of the application
COPY src ./src

# Build the application
RUN mvn clean package

# Use a specific version of the base image containing Java 17 for running the app
FROM openjdk:17-jdk-alpine3.14

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Use an environment variable for the JAR file name
ENV JAR_FILE=movies-0.0.1-SNAPSHOT.jar

# Copy the application's jar file from the build image
COPY --from=build /movies/target/*.jar ${JAR_FILE}

# Create a group and user
RUN addgroup -S spring && adduser -S spring -G spring

# Tell docker that all future commands should run as the spring user
USER JMRD:passwordJMRD

# Tell docker that all future commands should run as the spring user
USER spring:spring

# Copy the script into the image
COPY start.sh start.sh
RUN chmod +x start.sh

# Run the jar file
ENTRYPOINT ["./start.sh"]

# Add a healthcheck
HEALTHCHECK --interval=5m --timeout=3s CMD curl -f http://localhost:8080/actuator/health || exit 1