# Base image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy Gradle files from the root context to the service context
COPY gradlew /app/
COPY gradle /app/gradle/
COPY module-video/build.gradle /app/
COPY settings.gradle /app/
COPY module-video/src /app/src

# Ensure gradlew is executable
RUN chmod +x gradlew

# Print Gradle version (optional, for debugging)
RUN ./gradlew --version

# Build the application with detailed output
RUN ./gradlew build -x test --info

# Copy the built JAR file
COPY build/libs/adjustment-*.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
