# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Gradle files from the root context to the service context
# 루트 경로의 파일
COPY gradlew /app/
COPY gradle /app/gradle/
COPY settings.gradle /app/
COPY build.gradle /app/
# 공통 모듈의 파일
COPY module-common/build.gradle /app/module-common/
COPY module-common/src /app/module-common/src
# 모듈 내부 파일
COPY module-user/build.gradle /app/module-user/
COPY module-user/src /app/module-user/src

# Ensure gradlew is executable
RUN chmod +x gradlew

RUN ./gradlew wrapper --gradle-version 8.8

RUN ./gradlew clean
RUN rm -rf build/

# 해당 모듈에 의존성을 가진 정보만 빌드하도록 설정
RUN ./gradlew :module-user:build -x test --stacktrace --info

# Copy the built JAR file using the exact name
COPY module-user/build/libs/adjustment.module-user.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
