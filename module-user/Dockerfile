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

COPY module-user/build.gradle /app/module-user/
COPY module-user/src /app/module-user/src

#Copy settings.gradle for module
COPY module-user/settings.gradle /app/

# Ensure gradlew is executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build -x test --stacktrace

# Check the build output directory
RUN ls -l module-user/build/libs/

# Copy the built JAR file to /app.jar
RUN cp module-user/build/libs/module-user-0.0.1-SNAPSHOT.jar /app.jar

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app.jar"]
