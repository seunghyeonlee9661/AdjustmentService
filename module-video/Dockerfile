# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Gradle files from the root context to the service context
COPY gradlew /app/
COPY gradle /app/gradle/
COPY settings.gradle /app/
COPY build.gradle /app/
COPY module-common/build.gradle /app/module-common/
COPY module-video/build.gradle /app/module-video/
COPY module-common/src /app/module-common/src
COPY module-video/src /app/module-video/src

# Ensure gradlew is executable
RUN chmod +x gradlew

RUN ./gradlew wrapper --gradle-version 8.8

# Build the application
RUN ./gradlew :module-video:build -x test --stacktrace

# Copy the built JAR file using the exact name
COPY module-video/build/libs/adjustment.module-video.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]