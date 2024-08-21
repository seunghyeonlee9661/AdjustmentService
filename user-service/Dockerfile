# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy gradle wrapper and build files
COPY ../gradlew /app/
COPY ../gradle /app/gradle
COPY ../build.gradle /app/
COPY ../settings.gradle /app/

# Copy source code
COPY src /app/src

# Make gradlew executable
RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew build -x test

# Copy the built JAR file
COPY build/libs/adjustment-*.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
