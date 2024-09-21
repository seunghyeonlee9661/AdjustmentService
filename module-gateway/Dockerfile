# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Gradle files from the root context to the service context
COPY gradlew /app/
COPY gradle /app/gradle/
COPY build.gradle /app/

COPY module-gateway/build.gradle /app/module-gateway/
COPY module-gateway/src /app/module-gateway/src

#Copy settings.gradle for module
COPY module-gateway/settings.gradle /app/

# Ensure gradlew is executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build -x test

# Copy the built JAR file to /app.jar
RUN cp module-gateway/build/libs/module-gateway.jar /app.jar

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app.jar"]
