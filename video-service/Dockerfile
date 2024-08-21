# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Gradle files from the root context to the service context
COPY gradlew /app/
COPY gradle /app/gradle/
COPY video-service/build.gradle /app/
COPY settings.gradle /app/
COPY video-service/src /app/src

# Ensure gradlew is executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build --stacktrace --info --debug

# Copy the built JAR file
COPY build/libs/adjustment-*.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
