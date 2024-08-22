# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Gradle files from the root context to the service context
#COPY gradlew /app/
#COPY gradle /app/gradle/
#COPY module-adjust/build.gradle /app/
#COPY settings.gradle /app/
#COPY module-adjust/src /app/src

COPY gradlew /app/
COPY gradle /app/gradle/
COPY settings.gradle /app/
COPY module-common/build.gradle /app/module-common/
COPY module-adjust/build.gradle /app/module-adjust/
COPY module-common/src /app/module-common/src
COPY module-adjust/src /app/module-adjust/src

# Ensure gradlew is executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build -x test

# Copy the built JAR file
COPY build/libs/adjustment-*.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
