# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy application files
COPY . /app

# Build the application
RUN ./gradlew build

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "build/libs/adjustment-0.0.1-SNAPSHOT.jar"]