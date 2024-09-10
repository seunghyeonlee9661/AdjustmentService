# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Gradle files from the root context to the service context
COPY gradlew /app/
COPY gradle /app/gradle/
COPY build.gradle /app/

COPY module-common/build.gradle /app/module-common/
COPY module-common/src /app/module-common/src

COPY module-video/build.gradle /app/module-video/
COPY module-video/src /app/module-video/src

#Copy settings.gradle for module
COPY module-video/settings.gradle /app/

# Ensure gradlew is executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build -x test

# Copy the built JAR file to /app.jar
RUN cp module-video/build/libs/module-video.jar /app.jar

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app.jar"]