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

COPY module-adjust/build.gradle /app/module-adjust/
COPY module-adjust/src /app/module-adjust/src

COPY module-batch/build.gradle /app/module-batch/
COPY module-batch/src /app/module-batch/src

#Copy settings.gradle for module
COPY module-batch/settings.gradle /app/

# Ensure gradlew is executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build -x test

# Copy the built JAR file to /app.jar
RUN cp module-batch/build/libs/module-batch.jar /app.jar

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app.jar"]