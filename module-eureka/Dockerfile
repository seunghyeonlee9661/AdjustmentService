# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Gradle files from the root context to the service context
COPY gradlew /app/
COPY gradle /app/gradle/
COPY build.gradle /app/

COPY module-eureka/build.gradle /app/module-eureka/
COPY module-eureka/src /app/module-eureka/src

#Copy settings.gradle for module
COPY module-eureka/settings.gradle /app/

# Ensure gradlew is executable
RUN chmod +x gradlew

# Check dependencies (output will be shown in logs)
RUN ./gradlew dependencies

# Build the application
RUN ./gradlew build -x test

# Copy the built JAR file to /app.jar
RUN cp module-eureka/build/libs/module-eureka.jar /app.jar

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app.jar"]
