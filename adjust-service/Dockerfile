# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy necessary files from the build context
COPY gradlew /app/
COPY gradle /app/gradle
COPY build.gradle /app/
COPY settings.gradle /app/
COPY src /app/src

# Ensure gradlew is executable
RUN chmod +x ./gradlew

# Build the application without running tests
RUN ./gradlew build -x test

# Copy the built JAR file
COPY build/libs/adjustment-*.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
