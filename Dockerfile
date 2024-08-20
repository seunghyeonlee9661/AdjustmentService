# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy all files from current directory to the container's /app directory
COPY gradlew /app/
COPY gradle /app/gradle
COPY build.gradle /app/
COPY settings.gradle /app/

# Copy the source code to the /app directory
COPY src /app/src

# Ensure gradlew is executable
RUN chmod +x ./gradlew

# Check if environment variables are set correctly
RUN echo "MYSQL_USER: $MYSQL_USER"
RUN echo "MYSQL_PASSWORD: $MYSQL_PASSWORD"
RUN echo "MYSQL_DATABASE: $MYSQL_DATABASE"

# Build the application without running tests
RUN ./gradlew build -x test

# Expose port
EXPOSE 8081

# Run the application
CMD ["java", "-jar", "build/libs/adjustment-0.0.1-SNAPSHOT.jar"]
