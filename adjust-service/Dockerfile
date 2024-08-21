# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Print the file list in the root directory of the container
RUN echo "Files in / directory:" && ls -l /
# Copy Gradle files and source code from the root context
COPY ../gradlew /app/
COPY ../gradle /app/gradle/
COPY build.gradle /app/
COPY ../settings.gradle /app/
COPY src /app/src

# Ensure gradlew is executable
RUN chmod +x gradlew

# Build the entire project (including the user-service module)
RUN ./gradlew build -x test

# Copy the built JAR file
COPY build/libs/user-service-*.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
