# Base image
FROM openjdk:17-jdk-slim

# Set environment variables
ENV WORKDIR_PATH=/user-service
ENV JAR_PATH=build/libs/adjustment-*.jar
ENV JAR_FILE=app.jar

# Set working directory
WORKDIR ${WORKDIR_PATH}

# Copy necessary files from the build context
COPY gradlew ${WORKDIR_PATH}/
COPY gradle ${WORKDIR_PATH}/gradle
COPY build.gradle ${WORKDIR_PATH}/
COPY settings.gradle ${WORKDIR_PATH}/
# Note: src is already in the context path

# Ensure gradlew is executable
RUN chmod +x ./gradlew

# Build the application without running tests
RUN ./gradlew build -x test

# Copy the built JAR file
COPY ${JAR_PATH} ${JAR_FILE}

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "${JAR_FILE}"]
