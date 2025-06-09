# Use an official lightweight Java runtime as the base image
FROM eclipse-temurin:21-jre-alpine

# Set working directory inside the container
WORKDIR /app

# Copy the JAR file from your local machine into the container
COPY target/taskmanager-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on (default 8080)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java","-jar","app.jar"]
