# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR file from the local context
# Make sure to match the actual JAR file name
COPY module-user/build/libs/adjustment.module-user.jar app.jar

# Expose the port your application will run on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]