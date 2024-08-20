# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Gradle wrapper and configuration files
COPY gradlew /app/gradlew
COPY gradle /app/gradle
COPY build.gradle /app/build.gradle
COPY settings.gradle /app/settings.gradle

# Copy source code
COPY src /app/src

# Make Gradle wrapper executable
RUN chmod +x /app/gradlew

# Build the application
RUN ./gradlew build --no-daemon

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "build/libs/adjustment-0.0.1-SNAPSHOT.jar"]
